package ru.ubusheev.springtest;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import ru.ubusheev.springtest.model.Aircraft;


public class HibernateSession {
    private static SessionFactory sessionFactory;

    private HibernateSession() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Aircraft.class);
                StandardServiceRegistry builder = new StandardServiceRegistryBuilder().
                        applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(builder);

            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return sessionFactory;
    }

}
