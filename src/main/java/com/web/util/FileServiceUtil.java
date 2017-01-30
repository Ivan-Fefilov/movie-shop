package com.web.util;

import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FileServiceUtil {
    private static File pathToWeb;

    public static void initialize(ServletContext servletContext) {
        String address = servletContext.getRealPath(File.separator + "images");
        pathToWeb = new File(address);
    }

    public static boolean validateFileFromServlet(Part filePart) {
        String fileName = getSubmittedFileName(filePart);

        if (fileName == null || fileName.trim().equals("")) {
            return false;
        }

        return true;
    }

    public static String writeFileToServer(Part filePart) throws IOException {
        String fileName = getSubmittedFileName(filePart);
        String fName = getFileNameWithoutExt(fileName) + System.currentTimeMillis();
        String fExt = getFileExtension(fileName);
        File file = new File(pathToWeb, fName + fExt);

        String resultFileName = file.getName();

        try (InputStream fileContent = filePart.getInputStream()) {
            Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }

        return resultFileName;
    }

    public static void deleteFileFromServer(String fileName) {
        File file = new File(pathToWeb, fileName);
        file.delete();
    }

    private static String getSubmittedFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf("\\") + 1);
            }
        }
        return null;
    }

    private static String getFileExtension(String fileName) {
        String extension = "";

        int index = fileName.lastIndexOf('.');
        if (index > 0) {
            extension = fileName.substring(index);
        }

        return extension;
    }

    private static String getFileNameWithoutExt(String fileName) {
        String fileWithoutExt = "";

        int index = fileName.lastIndexOf('.');
        if (index > 0) {
            fileWithoutExt = fileName.substring(0, index);
        }

        return fileWithoutExt;
    }
}
