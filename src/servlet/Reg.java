package servlet;

import DAO.DAOUser;
import DB.Models.User;
import Utils.Hasher;
import com.mysql.cj.exceptions.WrongArgumentException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/reg")

public class Reg extends HttpServlet {
    @Override
    // Туть мы просто отправялем страничку на полученный GET-request
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/reg.ftl");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        // Туть мы вытаскиваем из формы данные, запихиваем их в модель(класс) юзера, и отправялем в ДАО
        // чтобы ДАО сохранил всё это дело в БД
        // Here we create new User row in DB from POST request of registration page
        String email = req.getParameter("email");
        String login = req.getParameter("login");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        // Туть мы хешируем пароль с помощью SHA256 (вывод получается из Base64 encoder'a)
        password = Hasher.hashString(password);
        User user = new User(email, name, login, password);
        DAOUser uUser = new DAOUser();
        req.setAttribute("email", email);
        req.setAttribute("login", login);
        req.setAttribute("name", name);
        if (uUser.existEmail(email)) {
            req.setAttribute("error_message", "User with this email exists");
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/reg.ftl");
            dispatcher.forward(req, resp);
        } else if (uUser.existLogin(login)) {
            req.setAttribute("error_message", "User with this login exists");
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/reg.ftl");
            dispatcher.forward(req, resp);
        } else {
            try {
                uUser.saveUser(user);
                // После успешной регистрации отправляем клиента на главную страничку
                resp.sendRedirect("/index");
            } catch (WrongArgumentException ex) {
                if (ex.getMessage().contains("Login")) {
                    req.setAttribute("error_message", "Login contains unsupported symbols");
                } else {
                    req.setAttribute("error_message", "E-mail has incorrect form");
                }
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/reg.ftl");
                dispatcher.forward(req, resp);
            }
        }

    }

}
