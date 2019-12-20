package DB;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnection {
    private static Connection con;
    private String url = "jdbc:mysql://localhost:3306/musicweb";
    private String password = "musicwebadmin";
    private String user = "musicwebadmin";

    public DBConnection() {
        connect();
    }

    private void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return con;
    }

    public PreparedStatement add(String q) {
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = con.prepareStatement("insert into " + q);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return preparedStmt;
    }
}

