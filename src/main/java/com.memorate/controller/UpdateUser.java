package com.memorate.controller;

import com.memorate.entity.User;
import com.memorate.persistence.UserDao;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "UpdateUser", urlPatterns = { "/updateUser" } )
public class UpdateUser extends HttpServlet {
    private final Logger log = Logger.getLogger(this.getClass());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("In the update user");
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        log.info("Email: " + email + ", first name: " + firstName + ", last name: " + lastName);

        UserDao dao = new UserDao();
        User user = dao.getUser(request.getRemoteUser());
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        dao.updateUser(user);
        response.setStatus(200);
    }
}
