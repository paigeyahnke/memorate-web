package com.memorate.persistence;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paulawaite on 4/24/16.
 */
public class AbstractDao<T> {

    private Class<T> type;
    private final Logger log = Logger.getLogger(this.getClass());

    public AbstractDao(Class<T> type) {
        this.type = type;
    }

    public int create(T object) {

        Transaction tx = null;
        Integer id = null;
        Session session = getSession();
        try {
            tx = session.beginTransaction();
            id = (Integer) session.save(object);
            tx.commit();
            log.debug("Created " + object.getClass().getName() + " with id " +
                    "of: " + id);
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.error(e);
        } finally {
            session.close();
        }

        return id;
    }

    public T get(int id) {
        Session session = getSession();
        T result = (T) session.get(type, id);
        session.close();
        return result;
    }

    public List<T> getAll() {
        Session session = getSession();
        ArrayList<T> result = (ArrayList<T>) session.createCriteria(type).list();
        session.close();
        return result;
    }

    public void update(T object) {
        Session session = getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(object);
            tx.commit();
            log.debug("Updated " + object.getClass().getName() + ": " + object);
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            log.error(e);
        } finally {
            session.close();
        }
    }

    public void delete(T object) {
        Session session = getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(object);
            tx.commit();
            log.debug("Deleted " + object.getClass().getName() + ": " + object);
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            log.error("Failed to delete object with error: " + e);
        } finally {
            session.close();
        }
    }

    private Session getSession() {
        return SessionFactoryProvider.getSessionFactory().openSession();
    }
}