package servlet;

import DAO.DAOUser;
import DB.Models.User;
import Utils.Hasher;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@WebServlet("/login")
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/login.ftl");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        DAOUser daoUser = new DAOUser();
        if (daoUser.userIsExist(email, Hasher.hashString(password))) {
            System.out.println("User existEmail");
            HttpSession session = req.getSession();
            session.setAttribute("user", daoUser.getUserByEmail(email));
            session.setMaxInactiveInterval(60*60);
            resp.sendRedirect("/profile");
        } else {
            System.out.println("User does not exist");
            req.setAttribute("notExist", true);
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/login.ftl");
            dispatcher.forward(req, resp);
        }
    }
}
