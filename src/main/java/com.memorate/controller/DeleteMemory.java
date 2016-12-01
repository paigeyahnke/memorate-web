package com.memorate.controller;

import com.memorate.persistence.MemoryDao;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by paige on 11/30/16.
 */
@WebServlet(name = "DeleteMemory", urlPatterns = { "/delete" } )
public class DeleteMemory  extends HttpServlet {
    private final Logger log = Logger.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int memoryId = Integer.parseInt(request.getParameter("memoryId"));

        log.info("Deleting memory with id: " + memoryId);

        MemoryDao dao = new MemoryDao();
        dao.deleteMemory(dao.getMemory(memoryId));

        response.sendRedirect("/remember");
    }
}
