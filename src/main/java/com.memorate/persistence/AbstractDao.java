package com.memorate.persistence;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * The abstract dao used to access generic information in the database.
 * @author Paula Waite
 */
public class AbstractDao<T> {

    private Class<T> type;
    private final Logger log = Logger.getLogger(this.getClass());

    /**
     * Instantiates the dao with the appropriate class type
     * @param type class type
     */
    public AbstractDao(Class<T> type) {
        this.type = type;
    }

    /**
     * Creates an on object in the database
     * @param object object to create
     * @return object id
     */
    public int create(T object) {

        Transaction transaction = null;
        Integer id = null;
        Session session = getSession();

        try {
            transaction = session.beginTransaction();
            id = (Integer) session.save(object);
            transaction.commit();
            log.debug("Created " + object.getClass().getName() + " with id " + "of: " + id);
        } catch (HibernateException exception) {
            if (transaction != null) transaction.rollback();
            log.error(exception);
        }

        return id;
    }

    /**
     * Retrieve item from database with id
     * @param id id of object to retrieve
     * @return retrieved item
     */
    public T get(int id) {
        Session session = getSession();
        T result = (T) session.get(type, id);
        return result;
    }

    /**
     * Retrieves list of objects in database
     * @return list of objects
     */
    public List<T> getAll() {
        Session session = getSession();
        ArrayList<T> result = (ArrayList<T>) session.createCriteria(type).list();
        return result;
    }

    /**
     * Updates object in database
     * @param object updated object
     */
    public void update(T object) {
        Session session = getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(object);
            transaction.commit();
            log.debug("Updated " + object.getClass().getName() + ": " + object);
        } catch (HibernateException exception) {
            if (transaction!=null) transaction.rollback();
            log.error(exception);
        }
    }

    /**
     * Deletes an object from the database
     * @param object object to delete
     */
    public void delete(T object) {
        Session session = getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(object);
            transaction.commit();
            log.debug("Deleted " + object.getClass().getName() + ": " + object);
        } catch (HibernateException exception) {
            if (transaction!=null) transaction.rollback();
            log.error("Failed to delete object with error: " + exception);
        }
    }

    private Session getSession() {
        return SessionFactoryProvider.getSessionFactory().getCurrentSession();
    }
}