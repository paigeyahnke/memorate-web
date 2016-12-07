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
import java.util.*;

import com.memorate.entity.Memory;
import com.memorate.entity.Tag;
import com.memorate.persistence.MemoryDao;
import org.apache.log4j.Logger;

/**
 * Created by paige on 10/19/16.
 */
@WebServlet(name = "Remember", urlPatterns = { "/remember" } )
@MultipartConfig
public class Remember extends HttpServlet {

    private final Logger log = Logger.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/remember.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("The recorded rating is: " + request.getParameter("rating"));

        // TODO Upload the file here
        final Part filePart = request.getPart("image");
        final String fileName = getFileName(filePart);

        String name = request.getParameter("name");
        int rating = Integer.parseInt(request.getParameter("rating"));
        String memo = request.getParameter("memo");
        String username = request.getUserPrincipal().getName();
        String tagString = request.getParameter("tags");

        Memory memory = new Memory(name, rating, fileName, memo, username);
        MemoryDao dao = new MemoryDao();
        memory.setTags(extractTags(tagString, memory));
        int id = dao.addMemory(memory);

        if (fileName != null && !fileName.isEmpty()) {
            String path = username + id + fileName;
            uploadImage(memory.getImagePath(), filePart);
        } else {
            log.info("Did not upload a file for memory " + memory.getMemoryId());
        }

        response.sendRedirect("memories");
    }

    private Set<Tag> extractTags(String tagsString, Memory memory) {
        log.info("Tags string: " + tagsString);

        // Adding the name/ title of the memo to the tags
        String nameTags = memory.getName().replaceAll("&|'|-","");
        nameTags = nameTags.replaceAll("\\s+",",");
        tagsString += "," + nameTags;

        String strippedString = tagsString.replaceAll("\\s+","");
        strippedString = strippedString.replaceAll("&", "");
        strippedString = strippedString.toLowerCase();
        HashSet<String> tagList = new HashSet<>(Arrays.asList(strippedString.split(",")));

        Set<Tag> tags = new HashSet<>();
        for (String tagString : tagList) {
            if (!tagString.equals("")) {
                Tag tag = new Tag();
                tag.setKeyword(tagString);
                tag.setMemory(memory);
                tags.add(tag);
            }
        }

        return tags;
    }

    private String uploadImage(String fileName, Part filePart) throws IOException {
        // Create path components to save the file
//        final String path = "/users/paige/documents/enterprisejava/memorate-web/uploads";
        final String path = "/root/uploads";


        OutputStream out = null;
        InputStream filecontent = null;

        try {
            out = new FileOutputStream(new File(path + File.separator
                    + fileName));
            filecontent = filePart.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            log.info("Successfully saved image.");

        } catch (FileNotFoundException fne) {
            log.error("You either did not specify a file to upload or are "
                    + "trying to upload a file to a protected or nonexistent "
                    + "location. ERROR: " + fne.getMessage());
        } finally {
            if (out != null) {
                out.close();
            }
            if (filecontent != null) {
                filecontent.close();
            }
        }

        return fileName;
    }

    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : partHeader.split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        log.error("Couldn't get file name");
        return null;
    }
}
