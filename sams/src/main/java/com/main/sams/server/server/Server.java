package com.main.sams.server.server;

import com.main.sams.server.database.DatabaseWorker;
import com.main.sams.student.Attendance;
import com.main.sams.student.ClassTime;
import com.main.sams.student.Group;
import com.main.sams.student.Student;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Server {

    // singleton
    private static Server instance = null;
    private ServerSocket serverSocket = null;
    private Socket clientSocket = null;
    private System.Logger logger = null;

    private static final int PORT = 8080;

    private ObjectOutputStream out = null;
    private ObjectInputStream in = null;

    private DatabaseWorker databaseWorker = null;
    private String dbHost = "";

    private String dbPort = "";

    private Server(String hostname, String port) {
        try {
            dbHost = hostname;
            dbPort = port;
            serverSocket = new ServerSocket(PORT);
            logger = System.getLogger("Server");
            logger.log(System.Logger.Level.INFO, "Server started on port " + PORT + " and ip address " + serverSocket.getInetAddress());
        } catch (Exception e) {
            logger.log(System.Logger.Level.ERROR, "Server constructor: Server failed to start on port " + PORT);
            System.exit(1);
        }
    }

    public static void main(String[] args) throws IOException {
        Server server = Server.getInstance(args[0], args[1]);
        server.runServer();
        server.cleanUpServer();
    }

    public static Server getInstance(String hostname, String port) {
        if (instance == null) {
            instance = new Server(hostname, port);
        }
        return instance;
    }

    private String getDbHost() {
        return dbHost;
    }

    private void setDbHost(String dbHost) {
        this.dbHost = dbHost;
    }

    private String getDbPort() {
        return dbPort;
    }

    private void setDbPort(String dbPort) {
        this.dbPort = dbPort;
    }

    private void runServer() {
        try {
            databaseWorker = DatabaseWorker.getInstance(getDbHost(), getDbPort());
            // handle one client at a time
            clientSocket = serverSocket.accept();
            logger.log(System.Logger.Level.INFO, "Client connected: " + clientSocket.getInetAddress().getHostAddress());
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());

            receivePackage();
        } catch (Exception e) {
            logger.log(System.Logger.Level.ERROR, "Server runServer: Server failed to run");
            cleanUpServer();
            System.exit(1);
        }
    }

    private void cleanUpServer() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
        } catch (Exception e) {
            logger.log(System.Logger.Level.ERROR, "Server cleanUpServer: Server failed to clean up");
            System.exit(1);
        }
    }

    private void receivePackage() {
        try {
            while (clientSocket.isConnected()) {
                // read from client
                SocketPackage socketPackage = (SocketPackage) in.readObject();
                if (socketPackage == null) {
                    logger.log(System.Logger.Level.INFO, "Received package from client is null. Closing connection.");
                    continue;
                }
                logger.log(System.Logger.Level.INFO, "Received package from client: " + socketPackage);

                // process package
                new Thread(() -> {
                    sendPackage(processPackage(socketPackage));
                }).start();
            }
        } catch (Exception e) {
            if (e.getClass() == EOFException.class) {
                logger.log(System.Logger.Level.INFO, "Client disconnected");
            } else {
                logger.log(System.Logger.Level.ERROR, "Server receivePackage: Server failed to receive package, error message: " + e.getMessage() + " error type: " + e.getClass());
            }

            cleanUpServer();
            System.exit(1);
        }
    }

    private void sendPackage(SocketPackage socketPackage) {
        try {
            // write to client
            if (!clientSocket.isConnected()) {
                logger.log(System.Logger.Level.INFO, "Client is not connected. Closing connection.");
                return;
            }
            out.writeObject(socketPackage);
            out.flush();
            logger.log(System.Logger.Level.INFO, "Sent package to client: " + socketPackage);
        } catch (Exception e) {
            logger.log(System.Logger.Level.ERROR, "Server sendPackage: Server failed to send package");
            cleanUpServer();
            System.exit(1);
        }
    }

    private SocketPackage processPackage(SocketPackage socketPackage) {
        SocketPackage responsePackage = null;

        switch (socketPackage.getRequestType()) {
            case ADD_STUDENT: {
                Student student = (Student) socketPackage.getObject1();
                databaseWorker.addStudent(student.getName(), student.getSurname(), student.getIndex());
                responsePackage = new SocketPackage(RequestType.SERVER_ACCEPTED, null, null);
                break;
            }
            case DELETE_STUDENT: {
                Student student = (Student) socketPackage.getObject1();
                databaseWorker.deleteStudent(student.getIndex());
                responsePackage = new SocketPackage(RequestType.SERVER_ACCEPTED, null, null);
                break;
            }
            case ADD_GROUP: {
                Group group = (Group) socketPackage.getObject1();
                databaseWorker.addGroup(group.getName(), group.getYear());
                responsePackage = new SocketPackage(RequestType.SERVER_ACCEPTED, null, null);
                break;
            }
            case DELETE_GROUP: {
                int groupid = (int) socketPackage.getObject1();
                databaseWorker.deleteGroup(groupid);
                responsePackage = new SocketPackage(RequestType.SERVER_ACCEPTED, null, null);
                break;
            }
            case ADD_STUDENT_TO_GROUP: {
                Student student = (Student) socketPackage.getObject1();
                int groupid = (int) socketPackage.getObject2();
                databaseWorker.addStudentToGroup(student.getIndex(), groupid);
                responsePackage = new SocketPackage(RequestType.SERVER_ACCEPTED, null, null);
                break;
            }
            case DELETE_STUDENT_FROM_GROUP: {
                Student student = (Student) socketPackage.getObject1();
                int groupid = (int) socketPackage.getObject2();
                databaseWorker.deleteStudentFromGroup(student.getIndex(), groupid);
                responsePackage = new SocketPackage(RequestType.SERVER_ACCEPTED, null, null);
                break;
            }
            case ADD_CLASS_TIME: {
                ClassTime classTime = (ClassTime) socketPackage.getObject1();
                databaseWorker.addClassTime(classTime.getName(), classTime.getDurationInMinutes(), classTime.getClassDate(),
                        classTime.getStartTime(), classTime.getEndTime(), classTime.getLocation(), classTime.getDescription());
                responsePackage = new SocketPackage(RequestType.SERVER_ACCEPTED, null, null);
                break;
            }
            case ADD_ATTENDANCE: {
                Attendance attendance = (Attendance) socketPackage.getObject1();
                int classTimeId = (int) socketPackage.getObject2();
                databaseWorker.addAttendance(attendance.getStudent().getIndex(), attendance.getAttendanceType(), classTimeId);
                break;
            }
            case PRINT_GROUP_ATTENDANCE: {
                int groupid = (int) socketPackage.getObject1();
                int classTimeId = (int) socketPackage.getObject2();
                ArrayList<Attendance> attendances = databaseWorker.getGroupAttendances(groupid, classTimeId);
                responsePackage = new SocketPackage(RequestType.SERVER_ACCEPTED, attendances, null);
                break;
            }
            case PRINT_ALL_STUDENTS: {
                ArrayList<Student> students = databaseWorker.getStudents();
                responsePackage = new SocketPackage(RequestType.SERVER_ACCEPTED, students, null);
                break;
            }
            case PRINT_ALL_GROUPS: {
                ArrayList<Group> groups = databaseWorker.getGroups();
                responsePackage = new SocketPackage(RequestType.SERVER_ACCEPTED, groups, null);
                break;
            }
            case PRINT_ALL_CLASS_TIMES: {
                ArrayList<ClassTime> classTimes = databaseWorker.getClassTimes();
                responsePackage = new SocketPackage(RequestType.SERVER_ACCEPTED, classTimes, null);
                break;
            }
            case PRINT_ALL_ATTENDANCES: {
                ArrayList<Attendance> attendances = databaseWorker.getAttendances();
                responsePackage = new SocketPackage(RequestType.SERVER_ACCEPTED, attendances, null);
                break;
            }
            case PRINT_STUDENTS_IN_GROUP: {
                int groupid = (int) socketPackage.getObject1();
                ArrayList<Student> students = databaseWorker.getStudentsInGroup(groupid);
                responsePackage = new SocketPackage(RequestType.SERVER_ACCEPTED, students, null);
                break;
            }
            case PRINT_ALL_GROUPS_OF_STUDENT: {
                Student student = (Student) socketPackage.getObject1();
                ArrayList<Group> groups = databaseWorker.getAllGroupsOfStudent(student.getIndex());
                responsePackage = new SocketPackage(RequestType.SERVER_ACCEPTED, groups, null);
                break;
            }
            case PRINT_ALL_STUDENT_ATTENDANCES: {
                Student student = (Student) socketPackage.getObject1();
                int classTimeId = (int) socketPackage.getObject2();
                ArrayList<Attendance> attendances = databaseWorker.getStudentAttendances(student.getIndex(), classTimeId);
                responsePackage = new SocketPackage(RequestType.SERVER_ACCEPTED, attendances, null);
                break;
            }
            case PRINT_ALL_CLASS_TIMES_OF_STUDENT: {
                Student student = (Student) socketPackage.getObject1();
                ArrayList<ClassTime> classTimes = databaseWorker.getAllClassTimesOfStudent(student.getIndex());
                responsePackage = new SocketPackage(RequestType.SERVER_ACCEPTED, classTimes, null);
                break;
            }
            case PRINT_ALL_CLASS_TIMES_OF_GROUP: {
                int groupid = (int) socketPackage.getObject1();
                ArrayList<ClassTime> classTimes = databaseWorker.getAllClassTimesOfGroup(groupid);
                responsePackage = new SocketPackage(RequestType.SERVER_ACCEPTED, classTimes, null);
                break;
            }
            default: {
                logger.log(System.Logger.Level.ERROR, "Server processPackage: Invalid request type");
                responsePackage = new SocketPackage(RequestType.SERVER_REJECTED, null, null);
                break;
            }
        }
        return responsePackage;
    }
}
