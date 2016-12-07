package com.memorate.controller;

import com.memorate.entity.Memory;
import com.memorate.persistence.MemoryDao;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.*;

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
        Memory memory = dao.getMemory(memoryId);
        String fileName = memory.getImagePath();
        dao.deleteMemory(memory);

        if (fileName != null) {
            final String UPLOAD_DIRECTORY = "/root/uploads";

            deleteImage(Paths.get(UPLOAD_DIRECTORY, fileName));
        }

        response.sendRedirect("memories");
    }

    private void deleteImage(Path imagePath) {
        try {
            Files.delete(imagePath);
        } catch (NoSuchFileException x) {
            log.error("No such file or directory: " + imagePath);
        } catch (IOException x) {
            // File permission problems are caught here.
            log.error("Problem deleting image" + x);
        }

    }
}
