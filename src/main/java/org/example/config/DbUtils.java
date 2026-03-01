package org.example.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtils {

    private final ConfigLoader configLoader = new ConfigLoader();

    public Connection getConnection() throws SQLException {

        String url = configLoader.get("db.url");
        String user = configLoader.get("db.user");
        String password = configLoader.get("db.password");


        return DriverManager.getConnection(url, user, password);
    }
}
