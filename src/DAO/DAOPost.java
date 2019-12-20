package DAO;

import DB.Models.Post;
import DB.Models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOPost {
    private static Connection connection = ConnectionProvider.getConnection();

    public void delete(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM musicweb.posts WHERE id = " + id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Exception during delete Track");
            throw new IllegalArgumentException();
        }

    }

    public Post getPostById(int id) {
        try {
            DAOUser daoUser = new DAOUser();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM musicweb.posts WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User author = daoUser.getUserById(resultSet.getInt("author_id"));
                return new Post(
                    resultSet.getInt("id"),
                    resultSet.getString("text"),
                    resultSet.getString("published_date"),
                    resultSet.getString("slug"),
                    resultSet.getString("title"),
                    resultSet.getInt("likes"),
                    author
                );
            }
        } catch (SQLException e) {
            System.out.println(String.format("SQException: Can not fetch track info with id '%d'", id));
            throw new IllegalArgumentException();
        }
        return null;
    }

    public void savePost(Post post) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO musicweb.posts (title, slug, text, published_date, author_id) VALUES (?, ?, ?, ?, ?) ");
            statement.setString(1, post.title);
            statement.setString(2, post.slug);
            statement.setString(3, post.text);
            statement.setString(4, post.published_date);
            statement.setInt(4, post.author.id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Exception during saveUser");
            throw new IllegalArgumentException();
        }
    }

    public List<Post> getPostsOfUser(User user) {
        try {
            DAOUser daoUser = new DAOUser();
            List<Post> posts = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM musicweb.posts WHERE author_id = ?");
            statement.setInt(1, user.id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                posts.add(new Post(
                    resultSet.getInt("id"),
                    resultSet.getString("text"),
                    resultSet.getString("published_date"),
                    resultSet.getString("slug"),
                    resultSet.getString("title"),
                    resultSet.getInt("likes"),
                    user
                ));
            }
            return posts;
        } catch (SQLException e) {
            System.out.println(String.format("SQException: Can not fetch reviews info with user id '%d'", user.id));
            throw new IllegalArgumentException();
        }
    }
}