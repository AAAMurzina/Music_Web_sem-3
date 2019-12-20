package DB.Models;

import DB.DBConnection;

import java.sql.Connection;
import java.time.LocalDateTime;

public class Comment {

    public int id;
    public String text;
    public String published_date;
    public int likes;
    public Post post;
    public User author;

    private static Connection con = DBConnection.getConnection();

    public Comment(int id, String text, String published_date, int likes, Post post, User author) {
        this.id = id;
        this.text = text;
        this.published_date = published_date;
        this.likes = likes;
        this.post = post;
        this.author = author;
    }

    public Comment(String text, Post post, User author) {
        this(-1, text, String.valueOf(LocalDateTime.now()), 0, post, author);
    }
}
