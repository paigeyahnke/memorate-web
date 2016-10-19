package com.memorate.persistence;

import com.memorate.entity.Memory;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by paige on 9/14/16.
 */
public class MemoryDaoTest {
    private MemoryDao memoryDao;
    private int memoryId;

    @Before
    public void setUp() {
        memoryDao = new MemoryDao();
    }

    @Test
    public void getAllMemories() throws Exception {
        List<Memory> memories = memoryDao.getAllMemories();
        assertTrue(memories.size() > 0);
    }

    @Test
    public void getMemory() throws Exception {
        Memory memory = memoryDao.getMemory(1);
        assertTrue(memory.getRating() == 5);
    }

    @Test
    public void getMemoriesByRating() throws Exception {
        assertTrue(memoryDao.getMemoriesByRating(5).size() == 1);
    }

    @Test
    public void deleteMemory() throws Exception {
        Memory memory = new Memory("Karuba Gold", 2);
        memoryId = memoryDao.addMemory(memory);
        assertNotNull("Failed to insert new memory", memoryDao.getMemory(memoryId));
        memoryDao.deleteMemory(memoryDao.getMemory(memoryId));
        assertNull("Failed to delete memory", memoryDao.getMemory(memoryId));
    }

    @Test
    public void updateMemory() throws Exception {
        Memory memory = new Memory("Karuba Gold", 2);
        memoryId = memoryDao.addMemory(memory);
        assertEquals("Memory not recorded correctly", 2, memoryDao.getMemory(memoryId).getRating());
        memory.setRating(4);
        memoryDao.updateMemory(memory);
        assertEquals("Memory not recorded correctly", 4, memoryDao.getMemory(memoryId).getRating());
    }

}