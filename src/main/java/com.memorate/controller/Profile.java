package com.memorate.controller;

import com.memorate.entity.User;
import com.memorate.persistence.UserDao;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by paige on 12/6/16.
 */
@WebServlet(name = "Profile", urlPatterns = { "/profile" } )
public class Profile  extends HttpServlet {
    private final Logger log = Logger.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserDao dao = new UserDao();

        // TODO: Need to separate user details (like name & email from their password)
        User currentUser = dao.getUser(request.getUserPrincipal().getName());
        request.setAttribute("user", currentUser);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/user/profile.jsp");
        dispatcher.forward(request, response);
    }
}