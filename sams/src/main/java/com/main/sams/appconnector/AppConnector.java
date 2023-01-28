package com.main.sams.appconnector;

import com.google.gson.Gson;
import com.main.sams.server.client.Client;
import com.main.sams.server.server.RequestType;
import com.main.sams.server.server.SocketPackage;
import com.main.sams.student.*;
import org.controlsfx.control.spreadsheet.SpreadsheetCell;

import java.util.ArrayList;

public class AppConnector {
    private static AppConnector instance = null;
    private Client client = null;
    private Gson gson = null;

    public AppConnector(String hostname) {
        client = Client.getInstance(hostname);
        gson = new Gson();
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
        RequestType requestType = RequestType.ADD_STUDENT;
        ArrayList<MyObject> studentPackages = new ArrayList<>();
        studentPackages.add(studentPackage);
        SocketPackage socketPackage = new SocketPackage(requestType, studentPackages, null, 0, 0);
        String json = gson.toJson(socketPackage);
        client.sendRequest(json);
    }

    public void deleteStudent(int index) {
        StudentPackage studentPackage = new StudentPackage(null, null, index);
        RequestType requestType = RequestType.DELETE_STUDENT;
        ArrayList<MyObject> studentPackages = new ArrayList<>();
        studentPackages.add(studentPackage);
        SocketPackage socketPackage = new SocketPackage(requestType, studentPackages, null, 0, 0);
        String json = gson.toJson(socketPackage);
        client.sendRequest(json);
    }

    public void addGroup(String name, int year) {
        Group group = new Group(name, year);
        RequestType requestType = RequestType.ADD_GROUP;
        ArrayList<MyObject> groups = new ArrayList<>();
        groups.add(group);
        SocketPackage socketPackage = new SocketPackage(requestType, groups, null, 0, 0);
        String json = gson.toJson(socketPackage);
        client.sendRequest(json);
    }

    public void deleteGroup(String name, int year) {
        Group group = new Group(name, year);
        RequestType requestType = RequestType.DELETE_GROUP;
        ArrayList<MyObject> groups = new ArrayList<>();
        groups.add(group);
        SocketPackage socketPackage = new SocketPackage(requestType, groups, null, 0, 0);
        String json = gson.toJson(socketPackage);
        client.sendRequest(json);
    }

    public void addStudentToGroup(int index, int groupID) {
        StudentPackage studentPackage = new StudentPackage(null, null, index);
        RequestType requestType = RequestType.ADD_STUDENT_TO_GROUP;
        ArrayList<MyObject> studentPackages = new ArrayList<>();
        studentPackages.add(studentPackage);
        SocketPackage socketPackage = new SocketPackage(requestType, studentPackages, null, groupID, 0);
        String json = gson.toJson(socketPackage);
        client.sendRequest(json);
    }

    public void deleteStudentFromGroup(int index, int groupID) {
        StudentPackage studentPackage = new StudentPackage(null, null, index);
        RequestType requestType = RequestType.DELETE_STUDENT_FROM_GROUP;
        ArrayList<MyObject> studentPackages = new ArrayList<>();
        studentPackages.add(studentPackage);
        SocketPackage socketPackage = new SocketPackage(requestType, studentPackages, null, groupID, 0);
        String json = gson.toJson(socketPackage);
        client.sendRequest(json);
    }

    public void addClassTime(String name, int durationInMinutes, String classDate, String startTime, String endTime, String location, String description) {
        ClassTime classTime = new ClassTime(name, durationInMinutes, classDate, startTime, endTime, location, description);
        RequestType requestType = RequestType.ADD_CLASS_TIME;
        ArrayList<MyObject> classTimes = new ArrayList<>();
        classTimes.add(classTime);
        SocketPackage socketPackage = new SocketPackage(requestType, classTimes, null, 0, 0);
        String json = gson.toJson(socketPackage);
        client.sendRequest(json);
    }

    public void addAttendance(int index, int classTimeID) {
        StudentPackage studentPackage = new StudentPackage(null, null, index);
        RequestType requestType = RequestType.ADD_ATTENDANCE;
        ArrayList<MyObject> studentPackages = new ArrayList<>();
        studentPackages.add(studentPackage);
        SocketPackage socketPackage = new SocketPackage(requestType, studentPackages, null, classTimeID, 0);
        String json = gson.toJson(socketPackage);
        client.sendRequest(json);
    }

    public ArrayList<StudentPackage> getStudents() {
        RequestType requestType = RequestType.PRINT_ALL_STUDENTS;
        SocketPackage socketPackage = new SocketPackage(requestType, null, null, 0, 0);
        String json = gson.toJson(socketPackage);
        client.sendRequest(json);
        SocketPackage response = client.receiveResponse();
        ArrayList<StudentPackage> studentPackages = new ArrayList<>();
        for (MyObject myObject : response.getMyObjects1()) {
            studentPackages.add((StudentPackage) myObject);
        }
        return studentPackages;
    }

