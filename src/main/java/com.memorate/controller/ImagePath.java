package com.memorate.controller;

import com.memorate.utilities.Utilities;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

@WebServlet("/image")
public class ImagePath extends HttpServlet {
    private Properties properties = Utilities.loadProperties("dev.properties");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        final String UPLOAD_DIRECTORY = "/users/paige/documents/enterprisejava/memorate-web/uploads";
//        final String UPLOAD_DIRECTORY = "/root/uploads";
        final String UPLOAD_DIRECTORY = properties.getProperty("uploadDirectory");
        String fileName = request.getParameter("fileName");
        Path imagePath = Paths.get(UPLOAD_DIRECTORY, fileName);
        response.setContentType(Files.probeContentType(imagePath));

        Files.copy(imagePath, response.getOutputStream());
    }

}
