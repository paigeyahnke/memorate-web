package com.memorate.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.memorate.entity.User;
import com.memorate.persistence.UserDao;
import org.apache.log4j.Logger;

/**
 * Created by paige on 10/19/16.
 */
@WebServlet(name = "SignUp", urlPatterns = { "/signUp" } )
public class SignUp extends HttpServlet {

    private final Logger log = Logger.getLogger(this.getClass());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        UserDao dao = new UserDao();

        User existingUser = dao.getUser(username);

        if (existingUser == null) {
            User user = new User(, request.getParameter("password"));
            log.debug("Adding User: " + user);
            dao.addUser(user);

            try {
                request.login(user.getUserName(), user.getPassword());
                log.debug("Logged in new user: " + user.getUserName());

            } catch (ServletException e) {
                log.debug("Failed to login new user: " + user.getUserName());
                log.error(e);
            }

            response.sendRedirect("/user/remember.jsp");
        } else {
            response.sendError(400, "Username already taken");
        }

    }
}
