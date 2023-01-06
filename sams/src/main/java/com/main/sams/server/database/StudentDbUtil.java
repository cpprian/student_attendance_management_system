package com.main.sams.server.database;

import com.main.sams.student.AttendanceType;

public class StudentDbUtil {

    public static String addStudent(String name, String surname, int studentNumber) {
        return """
                INSERT INTO student (studentname, studentsurname, studentnumber)
                VALUES ('%s', '%s', %d);
                """.formatted(name, surname, studentNumber);
    }

    public static String deleteStudent(int studentNumber) {
        return """
                DELETE FROM student
                WHERE studentnumber = %d;
                """.formatted(studentNumber);
    }

    public static String addGroup(String groupName, int groupYear) {
        return """
                INSERT INTO studentgroup (groupName, groupYear)
                VALUES ('%s', %d);
                """.formatted(groupName, groupYear);
    }

    public static String deleteGroup(int groupID) {
        return """
                DELETE FROM studentgroup
                WHERE groupid = %d;
                """.formatted(groupID);
    }

    public static String addStudentToGroup(int studentNumber, int groupID) {
        return """
                INSERT INTO studentgroup (studentid, groupid)
                VALUES ((SELECT studentid FROM student WHERE studentnumber = %d), %d);
                """.formatted(studentNumber, groupID);
    }

    public static String deleteStudentFromGroup(int studentNumber, int groupID) {
        return """
                DELETE FROM studentgroup
                WHERE studentid = (SELECT studentid FROM student WHERE studentnumber = %d) AND groupid = %d;
                """.formatted(studentNumber, groupID);
    }

    public static String addClassTime(String className, int durationInMinutes, String classTimeDate,
                                      String classTimeStartTime, String classTimeEndTime, String location, String description) {
        return """
                INSERT INTO classtime (classtimename, durationinminutes, classtimedate, classtimestarttime, classtimeendtime, location, description)
                VALUES ('%s', %d, '%s', '%s', '%s', '%s', '%s');
                """.formatted(className, durationInMinutes, classTimeDate, classTimeStartTime, classTimeEndTime, location, description);
    }

    public static String addAttendance(int studentNumber, AttendanceType attendanceType, int classTimeID) {
        return """
                INSERT INTO attendance (studentid, attendancetype, classtimeid)
                VALUES ((SELECT studentid FROM student WHERE studentnumber = %d), %d, %d);
                """.formatted(studentNumber, attendanceType, classTimeID);
    }

    public static String printStudentAttendance(int studentNumber, int classTimeID) {
        return """
                SELECT student.studentname, student.studentsurname, student.studentnumber, attendance.attendancetype, classtime.classtimename, classtime.classtimedate, classtime.classtimestarttime, classtime.classtimeendtime
                FROM student
                INNER JOIN attendance ON student.studentid = attendance.studentid
                INNER JOIN classtime ON attendance.classtimeid = classtime.classtimeid
                WHERE student.studentnumber = %d AND attendance.classtimeid = %d;
                """.formatted(studentNumber, classTimeID);
    }

    public static String printAllStudents() {
        return """
                SELECT studentname, studentsurname, studentnumber
                FROM student;
                """;
    }

    public static String printAllGroups() {
        return """
                SELECT groupname, groupyear
                FROM studentgroup;
                """;
    }

    public static String printAllClassTimes() {
        return """
                SELECT classtimename, durationinminutes, classtimedate, classtimestarttime, classtimeendtime, location, description
                FROM classtime;
                """;
    }

    public static String printAllAttendances() {
        return """
                SELECT student.studentname, student.studentsurname, student.studentnumber, attendance.attendancetype, classtime.classtimename, classtime.classtimedate, classtime.classtimestarttime, classtime.classtimeendtime, classtime.location, classtime.description
                FROM student
                INNER JOIN attendance ON student.studentid = attendance.studentid
                INNER JOIN classtime ON attendance.classtimeid = classtime.classtimeid;
                """;
    }

    public static String printAllStudentsInGroup(int groupID) {
        return """
                SELECT student.studentname, student.studentsurname, student.studentnumber
                FROM student
                INNER JOIN studentgroup ON student.studentid = studentgroup.studentid
                WHERE studentgroup.groupid = %d;
                """.formatted(groupID);
    }

    public static String printAllGroupsOfStudent(int studentNumber) {
        return """
                SELECT studentgroup.groupname, studentgroup.groupyear
                FROM student
                INNER JOIN studentgroup ON student.studentid = studentgroup.studentid
                WHERE student.studentnumber = %d;
                """.formatted(studentNumber);
    }

    public static String printAllClassTimesOfStudent(int studentNumber) {
        return """
                SELECT classtime.classtimename, classtime.durationinminutes, classtime.classtimedate, classtime.classtimestarttime, classtime.classtimeendtime, classtime.location, classtime.description
                FROM student
                INNER JOIN attendance ON student.studentid = attendance.studentid
                INNER JOIN classtime ON attendance.classtimeid = classtime.classtimeid
                WHERE student.studentnumber = %d;
                """.formatted(studentNumber);
    }

    public static String printAllClassTimesOfGroup(int groupID) {
        return """
                SELECT classtime.classtimename, classtime.durationinminutes, classtime.classtimedate, classtime.starttime, classtime.endtime, classtime.location, classtime.description
                FROM student
                INNER JOIN studentgroup ON student.studentid = studentgroup.studentid
                INNER JOIN attendance ON student.studentid = attendance.studentid
                INNER JOIN classtime ON attendance.classtimeid = classtime.classtimeid
                WHERE studentgroup.groupid = %d;
                """.formatted(groupID);
    }
}
