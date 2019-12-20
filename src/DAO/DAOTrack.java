package DAO;

import DB.Models.Track;
import DB.Models.User;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOTrack {
    private static Connection connection = ConnectionProvider.getConnection();

    public void delete(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM musicweb.track WHERE id = " + id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Exception during delete Track");
            throw new IllegalArgumentException();
        }

    }

    public DB.Models.Track getTrackById(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM musicweb.users_tracks WHERE track_id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            DAOUser daoUser = new DAOUser();
            List<User> authors = new ArrayList<>();
            if (resultSet.next()) {
                authors.add(daoUser.getUserById(resultSet.getInt("author_id")));
            }
            statement = connection.prepareStatement("SELECT * FROM musicweb.track WHERE id = ?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new DB.Models.Track(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("lyrics"),
                        resultSet.getString("release_date"),
                        resultSet.getInt("duration"),
                        authors,
                        resultSet.getString("cover_filename"),
                        resultSet.getString("audio_filename")
                );
            }
        } catch (SQLException e) {
            System.out.println(String.format("SQException: Can not fetch track info with id '%d'", id));
            throw new IllegalArgumentException();
        }
        return null;
    }

    public void saveTrack(DB.Models.Track track) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO musicweb.track  (name, lyrics, release_date, duration, cover_filename, audio_filename) VALUES (?, ?, ?, ?, ?, ?);");
            statement.setString(1, track.name);
            statement.setString(2, track.lyrics);
            statement.setString(3, track.release_date);
            statement.setInt(4, track.duration);
            statement.setString(5, track.cover_filename);
            statement.setString(6, track.audio_filename);
            statement.executeUpdate();
            statement = connection.prepareStatement("SELECT id FROM musicweb.track WHERE name = ? and release_date = ?;");
            statement.setString(1, track.name);
            statement.setString(2, track.release_date);
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                track.id = rs.getInt("id");
            }
            for(User author: track.authors) {
                statement = connection.prepareStatement("INSERT INTO musicweb.users_tracks (user_id, track_id) VALUES (?, ?);");
                statement.setInt(1, author.id);
                statement.setInt(2, track.id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    public List<Track> getTracksOfUser(User user) {
        try {
            List<Track> trackList = new ArrayList<>();
            DAOTrack daoTrack = new DAOTrack();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM musicweb.users_tracks  WHERE user_id = ?");
            statement.setInt(1, user.id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                trackList.add(daoTrack.getTrackById(resultSet.getInt("track_id")));
            }
            return trackList;
        } catch (SQLException e) {
            System.out.println("Exception during getTracks");
            throw new IllegalArgumentException();
        }
    }

}