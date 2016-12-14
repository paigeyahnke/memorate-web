package com.memorate.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;

import com.memorate.entity.Memory;
import com.memorate.persistence.MemoryDao;
import com.memorate.utilities.Utilities;
import org.apache.log4j.Logger;

/**
 * Servlet that records a memory.
 */
@WebServlet(name = "Remember", urlPatterns = { "/remember" } )
@MultipartConfig
public class Remember extends HttpServlet {

    private final Logger log = Logger.getLogger(this.getClass());

    /**
     * Redirects to the remember jsp
     * @param request request
     * @param response response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/remember.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Records a memory
     * @param request request with memory information
     * @param response response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("The recorded rating is: " + request.getParameter("rating"));

        final Part filePart = request.getPart("image");
        final String fileName = Utilities.getFileName(filePart);

        String name = request.getParameter("name");
        int rating = Integer.parseInt(request.getParameter("rating"));
        String memo = request.getParameter("memo");
        String username = request.getUserPrincipal().getName();
        String tagString = request.getParameter("tags");

        Memory memory = new Memory(name, rating, fileName, memo, username);
        MemoryDao dao = new MemoryDao();
        memory.setTags(Utilities.extractTags(tagString, memory));
        dao.addMemory(memory);

        if (fileName != null && !fileName.isEmpty()) {
            Utilities.uploadImage(memory.getFullImageName(), filePart);
        }

        response.sendRedirect("memories");
    }
}
