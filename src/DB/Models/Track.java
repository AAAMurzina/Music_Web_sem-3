package DB.Models;

import DB.DBConnection;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;

public class Track {

    public int id;
    public String name;
    public String lyrics;
    public String release_date;
    public int duration;
    public List<User> authors;
    public String cover_filename;
    public String audio_filename;

    private static Connection con = DBConnection.getConnection();

    public Track(int id, String name, String lyrics, String release_date,
                 int duration, List<User> authors, String cover_filename, String audio_filename) {
        this.id = id;
        this.name = name;
        this.lyrics = lyrics;
        this.release_date = release_date;
        this.duration = duration;
        this.authors = authors;
        this.cover_filename = cover_filename;
        this.audio_filename = audio_filename;
    }

    public Track(String name, String lyrics, String release_date,
                 int duration, List<User> authors, String audio_filename) {
        this(-1, name, lyrics, release_date, duration, authors, null, audio_filename);
    }

    public Track(String name, String lyrics, String release_date,
                 int duration, List<User> authors, String cover_filename, String audio_filename) {
        this(-1, name, lyrics, release_date, duration, authors, cover_filename, audio_filename);
    }
}
