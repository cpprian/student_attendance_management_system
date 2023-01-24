package com.main.sams.appconnector;

import com.main.sams.server.client.Client;
import com.main.sams.server.server.RequestType;
import com.main.sams.server.server.SocketPackage;
import com.main.sams.student.Attendance;
import com.main.sams.student.ClassTime;
import com.main.sams.student.Group;
import com.main.sams.student.StudentPackage;

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
        StudentPackage studentPackage = new StudentPackage(name, surname, index);
        client.sendRequest(new SocketPackage(RequestType.ADD_STUDENT, studentPackage, null));
    }

    public void deleteStudent(int index) {
        StudentPackage studentPackage = new StudentPackage(null, null, index);
        client.sendRequest(new SocketPackage(RequestType.DELETE_STUDENT, studentPackage, null));
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
        StudentPackage studentPackage = new StudentPackage(null, null, index);
        client.sendRequest(new SocketPackage(RequestType.ADD_STUDENT_TO_GROUP, studentPackage, groupID));
    }

    public void deleteStudentFromGroup(int index, int groupID) {
        StudentPackage studentPackage = new StudentPackage(null, null, index);
        client.sendRequest(new SocketPackage(RequestType.DELETE_STUDENT_FROM_GROUP, studentPackage, groupID));
    }

    public void addClassTime(String name, int durationInMinutes, String classDate, String startTime, String endTime, String location, String description) {
        ClassTime classTime = new ClassTime(name, durationInMinutes, classDate, startTime, endTime, location, description);
        client.sendRequest(new SocketPackage(RequestType.ADD_CLASS_TIME, classTime, null));
    }

    public void addAttendance(int index, int classTimeID) {
        StudentPackage studentPackage = new StudentPackage(null, null, index);
        client.sendRequest(new SocketPackage(RequestType.ADD_ATTENDANCE, studentPackage, classTimeID));
    }

    public ArrayList<StudentPackage> getStudents() {
        client.sendRequest(new SocketPackage(RequestType.PRINT_ALL_STUDENTS, null, null));
        return (ArrayList<StudentPackage>) client.receiveResponse().getObject1();
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

    public ArrayList<StudentPackage> getStudentsInGroup(int groupID) {
        client.sendRequest(new SocketPackage(RequestType.PRINT_STUDENTS_IN_GROUP, null, groupID));
        return (ArrayList<StudentPackage>) client.receiveResponse().getObject1();
    }

    public ArrayList<Group> getGroupsOfStudent(int index) {
        StudentPackage studentPackage = new StudentPackage(null, null, index);
        client.sendRequest(new SocketPackage(RequestType.PRINT_ALL_GROUPS_OF_STUDENT, studentPackage, null));
        return (ArrayList<Group>) client.receiveResponse().getObject1();
    }

    public ArrayList<Attendance> getAttendancesOfStudent(int index) {
        StudentPackage studentPackage = new StudentPackage(null, null, index);
        client.sendRequest(new SocketPackage(RequestType.PRINT_ALL_STUDENT_ATTENDANCES, studentPackage, null));
        return (ArrayList<Attendance>) client.receiveResponse().getObject1();
    }

    public ArrayList<Attendance> getClassTimesOfStudent(int index) {
        StudentPackage studentPackage = new StudentPackage(null, null, index);
        client.sendRequest(new SocketPackage(RequestType.PRINT_ALL_CLASS_TIMES_OF_STUDENT, studentPackage, null));
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