    public ArrayList<Group> getGroups() {
        RequestType requestType = RequestType.PRINT_ALL_GROUPS;
        SocketPackage socketPackage = new SocketPackage(requestType, null, null, 0, 0);
        String json = gson.toJson(socketPackage);
        client.sendRequest(json);
        SocketPackage response = client.receiveResponse();
        ArrayList<Group> groups = new ArrayList<>();
        for (MyObject myObject : response.getMyObjects1()) {
            groups.add((Group) myObject);
        }
        return groups;
    }

    public ArrayList<ClassTime> getClassTimes() {
        RequestType requestType = RequestType.PRINT_ALL_CLASS_TIMES;
        SocketPackage socketPackage = new SocketPackage(requestType, null, null, 0, 0);
        String json = gson.toJson(socketPackage);
        client.sendRequest(json);
        SocketPackage response = client.receiveResponse();
        ArrayList<ClassTime> classTimes = new ArrayList<>();
        for (MyObject myObject : response.getMyObjects1()) {
            classTimes.add((ClassTime) myObject);
        }
        return classTimes;
    }

    public ArrayList<Attendance> getAttendances() {
        RequestType requestType = RequestType.PRINT_ALL_ATTENDANCES;
        SocketPackage socketPackage = new SocketPackage(requestType, null, null, 0, 0);
        String json = gson.toJson(socketPackage);
        client.sendRequest(json);
        SocketPackage response = client.receiveResponse();
        ArrayList<Attendance> attendances = new ArrayList<>();
        for (MyObject myObject : response.getMyObjects1()) {
            attendances.add((Attendance) myObject);
        }
        return attendances;
    }

    public ArrayList<StudentPackage> getStudentsInGroup(int groupID) {
        RequestType requestType = RequestType.PRINT_STUDENTS_IN_GROUP;
        SocketPackage socketPackage = new SocketPackage(requestType, null, null, groupID, 0);
        String json = gson.toJson(socketPackage);
        client.sendRequest(json);
        SocketPackage response = client.receiveResponse();
        ArrayList<StudentPackage> studentPackages = new ArrayList<>();
        for (MyObject myObject : response.getMyObjects1()) {
            studentPackages.add((StudentPackage) myObject);
        }
        return studentPackages;
    }

    public ArrayList<Group> getGroupsOfStudent(int index) {
        RequestType requestType = RequestType.PRINT_ALL_GROUPS_OF_STUDENT;
        SocketPackage socketPackage = new SocketPackage(requestType, null, null, index, 0);
        String json = gson.toJson(socketPackage);
        client.sendRequest(json);
        SocketPackage response = client.receiveResponse();
        ArrayList<Group> groups = new ArrayList<>();
        for (MyObject myObject : response.getMyObjects1()) {
            groups.add((Group) myObject);
        }
        return groups;
    }

    public ArrayList<Attendance> getAttendancesOfStudent(int index, int classTimeID) {
        RequestType requestType = RequestType.PRINT_ALL_STUDENT_ATTENDANCES;
        StudentPackage studentPackage = new StudentPackage(null, null, index);
        ArrayList<MyObject> studentPackages = new ArrayList<>();
        studentPackages.add(studentPackage);
        SocketPackage socketPackage = new SocketPackage(requestType, studentPackages, null, classTimeID, 0);
        String json = gson.toJson(socketPackage);
        client.sendRequest(json);
        SocketPackage response = client.receiveResponse();
        ArrayList<Attendance> attendances = new ArrayList<>();
        for (MyObject myObject : response.getMyObjects1()) {
            attendances.add((Attendance) myObject);
        }
        return attendances;
    }

    public ArrayList<Attendance> getClassTimesOfStudent(int index) {
        RequestType requestType = RequestType.PRINT_ALL_CLASS_TIMES_OF_STUDENT;
        SocketPackage socketPackage = new SocketPackage(requestType, null, null, index, 0);
        String json = gson.toJson(socketPackage);
        client.sendRequest(json);
        SocketPackage response = client.receiveResponse();
        ArrayList<Attendance> attendances = new ArrayList<>();
        for (MyObject myObject : response.getMyObjects1()) {
            attendances.add((Attendance) myObject);
        }
        return attendances;
    }

    public ArrayList<ClassTime> getClassTimesOfGroup(int groupID) {
        RequestType requestType = RequestType.PRINT_ALL_CLASS_TIMES_OF_GROUP;
        SocketPackage socketPackage = new SocketPackage(requestType, null, null, groupID, 0);
        String json = gson.toJson(socketPackage);
        client.sendRequest(json);
        SocketPackage response = client.receiveResponse();
        ArrayList<ClassTime> classTimes = new ArrayList<>();
        for (MyObject myObject : response.getMyObjects1()) {
            classTimes.add((ClassTime) myObject);
        }
        return classTimes;
    }

    public ArrayList<Attendance> getAttendancesOfGroup(int groupID, int classTimeID) {
        RequestType requestType = RequestType.PRINT_GROUP_ATTENDANCE;
        SocketPackage socketPackage = new SocketPackage(requestType, null, null, groupID, 0);
        String json = gson.toJson(socketPackage);
        client.sendRequest(json);
        SocketPackage response = client.receiveResponse();
        ArrayList<Attendance> attendances = new ArrayList<>();
        for (MyObject myObject : response.getMyObjects1()) {
            attendances.add((Attendance) myObject);
        }
        return attendances;
    }
}
