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
        Session session = SessionFactoryProvider.getSession();
        memories = session.createCriteria(Memory.class).list();
        return memories;
    }

    /** Return a list of all memories for user
     *
     * @return All memories
     */
    public Set<Memory> getAllMemoriesFor(String username) {

        List<Memory> memories = new ArrayList<Memory>();
        Session session = SessionFactoryProvider.getSession();

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


//        memories = session.createCriteria(Memory.class, username).list();
        return new HashSet<Memory>(memories);
    }

    /** Get a single memory by the given id
     *
     * @param id memory's id
     * @return Memory
     */
    public Memory getMemory(int id) {
        Session session = SessionFactoryProvider.getSession();
        Memory memory = (Memory) session.get(Memory.class, id);

        return memory;
    }

    /** Retrieve memories by rating
     *
     * @param rating Memory's rating which is the search criteria
     * @return Memory
     */
    public List<Memory> getMemoriesByRating(int rating) {
        Session session = SessionFactoryProvider.getSession();
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
