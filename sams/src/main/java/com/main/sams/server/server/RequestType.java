package com.main.sams.server.server;

public enum RequestType {
    SERVER_ACCEPTED, // only for server
    SERVER_REJECTED, // only for server
    ADD_STUDENT,
    DELETE_STUDENT,
    ADD_GROUP,
    DELETE_GROUP,
    ADD_STUDENT_TO_GROUP,
    DELETE_STUDENT_FROM_GROUP,
    ADD_CLASS_TIME,
    ADD_ATTENDANCE,
    PRINT_GROUP_ATTENDANCE,
}
