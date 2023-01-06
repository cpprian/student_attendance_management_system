package com.main.sams.server.database;

public class DatabaseWorker {
    private static DatabaseWorker instance = null;
    private static final String DATABASE_NAME = "sams.db";
    private static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\joshu\\IdeaProjects\\sams\\src\\main\\java\\com\\main\\sams\\server\\database\\" + DATABASE_NAME;

    private DatabaseWorker() {
        // connect to database from any ip address
    }

    // TODO: add all methods from server
    public static DatabaseWorker getInstance() {
        if (instance == null) {
            instance = new DatabaseWorker();
        }
        return instance;
    }


    // TODO: add listener 
}
