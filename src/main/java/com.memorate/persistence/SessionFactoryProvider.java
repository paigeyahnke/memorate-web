package com.memorate.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * This file provides a SessionFactory for use with DAOS using Hibernate
 * @author paulawaite
 * @version 1.0 10/21/15.
 */
public class SessionFactoryProvider {

    private static SessionFactory sessionFactory;
    private static Session session;


    private SessionFactoryProvider() {
    }

    private static void createSessionFactory() {

        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            createSessionFactory();
        }
        return sessionFactory;
    }


    public static Session getSession() {

//        if (session == null) {
            if (sessionFactory == null) {
                createSessionFactory();
            }
//
//            session = sessionFactory.openSession();
//        }

        return sessionFactory.getCurrentSession();
    }

}