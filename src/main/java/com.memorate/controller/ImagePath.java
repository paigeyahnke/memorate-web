package com.memorate.controller;

import com.memorate.utilities.Utilities;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Grabs an image and servers it to the calling jsp
 */
@WebServlet("/image")
public class ImagePath extends HttpServlet {
    private Properties properties = Utilities.loadProperties("dev.properties");

    /**
     * Grabs an image and servers it to the calling jsp
     * @param request request
     * @param response response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = request.getParameter("fileName");
        Path imagePath = Paths.get(Utilities.getUploadDirectory(), fileName);
        response.setContentType(Files.probeContentType(imagePath));

        Files.copy(imagePath, response.getOutputStream());
    }
}
