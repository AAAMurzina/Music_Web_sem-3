package servlet;

import DB.Models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/profile")
public class UserProfile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        if (httpSession.getAttribute("user") == null) {
            resp.sendRedirect("/login");
        } else {
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/userProfile.ftl");
            User user = (User) httpSession.getAttribute("user");
            if (req.getSession().getAttribute("user") != null) {
                req.setAttribute("session", true);
            }
            req.setAttribute("login", user.login);
            req.setAttribute("name", user.name);
            req.setAttribute("email", user.email);
            dispatcher.forward(req, resp);
        }

    }
}
