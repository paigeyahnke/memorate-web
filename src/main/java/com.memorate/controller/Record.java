package com.memorate.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import com.memorate.entity.Memory;
import com.memorate.entity.Tag;
import com.memorate.entity.User;
import com.memorate.persistence.AbstractDao;
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
        log.info("The recorded tags are: " + request.getParameter("tags"));

        String name = request.getParameter("name");
        int rating = Integer.parseInt(request.getParameter("rating"));
        String memo = request.getParameter("memo");
        String imagePath = null;
        String username = request.getUserPrincipal().getName();
        String tagString = request.getParameter("tags");

        Memory memory = new Memory(name, rating, imagePath, memo, username);
        MemoryDao dao = new MemoryDao();
        memory.setTags(extractTags(tagString, memory));
        dao.addMemory(memory);

        response.sendRedirect("/remember");
    }

    private Set<Tag> extractTags(String tagsString, Memory memory) {
        log.info("Tags string: " + tagsString);
        String strippedString = tagsString.replaceAll("\\s+","");
        List<String> tagList = Arrays.asList(strippedString.split(","));

        Set<Tag> tags = new HashSet<>();
        for (String tagString : tagList) {
//            Tag tag = new Tag(tagString, memory);
            Tag tag = new Tag();
            tag.setKeyword(tagString);
            tag.setMemory(memory);
            tags.add(tag);
        }

        return tags;
    }
}
