package com.memorate.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.apache.log4j.Logger;

/**
 * Signs out a user.
 */
@WebServlet(name = "SignOut", urlPatterns = { "/signOut" } )
public class SignOut extends HttpServlet {
    private final Logger log = Logger.getLogger(this.getClass());

    /**
     * Signs out a user.
     * @param request request
     * @param response response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            request.logout();
            log.debug("Logging out");
        } catch (ServletException e) {
            log.debug("Failed to logout");
            log.error(e);
        }

        response.sendRedirect("memories");
    }
}
