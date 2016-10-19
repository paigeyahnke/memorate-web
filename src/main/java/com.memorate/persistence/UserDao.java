package com.memorate.persistence;

import com.memorate.entity.Role;
import com.memorate.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.apache.log4j.Logger;


/**
 * Created by paige on 10/19/16.
 */
public class UserDao {

    private final Logger log = Logger.getLogger(this.getClass());


    /**
     * Add a new memory
     * @param user
     * @return memory id
     */
    public String addUser(User user) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        String username = null;
        try {
            tx = session.beginTransaction();
            username = (String) session.save(user);
            session.save(createUserRole(user));
            tx.commit();
            log.info("Added user: " + user + " with username: " + username);
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            log.error(e);
        } finally {
            session.close();
        }
        return username;
    }

    /**
     * Updates a user
     * @param user the user
     */
    public void updateMemory(User user) {
        AbstractDao dao = new AbstractDao(User.class);
        dao.update(user);
    }

    private Role createUserRole(User user) {
        Role role = new Role();
        role.setUserName(user.getUserName());
        role.setName("user");
        return role;
    }
}
