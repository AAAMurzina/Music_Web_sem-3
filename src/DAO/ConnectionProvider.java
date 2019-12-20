package DAO;

import java.sql.*;

public class ConnectionProvider implements Provider {

    private static Connection connection = null;

    static {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(connectionString, login, password);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
