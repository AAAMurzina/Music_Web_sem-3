package DAO;

import DB.Models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOComment {
    private static Connection connection = ConnectionProvider.getConnection();

    public void delete(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM musicweb.comments WHERE id = " + id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Exception during delete Comment");
            throw new IllegalArgumentException();
        }

    }

    public DB.Models.Comment getCommentById(int id) {
        try {
            DAOUser daoUser = new DAOUser();
            DAOPost daoPost = new DAOPost();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM musicweb.comments WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User author = daoUser.getUserById(resultSet.getInt("author_id"));
                Post post = daoPost.getPostById(resultSet.getInt("post_id"));
                return new DB.Models.Comment(
                    resultSet.getInt("id"),
                    resultSet.getString("text"),
                    resultSet.getString("published_date"),
                    resultSet.getInt("likes"),
                    post,
                    author
                );
            }
        } catch (SQLException e) {
            System.out.println(String.format("SQException: Can not fetch track info with id '%d'", id));
            throw new IllegalArgumentException();
        }
        return null;
    }

    public void saveComment(DB.Models.Comment comment) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO musicweb.comments (text, published_date, post_id, author_id) VALUES (?, ?, ?, ?) ");
            statement.setString(1, comment.text);
            statement.setString(2, comment.published_date);
            statement.setInt(3, comment.post.id);
            statement.setInt(4, comment.author.id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Exception during saveUser");
            throw new IllegalArgumentException();
        }
    }

    public List<Comment> getCommentsOfUser(User user) {
        try {
            DAOPost daoPost = new DAOPost();
            List<Comment> comments = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM musicweb.comments WHERE author_id = ?");
            statement.setInt(1, user.id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Post post = daoPost.getPostById(resultSet.getInt("post_id"));
                comments.add(new DB.Models.Comment(
                    resultSet.getInt("id"),
                    resultSet.getString("text"),
                    resultSet.getString("published_date"),
                    resultSet.getInt("likes"),
                    post,
                    user
                ));
            }
            return comments;
        } catch (SQLException e) {
            System.out.println(String.format("SQException: Can not fetch reviews info with user id '%d'", user.id));
            throw new IllegalArgumentException();
        }
    }
}