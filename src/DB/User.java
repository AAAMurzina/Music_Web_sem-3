package DB;



import java.io.InputStream;
import java.io.Serializable;
import java.sql.*;


public class User implements Serializable {
    private int id; // pk
    private String email;
    private String name;

    private String password;

    private DBConnection dataBase;
    //    private InputStream photo;
    private static Connection con = DBConnection.getConnection();


    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setDataBase(DBConnection dataBase) {
        this.dataBase = dataBase;
    }


    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }


    public String getPassword() {
        return password;
    }


    public DBConnection getDataBase() {
        return dataBase;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public User(int id, String email, String name, String surname, int age, String password, String photo) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;

    }

    public User(
            String email,
            String name,
            String surname,
            int age,
            String password
    ) {

        this.email = email;
        this.name = name;

        this.password = password;

    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}