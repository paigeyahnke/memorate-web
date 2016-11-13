package com.memorate.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.memorate.entity.Memory;
import com.memorate.entity.User;
import com.memorate.persistence.MemoryDao;
import com.memorate.persistence.UserDao;
import org.apache.log4j.Logger;

/**
 * Created by paige on 10/19/16.
 */
@WebServlet(name = "Record", urlPatterns = { "/record" } )
public class Record extends HttpServlet {

    private final Logger log = Logger.getLogger(this.getClass());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int rating = Integer.parseInt(request.getParameter("rating"));
        String memo = request.getParameter("memo");
        String imagePath = null;
        String username = request.getUserPrincipal().getName();

        Memory memory = new Memory(name, rating, imagePath, memo, username);
        MemoryDao dao = new MemoryDao();
        dao.addMemory(memory);

    }
}
