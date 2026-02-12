package com.pedrodelmiro.taskmanager;

import com.pedrodelmiro.taskmanager.db.Database;
import com.sun.net.httpserver.HttpServer;
import org.flywaydb.core.Flyway;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.sql.SQLException;
import java.util.Properties;

/**
 *  http server
 */

public class Main {
    public static void main (String[] args) throws IOException, SQLException {
        Properties props = new Properties();
        try(InputStream is =
                Main.class.getClassLoader().getResourceAsStream("application.properties")){

            if (is == null) {
                throw new RuntimeException("application.properties not found");
            }
            props.load(is);
        }

        int port = Integer.parseInt(props.getProperty("server.port"));
        String host = props.getProperty("server.host");
        String response = "Hello World";

        InetSocketAddress address = new InetSocketAddress(host, port);
        HttpServer server = HttpServer.create(address, 0);

        server.createContext("/", (exchange->{
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, response.getBytes().length);
            exchange.getResponseBody().write(response.getBytes());
            exchange.close();
        }));

        try {

            Flyway.configure()
                    .dataSource(Database.getDataSource())
                    .load()
                    .migrate();

            System.out.println("Migrations OK");

        } catch (Exception e) {
            throw new RuntimeException("Migration error", e);
        }



        server.start();
        System.out.println("server starting on http://" + host +":" + port + "/");
    }
}
