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

}