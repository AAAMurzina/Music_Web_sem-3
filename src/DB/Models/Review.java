package DB.Models;

import DB.DBConnection;

import java.sql.Connection;
import java.time.LocalDateTime;

public class Review {

    public int id;
    public String text;
    public String published_date;
    public int likes;
    public Track track;
    public User author;

    private static Connection con = DBConnection.getConnection();

    public Review(int id, String text, String published_date, int likes, Track track, User author) {
        this.id = id;
        this.text = text;
        this.published_date = published_date;
        this.likes = likes;
        this.track = track;
        this.author = author;
    }

    public Review(String text, Track track, User author) {
        this(-1, text, String.valueOf(LocalDateTime.now()), 0, track, author);
    }
}
