package org.example.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbUtils {

    private static final String URL = "jdbc:postgresql://localhost:5432/clinic";

    public static Connection getConnection() throws SQLException {

        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "pass");

        return DriverManager.getConnection(URL, props);
    }
}
