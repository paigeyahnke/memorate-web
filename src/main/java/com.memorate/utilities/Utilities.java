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
 * General utilities for other code to use
 * @author Paige Yahnke
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
        Properties newProperties = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        try (InputStream resourceStream = loader.getResourceAsStream(propertiesPath)) {
            log.info("Loading properties from properties file: " + propertiesPath);
            newProperties.load(resourceStream);
        } catch (IOException exception) {
            log.error(exception);
        }

        return newProperties;
    }

    /**
     * Uploads the image to the server
     * @param fileName name of the file
     * @param filePart file part
     * @throws IOException
     */
    public static void uploadImage(String fileName, Part filePart) throws IOException {
        OutputStream out = null;
        InputStream fileContent = null;

        try {
            File file = new File(UPLOAD_DIRECTORY + File.separator + fileName);
            out = new FileOutputStream(file);
            fileContent = filePart.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = fileContent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            log.info("Successfully saved image.");

        } catch (FileNotFoundException fileNotFoundException) {
            log.error("You either did not specify a file to upload or are "
                    + "trying to upload a file to a protected or nonexistent "
                    + "location. ERROR: " + fileNotFoundException.getMessage());
        } finally {
            if (out != null) {
                out.close();
            }
            if (fileContent != null) {
                fileContent.close();
            }
        }
    }

    /**
     * Clean the string of tags
     * @param tagsString string containing all the tags
     * @return cleaned string
     */
    public static String cleanTagString(String tagsString) {
        String strippedString = tagsString.replaceAll("\\s+","");
        strippedString = strippedString.replaceAll("&", "");
        strippedString = strippedString.toLowerCase();

        return strippedString;
    }

    /**
     * Returns a list of tags for memory
     * @param rawTagString user entered string of tags
     * @param memory memory
     * @return list of tags for the memory
     */
    public static Set<Tag> extractTags(String rawTagString, Memory memory) {
        String autoGeneratedTagString = autoGenerateTags(memory);
        String cleanedString = cleanTagString(rawTagString + autoGeneratedTagString);

        HashSet<String> keywordList = new HashSet<>(Arrays.asList(cleanedString.split(",")));

        Set<Tag> tags = new HashSet<>();
        for (String keyword : keywordList) {
            if (!keyword.isEmpty()) {
                Tag tag = new Tag();
                tag.setKeyword(keyword);
                tag.setMemory(memory);
                tags.add(tag);
            }
        }

        return tags;
    }

    /**
     * Generate tags for user, based on memory
     * @param memory memory
     * @return auto generated tags
     */
    public static String autoGenerateTags(Memory memory) {
        String autoGeneratedTags = "";

        // Adding the name/ title of the memo to the tags
        String nameTags = memory.getName().replaceAll("&|'|-","");
        nameTags = nameTags.replaceAll("\\s+",",");
        autoGeneratedTags += "," + nameTags;

        return autoGeneratedTags;
    }

    /**
     * Get the file name from the part
     * @param part part
     * @return file name
     */
    public static String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : partHeader.split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }

        log.error("Couldn't get file name");
        return null;
    }

    /**
     * Delete the file with name
     * @param fileName file name to delete
     */
    public static void deleteFile(String fileName) {
        Path imagePath = Paths.get(UPLOAD_DIRECTORY, fileName);

        try {
            Files.delete(imagePath);
        } catch (NoSuchFileException noSuchFileException) {
            log.error("No such file or directory: " + imagePath + ". " + noSuchFileException.getMessage());
        } catch (IOException error) {
            // File permission problems are caught here.
            log.error("Problem deleting image: " + error);
        }
    }

    public static String getUploadDirectory() {
        return UPLOAD_DIRECTORY;
    }
}
