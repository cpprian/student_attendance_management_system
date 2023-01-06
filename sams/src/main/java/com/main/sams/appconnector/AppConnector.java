package com.main.sams.appconnector;

import com.main.sams.server.client.Client;
import com.main.sams.server.server.RequestType;
import com.main.sams.server.server.SocketPackage;
import com.main.sams.student.Attendance;
import com.main.sams.student.ClassTime;
import com.main.sams.student.Group;
import com.main.sams.student.Student;

import java.util.ArrayList;

public class AppConnector {
    private static AppConnector instance = null;
    private Client client = null;

    public AppConnector(String hostname) {
        client = Client.getInstance(hostname);
    }

    private static AppConnector getInstance(String hostname) {
        if (instance == null) {
            instance = new AppConnector(hostname);
        }
        return instance;
    }

    public static AppConnector run(String hostname) {
        return getInstance(hostname);
    }

    public void stop() {
        client.cleanUpClient();
    }

    public void addStudent(String name, String surname, int index) {
        Student student = new Student(name, surname, index);
        client.sendRequest(new SocketPackage(RequestType.ADD_STUDENT, student, null));
    }

    public void deleteStudent(int index) {
        Student student = new Student(null, null, index);
        client.sendRequest(new SocketPackage(RequestType.DELETE_STUDENT, student, null));
    }

    public void addGroup(String name, int year) {
        Group group = new Group(name, year);
        client.sendRequest(new SocketPackage(RequestType.ADD_GROUP, group, null));
    }

    public void deleteGroup(String name, int year) {
        Group group = new Group(name, year);
        client.sendRequest(new SocketPackage(RequestType.DELETE_GROUP, group, null));
    }

    public void addStudentToGroup(int index, int groupID) {
        Student student = new Student(null, null, index);
        client.sendRequest(new SocketPackage(RequestType.ADD_STUDENT_TO_GROUP, student, groupID));
    }

    public void deleteStudentFromGroup(int index, int groupID) {
        Student student = new Student(null, null, index);
        client.sendRequest(new SocketPackage(RequestType.DELETE_STUDENT_FROM_GROUP, student, groupID));
    }

    public void addClassTime(String name, int durationInMinutes, String classDate, String startTime, String endTime, String location, String description) {
        ClassTime classTime = new ClassTime(name, durationInMinutes, classDate, startTime, endTime, location, description);
        client.sendRequest(new SocketPackage(RequestType.ADD_CLASS_TIME, classTime, null));
    }

    public void addAttendance(int index, int classTimeID) {
        Student student = new Student(null, null, index);
        client.sendRequest(new SocketPackage(RequestType.ADD_ATTENDANCE, student, classTimeID));
    }

    public ArrayList<Student> getStudents() {
        client.sendRequest(new SocketPackage(RequestType.PRINT_ALL_STUDENTS, null, null));
        return (ArrayList<Student>) client.receiveResponse().getObject1();
    }

    public ArrayList<Group> getGroups() {
        client.sendRequest(new SocketPackage(RequestType.PRINT_ALL_GROUPS, null, null));
        return (ArrayList<Group>) client.receiveResponse().getObject1();
    }

    public ArrayList<ClassTime> getClassTimes() {
        client.sendRequest(new SocketPackage(RequestType.PRINT_ALL_CLASS_TIMES, null, null));
        return (ArrayList<ClassTime>) client.receiveResponse().getObject1();
    }

    public ArrayList<Attendance> getAttendances() {
        client.sendRequest(new SocketPackage(RequestType.PRINT_ALL_ATTENDANCES, null, null));
        return (ArrayList<Attendance>) client.receiveResponse().getObject1();
    }

    public ArrayList<Student> getStudentsInGroup(int groupID) {
        client.sendRequest(new SocketPackage(RequestType.PRINT_STUDENTS_IN_GROUP, null, groupID));
        return (ArrayList<Student>) client.receiveResponse().getObject1();
    }

    public ArrayList<Group> getGroupsOfStudent(int index) {
        Student student = new Student(null, null, index);
        client.sendRequest(new SocketPackage(RequestType.PRINT_ALL_GROUPS_OF_STUDENT, student, null));
        return (ArrayList<Group>) client.receiveResponse().getObject1();
    }

    public ArrayList<Attendance> getAttendancesOfStudent(int index) {
        Student student = new Student(null, null, index);
        client.sendRequest(new SocketPackage(RequestType.PRINT_ALL_STUDENT_ATTENDANCES, student, null));
        return (ArrayList<Attendance>) client.receiveResponse().getObject1();
    }

    public ArrayList<Attendance> getClassTimesOfStudent(int index) {
        Student student = new Student(null, null, index);
        client.sendRequest(new SocketPackage(RequestType.PRINT_ALL_CLASS_TIMES_OF_STUDENT, student, null));
        return (ArrayList<Attendance>) client.receiveResponse().getObject1();
    }

    public ArrayList<ClassTime> getClassTimesOfGroup(int groupID) {
        client.sendRequest(new SocketPackage(RequestType.PRINT_ALL_CLASS_TIMES_OF_GROUP, null, groupID));
        return (ArrayList<ClassTime>) client.receiveResponse().getObject1();
    }

    public ArrayList<Attendance> getAttendancesOfGroup(int groupID) {
        client.sendRequest(new SocketPackage(RequestType.PRINT_GROUP_ATTENDANCE, null, groupID));
        return (ArrayList<Attendance>) client.receiveResponse().getObject1();
    }
}
