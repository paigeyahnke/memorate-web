package com.memorate.persistence;

import com.memorate.entity.Role;
import com.memorate.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.apache.log4j.Logger;


/**
 * The user dao used to access user related information in the database.
 * @author Paige Yahnke
 */
public class UserDao {

    private final Logger log = Logger.getLogger(this.getClass());

    /**
     * Get the user with a given username.
     *
     * @param  username
     * @return User
     */
    public User getUser(String username) {
        Session session = SessionFactoryProvider.getSessionFactory().getCurrentSession();

        User user = null;
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            user = (User) session.get(User.class, username);
            transaction.commit();
        } catch (HibernateException exception) {
            if (transaction != null) transaction.rollback();
            log.error(exception);
        }

        return user;
    }

    /**
     * Add a new user
     * @param user
     * @return the username
     */
    public String addUser(User user) {
        Session session = SessionFactoryProvider.getSessionFactory().getCurrentSession();
        Transaction transaction = null;
        String username = null;

        try {
            transaction = session.beginTransaction();
            username = (String) session.save(user);
            session.save(createUserRole(user));
            transaction.commit();
            log.info("Added user: " + user + " with username: " + username);
        } catch (HibernateException exception) {
            if (transaction !=null ) transaction.rollback();
            log.error(exception);
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
