package database.dbUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {
    private static final String USERNAME = "dbUser";
    private static final String PASSWORD = "dbPassword";
    private static final String CONNECTION = "jdbc:msql://localhost/login";
    private static final String SQL_CONNECTION = "jdbc:sqlite:src/main/java/schoolSystem.sqlite";


    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            System.out.println(DriverManager.getConnection(SQL_CONNECTION));
            return DriverManager.getConnection(SQL_CONNECTION);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
