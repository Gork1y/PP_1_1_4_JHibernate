package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private final static String USER_NAME = "root";
    private final static String PASSWORD = "11111";
    private final static String CONNECTION_URL = "jdbc:mysql://localhost:3306";
    private static Connection connection;




    public Util() {
        try (Connection connection = DriverManager.getConnection(CONNECTION_URL, USER_NAME, PASSWORD)) {
            System.out.println("Success connection");
        } catch (SQLException e) {
            System.err.println("FiGA S MASLOM TEBE A NE CONNECTION");
            ;
        }
    }
    public static Connection getConnection() {
        return connection;
    }

}
