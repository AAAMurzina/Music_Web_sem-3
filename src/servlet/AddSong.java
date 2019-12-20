package servlet;

import DAO.DAOTrack;
import DAO.DAOUser;
import DB.Models.Track;
import DB.Models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/new_song")
@MultipartConfig
public class AddSong extends HttpServlet {
    @Override
    // Туть мы просто отправялем страничку на полученный GET-request
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("user") != null) {
            req.setAttribute("session", true);
        }
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/newSong.ftl");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String authorsString = req.getParameter("author");
        String text = req.getParameter("lyrics");
        String title = req.getParameter("title");
        String genre = req.getParameter("genre");
        String releaseDate = String.valueOf(LocalDateTime.now());

        Part coverPart = req.getPart("cover");
        String coverExtension = getExtension(coverPart);
        String coverFileName = getNewFileName(title, releaseDate, coverExtension);
        saveFile(coverFileName, coverPart.getInputStream());

        Part audioPart = req.getPart("audio");
        String audioExtension = getExtension(audioPart);
        String audioFileName = getNewFileName(title, releaseDate, audioExtension);
        saveFile(audioFileName, audioPart.getInputStream());

        List<User> authorProfiles = new ArrayList<>();
        String[] authors = authorsString.split(", ");
        DAOUser daoUser = new DAOUser();
        for (String author : authors) {
            System.out.println(Integer.valueOf(author));
            authorProfiles.add(daoUser.getUserById(Integer.valueOf(author)));
        }
        Track track = new Track(title, text, releaseDate, 0, authorProfiles, coverFileName, audioFileName);
        DAOTrack daoTrack = new DAOTrack();
        daoTrack.saveTrack(track);
        resp.sendRedirect("/index");
    }

    private void saveFile(String fileName, InputStream inputStream) throws IOException {
        File file = new File("C:/Users/Alena/Desktop/Music_Web1.0/media/", fileName);
        Files.copy(inputStream, file.toPath());
    }

    private String getNewFileName(String title, String releaseDate, String extension) {
        releaseDate = releaseDate.replace('-', '_')
                                 .replace('.', '_')
                                 .replace(':', '_');
        return title + releaseDate + '.' + extension;
    }

    private String getExtension(Part part) {
        String coverFileNameOriginal = Paths.get(getSubmittedFileName(part)).getFileName().toString();
        String[] coverFileNameSplitted = coverFileNameOriginal.split("\\.");
        return coverFileNameSplitted[coverFileNameSplitted.length - 1];
    }

    private static String getSubmittedFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }

}
