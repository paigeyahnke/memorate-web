package com.memorate.utilities;

import com.memorate.entity.Memory;
import com.memorate.entity.Tag;
import org.apache.log4j.Logger;

import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * Created by paige on 12/7/16.
 */
public class Utilities {
    private static final Logger log = Logger.getLogger(Utilities.class);
    private static final Properties properties = loadProperties("dev.properties");
    private static final String UPLOAD_DIRECTORY = properties.getProperty("uploadDirectory");

    /**
     * This method will load the values from the properties files
     * @param propertiesPath the path for the properties file
     * @return the properties values
     */
    public static Properties loadProperties(String propertiesPath) {

        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        try (InputStream resourceStream = loader.getResourceAsStream(propertiesPath)) {
            log.info("Loading properties from properties file: " + propertiesPath);
            properties.load(resourceStream);
        } catch (IOException e) {
            log.error(e);
        }

        return properties;
    }

    public static void uploadImage(String fileName, Part filePart) throws IOException {
//        final String path = "/users/paige/documents/enterprisejava/memorate-web/uploads";
//        final String path = "/root/uploads";

//        final String UPLOAD_DIRECTORY = properties.getProperty("uploadDirectory");

        OutputStream out = null;
        InputStream filecontent = null;

        try {
            out = new FileOutputStream(new File(UPLOAD_DIRECTORY + File.separator
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
    }

    public static Set<Tag> extractTags(String tagsString, Memory memory) {
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

    public static String getFileName(final Part part) {
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

    public static void deleteFile(String fileName) {
        Path imagePath = Paths.get(UPLOAD_DIRECTORY, fileName);

        try {
            Files.delete(imagePath);
        } catch (NoSuchFileException x) {
            log.error("No such file or directory: " + imagePath);
        } catch (IOException error) {
            // File permission problems are caught here.
            log.error("Problem deleting image: " + error);
        }
    }
}
