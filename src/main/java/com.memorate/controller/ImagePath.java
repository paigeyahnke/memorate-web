package com.memorate.controller;

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

@WebServlet("/image")
public class ImagePath extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        final String UPLOAD_DIRECTORY = "/users/paige/documents/enterprisejava/memorate-web/uploads";
        final String UPLOAD_DIRECTORY = "/root/uploads";


        String fileName = request.getParameter("fileName");

        Path imagePath = Paths.get(UPLOAD_DIRECTORY, fileName);

        // set content type
        response.setContentType(Files.probeContentType(imagePath));

        // write image path to outputstream
        Files.copy(imagePath, response.getOutputStream());
    }

}
