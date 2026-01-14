package com.pedrodelmiro.taskmanager.db.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SchemaInitializer {
    private SchemaInitializer(){}

    public static void run(Connection connection) throws SQLException {
        createTables(connection);
    }
    private static void createTables(Connection connection) throws SQLException {
        // CREATE TABLE statements here
    }
}
