package com.memorate.persistence;

import com.memorate.entity.Memory;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paige Yahnke on 9/14/16.
 *
 * @author Paige Yahnke
 */
public class MemoryDao {

    private final Logger log = Logger.getLogger(this.getClass());

    /** Return a list of all memories
     *
     * @return All memories
     */
    public List<Memory> getAllMemories() {
        List<Memory> memories = new ArrayList<Memory>();
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        memories = session.createCriteria(Memory.class).list();
        return memories;
    }

    /** Get a single memory by the given id
     *
     * @param id memory's id
     * @return Memory
     */
    public Memory getMemory(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Memory memory = (Memory) session.get(Memory.class, id);
        return memory;
    }

    /** Retrieve memories by rating
     *
     * @param rating Memory's rating which is the search criteria
     * @return Memory
     */
    public List<Memory> getMemoriesByRating(int rating) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Memory.class);
        criteria.add(Restrictions.eq("rating", rating));
        return criteria.list();
    }

    /**
     * Add a new memory
     * @param memory
     * @return memory id
     */
    public int addMemory(Memory memory) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        Integer memoryId = null;
        try {
            tx = session.beginTransaction();
            memoryId = (Integer) session.save(memory);
            tx.commit();
            log.info("Added memory: " + memory + " with id of: " + memoryId);
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            log.error(e);
        } finally {
            session.close();
        }
        return memoryId;
    }

    /**
     * Deletes a memory
     * @param memory
     */
    public void deleteMemory(Memory memory) {
        AbstractDao dao = new AbstractDao(Memory.class);
        dao.delete(memory);
    }

    /**
     * Updates a memory
     * @param memory
     */
    public void updateMemory(Memory memory) {
        AbstractDao dao = new AbstractDao(Memory.class);
        dao.update(memory);
    }
}
