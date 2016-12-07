package com.memorate.persistence;

import com.memorate.entity.Memory;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        List<Memory> memories;
        Session session = SessionFactoryProvider.getSessionFactory().getCurrentSession();
        memories = session.createCriteria(Memory.class).list();
        return memories;
    }

    /** Return a list of all memories for user
     *
     * @return All memories
     */
    public Set<Memory> getAllMemoriesFor(String username) {

        List<Memory> memories = new ArrayList<Memory>();
        Session session = SessionFactoryProvider.getSessionFactory().getCurrentSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            memories = (List<Memory>) session.createCriteria(Memory.class)
                    .add( Restrictions.eq("username", username))
                    .list();

            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            log.error(e);
        }

//        memories = session.createCriteria(ViewMemory.class, username).list();
        return new HashSet<Memory>(memories);
    }

    /** Get a single memory by the given id
     *
     * @param id memory's id
     * @return ViewMemory
     */
    public Memory getMemory(int id) {
        Memory memory = null;
        Session session = SessionFactoryProvider.getSessionFactory().getCurrentSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            memory = (Memory) session.get(Memory.class, id);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.error(e);
        }

        return memory;
    }

    /** Retrieve memories by rating
     *
     * @param rating ViewMemory's rating which is the search criteria
     * @return ViewMemory
     */
    public List<Memory> getMemoriesByRating(int rating) {
        Session session = SessionFactoryProvider.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(Memory.class);
        criteria.add(Restrictions.eq("rating", rating));
        List<Memory> memories = criteria.list();
        return memories;
    }

    /**
     * Add a new memory
     * @param memory
     * @return memory id
     */
    public int addMemory(Memory memory) {
        AbstractDao dao = new AbstractDao(Memory.class);
        return dao.create(memory);
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
