package DAO;

public interface Provider {
    // Here lies the config for the DB connection (it is better to put it in .gitignore)
    final String driverName = "com.mysql.cj.jdbc.Driver";
    final String connectionString =  "jdbc:mysql://localhost:3306/musicweb?useUnicode=true&serverTimezone=UTC";
    final String login = "root";
    final String password = "root";
}