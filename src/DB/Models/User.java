package DB.Models;

import DAO.DAOComment;
import DAO.DAOPost;
import DAO.DAOReview;
import DAO.DAOTrack;
import DB.DBConnection;

import java.io.Serializable;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;


public class User implements Serializable {
    public int id; // pk
    public String email;
    public String name;
    public String login;

    public String password;

    public String registerDate;

    public DBConnection dataBase;

    private static Connection con = DBConnection.getConnection();

    public User(int id, String email, String name, String login, String password, String registerDate) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.login = login;
        this.password = password;
        this.registerDate = registerDate;
    }

    public User(String email, String name, String login, String password) {
        this(-1, email, name, login, password, null);
    }

    public User(String login, String email, String password) {
        this(email, null, login, password);
    }


    public List<Review> getReviews() {
        return new DAOReview().getReviewsOfUser(this);
    }

    public List<Track> getTracks() {
        return new DAOTrack().getTracksOfUser(this);
    }

    public List<Post> getPosts() {
        return new DAOPost().getPostsOfUser(this);
    }

    public List<Comment> getComments() {
        return new DAOComment().getCommentsOfUser(this);
    }
}