package uk.ac.brighton.uni.ch629.catshop.spring.test.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.Query;
import java.util.List;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    private static void setUp() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }


    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static List read(String tableName) {
        Session session = getSessionFactory().openSession();
        List values = session.createQuery("FROM " + tableName).list();
        session.close();
        return values;
    }

    public static void create(Object o) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        session.close();
    }

    public static void deleteAll(String tableName) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("DELETE FROM " + tableName);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
