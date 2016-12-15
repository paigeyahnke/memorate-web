package com.memorate.utilities;

import com.memorate.entity.Memory;
import com.memorate.entity.Tag;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Tests utilities methods
 */
public class UtilitiesTest {
    private static final String UGLY_TAG_STRING = "coffee,ESPRESSO,       kwik, tRi  p";
    private final String CORRECT_CLEANED_STRING = "coffee,espresso,kwik,trip";

    /**
     * Verifies that properties can be loaded
     * @throws Exception
     */
    @Test
    public void loadProperties() throws Exception {
        Properties properties = Utilities.loadProperties("dev.properties");
        String directory = properties.getProperty("uploadDirectory");
        assertTrue("Directory path not found", directory != null);
    }

    /**
     * Cleans a string
     * @throws Exception
     */
    @Test
    public void cleanTagString() throws Exception {
        String cleanString = Utilities.cleanTagString(UGLY_TAG_STRING);
        assertEquals("Did not correctly clean string", CORRECT_CLEANED_STRING, cleanString);
    }

    /**
     * Generated tags for memory
     * @throws Exception
     */
    @Test
    public void autoGenerateTags() throws Exception {
        Memory memory = new Memory("Jack's Pepperoni & Sausage Pizza", 2, null, "Pretty good", "test");
        String generatedString = Utilities.autoGenerateTags(memory);
        String correctString = ",Jacks,Pepperoni,Sausage,Pizza";

        assertEquals("Tags not generated correctly", correctString, generatedString);
    }
}