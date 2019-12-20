package DB.Models;

import DB.DBConnection;

import java.sql.Connection;
import java.time.LocalDateTime;

public class Post {

    public int id;
    public String text;
    public String slug;
    public String title;
    public String published_date;
    public int likes;
    public User author;

    private static Connection con = DBConnection.getConnection();

    public Post(int id, String text, String published_date, String slug, String title, int likes, User author) {
        this.id = id;
        this.text = text;
        this.slug = slug;
        this.title = title;
        this.published_date = published_date;
        this.likes = likes;
        this.author = author;
    }

    public Post(String text, String title, String slug, User author) {
        this(-1, text, String.valueOf(LocalDateTime.now()), slug, title, 0, author);
    }
}
