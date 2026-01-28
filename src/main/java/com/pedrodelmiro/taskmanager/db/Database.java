package com.pedrodelmiro.taskmanager.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

public final class Database {

    private static final HikariDataSource dataSource;

    static {
        try {
            Properties props = new Properties();
            try (InputStream is =
                         Database.class.getClassLoader().getResourceAsStream("application.properties")) {

                if (is == null) {
                    throw new RuntimeException("application.properties not found");
                }
                props.load(is);
            }

            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(props.getProperty("database.url"));
            config.setUsername(props.getProperty("database.role"));
            config.setPassword(props.getProperty("database.password"));

            config.setMaximumPoolSize(10);
            config.setMinimumIdle(2);
            config.setPoolName("TaskManagerPool");

            dataSource = new HikariDataSource(config);

        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private Database() {}

    public static DataSource getDataSource() {
        return dataSource;
    }
}
