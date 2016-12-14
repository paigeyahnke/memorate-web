package com.memorate.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.memorate.entity.Memory;
import com.memorate.persistence.MemoryDao;
import org.apache.log4j.Logger;

/**
 * Servlet that hands the memory to the jsp to display to the user
 */
@WebServlet(name = "ViewMemory", urlPatterns = { "/viewMemory" } )
public class ViewMemory extends HttpServlet {
    private final Logger log = Logger.getLogger(this.getClass());

    /**
     * Retrieves the memory and passes it to the jsp.
     *
     * @param request servlet request that contains memory id
     * @param response servlet response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MemoryDao dao = new MemoryDao();
        int id = Integer.parseInt(request.getParameter("id"));
        com.memorate.entity.Memory memory = dao.getMemory(id);
        request.setAttribute("memory", memory);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user/memory.jsp");
        dispatcher.forward(request, response);
    }
}
