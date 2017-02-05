package br.com.softop.imobiliaria.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author danilo
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;
    static final StandardServiceRegistry registry;

    static {
        registry = new StandardServiceRegistryBuilder().configure().build();

//        try {
//            sessionFactory = new Configuration().configure().buildSessionFactory();;
//        } catch (Throwable ex) {
//            System.err.println("Initial SessionFactory creation failed." + ex);
//            throw new ExceptionInInitializerError(ex);
//        }
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null || sessionFactory.isClosed()) {
            try {
                sessionFactory = new Configuration().configure().buildSessionFactory();
//                sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            } catch (Exception e) {
                // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
                // so destroy it manually.
//                StandardServiceRegistryBuilder.destroy(registry);
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
