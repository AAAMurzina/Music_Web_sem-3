package DAO;

import Utils.Validators;
import com.mysql.cj.exceptions.WrongArgumentException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOUser {
    private static Connection connection = ConnectionProvider.getConnection();

    public void delete(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM musicweb.user WHERE id = " + id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Exception during delete User");
            throw new IllegalArgumentException();
        }

    }

    public DB.Models.User getUserByEmail(String email) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM musicweb.user  WHERE email = ?");
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new DB.Models.User(resultSet.getInt("id"), resultSet.getString("email"), resultSet.getString("name"), resultSet.getString("login"), resultSet.getString("password"), resultSet.getString("register_date"));
            }
        } catch (SQLException e) {
            System.out.println("Exception during get user by email");
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
        return null;
    }

    public DB.Models.User getUserById(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM musicweb.user WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new DB.Models.User(resultSet.getInt("id"), resultSet.getString("email"), resultSet.getString("name"), resultSet.getString("login"), resultSet.getString("password"), resultSet.getString("register_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
        return null;
    }

    public String getUsernameById(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM musicweb.user  WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                DB.Models.User user = new DB.Models.User(resultSet.getInt("id"), resultSet.getString("email"), resultSet.getString("name"), resultSet.getString("login"), resultSet.getString("password"), resultSet.getString("registerDate"));
                return user.name;
            }
        } catch (SQLException e) {
            System.out.println("Exception during get user by email");
            throw new IllegalArgumentException();
        }
        return null;
    }

    public void saveUser(DB.Models.User user) {
        try {
            if(Validators.validateEmail(user.email)) {
                if(Validators.validateLogin(user.login)) {
                    PreparedStatement statement = connection.prepareStatement("INSERT INTO musicweb.user  (login, name, email, password) VALUES (?, ?, ?, ?) ");
                    statement.setString(1, user.login);
                    statement.setString(2, user.name);
                    statement.setString(3, user.email);
                    statement.setString(4, user.password);
                    statement.executeUpdate();
                } else {
                    throw new WrongArgumentException("Login contains unsupported symbols");
                }
            } else {
                throw new WrongArgumentException("E-mail is in incorrect form");
            }
        } catch (SQLException e) {
            System.out.println("Exception during saveUser");
            throw new IllegalArgumentException();
        }
    }

    public boolean existEmail(String email) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM musicweb.user  WHERE email = ?");
            statement.setString(1, email);
            if (statement.executeQuery().next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Exception during email exist");
            throw new IllegalArgumentException();
        }
        return false;
    }

    public boolean userIsExist(String email, String password) {
        try {
            System.out.println(email);
            System.out.println(password);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM musicweb.user  WHERE email = ? AND password = ?");
            statement.setString(1, email);
            statement.setString(2, password);
            if (statement.executeQuery().next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Exception during userIsExist");
            throw new IllegalArgumentException();
        }
        return false;
    }

    public boolean existLogin(String login) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM musicweb.user  WHERE login = ?");
            statement.setString(1, login);
            if (statement.executeQuery().next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Exception during login exist");
            throw new IllegalArgumentException();
        }
        return false;
    }
}