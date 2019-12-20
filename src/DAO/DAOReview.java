package DAO;

import DB.Models.Review;
import DB.Models.Track;
import DB.Models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOReview {
    private static Connection connection = ConnectionProvider.getConnection();

    public void delete(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM musicweb.review WHERE id = " + id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Exception during delete Comment");
            throw new IllegalArgumentException();
        }

    }

    public DB.Models.Review getReviewById(int id) {
        try {
            DAOUser daoUser = new DAOUser();
            DAOTrack daoTrack = new DAOTrack();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM musicweb.review WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User author = daoUser.getUserById(resultSet.getInt("author_id"));
                Track track = daoTrack.getTrackById(resultSet.getInt("track_id"));
                return new DB.Models.Review(
                    resultSet.getInt("id"),
                    resultSet.getString("text"),
                    resultSet.getString("published_date"),
                    resultSet.getInt("likes"),
                    track,
                    author
                );
            }
        } catch (SQLException e) {
            System.out.println(String.format("SQException: Can not fetch track info with id '%d'", id));
            throw new IllegalArgumentException();
        }
        return null;
    }

    public void saveReview(DB.Models.Review review) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO musicweb.review (text, track_id, author_id) VALUES (?, ?, ?) ");
            statement.setString(1, review.text);
            statement.setInt(2, review.track.id);
            statement.setInt(3, review.author.id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Exception during saveUser");
            throw new IllegalArgumentException();
        }
    }

    public List<Review> getReviewsOfUser(User user) {
        try {
            DAOTrack daoTrack = new DAOTrack();
            List<Review> reviews = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM musicweb.review WHERE author_id = ?");
            statement.setInt(1, user.id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Track track = daoTrack.getTrackById(resultSet.getInt("track_id"));
                reviews.add(new DB.Models.Review(
                    resultSet.getInt("id"),
                    resultSet.getString("text"),
                    resultSet.getString("published_date"),
                    resultSet.getInt("likes"),
                    track,
                    user
                ));
            }
            return reviews;
        } catch (SQLException e) {
            System.out.println(String.format("SQException: Can not fetch reviews info with user id '%d'", user.id));
            throw new IllegalArgumentException();
        }
    }
}