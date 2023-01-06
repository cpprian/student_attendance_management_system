package com.main.sams.server.database;

public class CreateTableStatement {
    public static String createStudentGroupTable() {
        return """
CREATE TABLE IF NOT EXISTS studentgroup (
    groupid     INT NOT NULL AUTO_INCREMENT,
    groupName   VARCHAR(25) NOT NULL,
    groupYear   INT NOT NULL,
    studentID   INT NOT NULL,
    PRIMARY KEY(groupid),
    FOREIGN KEY (studentid) REFERENCES student(studentid) ON DELETE CASCADE ON UPDATE CASCADE
);
                """;
    }

    public static String createStudentTable() {
        return """
CREATE TABLE IF NOT EXISTS student (
    studentid       INT NOT NULL AUTO_INCREMENT,
    studentname     VARCHAR(25) NOT NULL,
    studentsurname  VARCHAR(25) NOT NULL,
    studentnumber   INT NOT NULL,
    PRIMARY KEY (studentid)
);
                """;
    }

    public static String createAttendanceTable() {
        return """
CREATE TABLE IF NOT EXISTS attendance (
    attendanceid    INT NOT NULL AUTO_INCREMENT,
    studentid       INT NOT NULL,
    attendancetype  INT NOT NULL,
    classtimeid     INT NOT NULL,
    PRIMARY KEY (attendanceid),
    FOREIGN KEY (studentid) REFERENCES student(studentid) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (classtimeid) REFERENCES classtime(classtimeid) ON DELETE CASCADE ON UPDATE CASCADE
);
                """;
    }

    public static String createClasstimeTable() {
        return """
CREATE TABLE IF NOT EXISTS classtime (
    classtimeid         INT NOT NULL AUTO_INCREMENT,
    classtimename       VARCHAR(255) NOT NULL,
    durationinminutes   INT NOT NULL,
    classtimedate       VARCHAR(50) NOT NULL,
    starttime           VARCHAR(25) NOT NULL,
    endtime             VARCHAR(25) NOT NULL,
    location            VARCHAR(255) NOT NULL,
    description         VARCHAR(255) NOT NULL,
    PRIMARY KEY (classtimeid)
);
                """;
    }
}
