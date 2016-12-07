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

    /** Get a single memory by the given id
     *
     * @param  username
     * @return User
     */
    public User getUser(String username) {
        Session session = SessionFactoryProvider.getSessionFactory().getCurrentSession();

        User user = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            user = (User) session.get(User.class, username);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.error(e);
        }

//        User user = (User) session.get(User.class, username);
        return user;
    }

    /**
     * Add a new user
     * @param user
     * @return memory id
     */
    public String addUser(User user) {
        Session session = SessionFactoryProvider.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        String username = null;
        try {
            tx = session.beginTransaction();
            username = (String) session.save(user);
            session.save(createUserRole(user));
            tx.commit();
            log.info("Added user: " + user + " with username: " + username);
        } catch (HibernateException e) {
            if (tx !=null ) tx.rollback();
            log.error(e);
        }

        return username;
    }

    /**
     * Updates a user
     * @param user the user
     */
    public void updateUser(User user) {
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
