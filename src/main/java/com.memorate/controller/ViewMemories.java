package com.memorate.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import com.memorate.entity.Memory;
import com.memorate.persistence.MemoryDao;
import org.apache.log4j.Logger;

/**
 * Created by paige on 10/19/16.
 */
@WebServlet(name = "memories", urlPatterns = { "/memories" } )
public class ViewMemories extends HttpServlet {

    private final Logger log = Logger.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getUserPrincipal().getName();
        log.info("Current user: " + username);

        MemoryDao memoryDao = new MemoryDao();
        Set<Memory> memories = memoryDao.getAllMemoriesFor(username);

        request.setAttribute("memories", memories);

        RequestDispatcher dispatcher = request.getRequestDispatcher("user/memories.jsp");
        dispatcher.forward(request, response);

    }
}
