package com.pedrodelmiro.taskmanager.db;

import com.pedrodelmiro.taskmanager.Main;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Database {

    public Connection connect() throws SQLException {
        Properties props = new Properties();
        try(InputStream is =
                    Main.class.getClassLoader().getResourceAsStream("application.properties")){

            if (is == null) {
                throw new RuntimeException("application.properties not found");
            }
            props.load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String databaseUrl = props.getProperty("database.url");
        String databaseRole = props.getProperty("database.role");
        String databasePassword = props.getProperty("database.password");
        return DriverManager.getConnection(
                databaseUrl,
                databaseRole,
                databasePassword
        );
    }
}
