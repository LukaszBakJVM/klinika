package org.example.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbUtils {

    private final String URL = "jdbc:firebirdsql://localhost:3050//firebird/clinic.fdb";

    public  Connection getConnection() throws SQLException {

        Properties props = new Properties();
        props.setProperty("user", "SYSDBA");
        props.setProperty("password", "WH5ny6SjjmWZtvZTJTPm");

        return DriverManager.getConnection(URL, props);
    }
}
