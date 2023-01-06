package com.main.sams.server.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseWorker {
    private static DatabaseWorker instance = null;
    private static final String DATABASE_NAME = "sams";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String USERNAME = "sams";
    private static final String PASSWORD = "123";

    private DatabaseWorker(String hostname, String port) {
        try {
            Class.forName(DRIVER);
        } catch (Exception e) {
            System.out.println("DatabaseWorker constructor: Failed to load driver");
        }
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://" + hostname + ":" + port + "/" + DATABASE_NAME, USERNAME, PASSWORD);
            Statement statement = conn.createStatement()) {
            statement.execute("""
                    CREATE TABLE IF NOT EXISTS classtime (
                        classtimeid INT NOT NULL AUTO_INCREMENT,
                        classtimename VARCHAR(255) NOT NULL,
                        durationinminutes INT NOT NULL,
                        starttime VARCHAR(255) NOT NULL,
                        endtime VARCHAR(255) NOT NULL,
                        location VARCHAR(255) NOT NULL,
                        description VARCHAR(255) NOT NULL,
                        PRIMARY KEY (classtimeid)
                    );
                    """);

            System.out.println("Database is OK");
        } catch (SQLException e) {
            System.out.println("DatabaseWorker constructor: Failed to create database, " + e.getMessage());
            System.exit(1);
        }
    }

    // TODO: add all methods from server
    public static DatabaseWorker getInstance(String hostname, String port) {
        if (instance == null) {
            instance = new DatabaseWorker(hostname, port);
        }
        return instance;
    }
}
