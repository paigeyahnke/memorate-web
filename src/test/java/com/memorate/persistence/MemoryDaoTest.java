package com.memorate.persistence;

import com.memorate.entity.Memory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

/**
 * Tests for memory dao
 */
public class MemoryDaoTest {
    private MemoryDao memoryDao;
    private static final String USER_NAME = "test";
    private Memory memory1;
    private Memory memory2;

    /**
     * Creates 2 memories for each test to use
     */
    @Before
    public void setUp() {
        memoryDao = new MemoryDao();
        memory1 = new Memory("Karuba Gold", 2, null, "Never hot enough", USER_NAME);
        memory2 = new Memory("Flamin Hot Cheetos", 5, null, "Never hot enough", USER_NAME);

        memoryDao.addMemory(memory1);
        memoryDao.addMemory(memory2);
    }

    /**
     * Gets all memories for the user
     * @throws Exception
     */
    @Test
    public void getAllMemoriesForUser() throws Exception {
        Set<Memory> memories = memoryDao.getAllMemoriesFor(USER_NAME);
        assertTrue("Incorrect number of memories returned", memories.size() == 2);
    }

    /**
     * Gets a memory and verifies it's the correct one
     * @throws Exception
     */
    @Test
    public void getMemory() throws Exception {
        Memory memory = memoryDao.getMemory(memory1.getMemoryId());
        assertTrue("Memory rating not returned correct", memory.getRating() == memory1.getRating());
    }

    /**
     * Updates a memory
     * @throws Exception
     */
    @Test
    public void updateMemory() throws Exception {
        final int NEW_RATING = 1;
        memory1.setRating(NEW_RATING);
        memoryDao.updateMemory(memory1);
        assertEquals("ViewMemory not recorded correctly", NEW_RATING, memoryDao.getMemory(memory1.getMemoryId()).getRating());
    }

    /**
     * Deletes the memories created for the test
     */
    @After
    public void cleanUp() {
        memoryDao.deleteMemory(memoryDao.getMemory(memory1.getMemoryId()));
        memoryDao.deleteMemory(memoryDao.getMemory(memory2.getMemoryId()));
        assertNull("Failed to delete memory", memoryDao.getMemory(memory1.getMemoryId()));

    }

//    @Test
//    public void getMemoriesByRating() throws Exception {
//        assertTrue(memoryDao.getMemoriesByRating(5).size() > 1);
//    }

//    @Test
//    public void deleteMemory() throws Exception {
//        memoryDao.deleteMemory(memoryDao.getMemory(memory1.getMemoryId()));
//        assertNull("Failed to delete memory", memoryDao.getMemory(memory1.getMemoryId()));
//    }

    //    @Test
//    public void getAllMemoriesForUser() throws Exception {
//        Set<Memory> memories = memoryDao.getAllMemoriesFor(USER_NAME);
//        assertNotNull("No memories returned", memories);
//    }

}