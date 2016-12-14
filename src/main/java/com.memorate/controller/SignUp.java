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
 * Servlet that signs up and signs in a user
 *
 * @author Paige Yahnke
 */
@WebServlet(name = "SignUp", urlPatterns = { "/signUp", "" } )
public class SignUp extends HttpServlet {

    private final Logger log = Logger.getLogger(this.getClass());

    /**
     * Redirects user to view memories, which prompts sign in.
     *
     * @param request request
     * @param response response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("memories");
    }

    /**
     * Signs up a new users, and then signs them in
     * @param request request that includes the new usename and password
     * @param response response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        UserDao dao = new UserDao();

        User existingUser = dao.getUser(username);

        if (existingUser == null) {
            User user = new User(username, request.getParameter("password"));
            log.info("Adding User: " + user);
            dao.addUser(user);

            try {
                request.login(user.getUserName(), user.getPassword());
                log.info("Logged in new user: " + user.getUserName());

            } catch (ServletException e) {
                log.debug("Failed to login new user: " + user.getUserName());
                log.error(e);
            }

            response.sendRedirect("memories");
        } else {
            response.sendError(400, "Username already taken");
        }
    }
}
