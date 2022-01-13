package jm.task.core.jdbc.util;

import lombok.extern.java.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Log
public class Util {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/jdbc_user";

    static final String USER = "root";
    static final String PASS = "Whitegoldukhta1488";

    static Connection connection = null;

    public static Connection connection() {
        try {

            log.info("Creating connection to database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}

