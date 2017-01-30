package com.web.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(name = "ImageServlet", urlPatterns = "/images")
public class ImageServlet extends AbstractServlet {

    private static String pathToWeb;

    @Override
    public void init() throws ServletException {
        pathToWeb = getServletContext().getRealPath(File.separator) + "images";
    }

    @Override
    protected void doGet() throws ServletException, IOException {
        String fileName = getRequest().getParameter("id");
        String mime = getServletContext().getMimeType(fileName);
        getResponse().setContentType(mime);

        File file = new File(pathToWeb + File.separator + fileName);

        try (FileInputStream in = new FileInputStream(file)) {
            try (OutputStream out = getResponse().getOutputStream()) {
                byte[] buf = new byte[1024];
                int count = 0;
                while ((count = in.read(buf)) >= 0) {
                    out.write(buf, 0, count);
                }
            }
        }
    }
}