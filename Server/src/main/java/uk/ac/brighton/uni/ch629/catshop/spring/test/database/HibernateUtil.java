package uk.ac.brighton.uni.ch629.catshop.spring.test.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.Query;
import java.io.Serializable;
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
        if (sessionFactory == null) setUp();
        return sessionFactory;
    }

    public static Session getSession() {
        return getSessionFactory().openSession();
    }

    public static List getAll(String tableName) {
        Session session = getSessionFactory().openSession();
        List values = session.createQuery("FROM " + tableName).list();
        session.close();
        return values;
    }

    public static void create(Object o) {
        Session session = getSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        session.close();
    }

    public static void deleteAll(String tableName) {
        Session session = getSession();
        session.beginTransaction();
        Query query = session.createQuery("DELETE FROM " + tableName);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public static <T> T get(Class<T> clazz, Serializable id) {
        Session session = getSession();
        T t = session.load(clazz, id);
        session.close();
        return t;
    }

    public static <T> void delete(Class<T> clazz, Serializable id) {
        Session session = getSession();
        session.beginTransaction();
        T object = get(clazz, id);
        session.delete(object);
        session.getTransaction().commit();
        session.close();
    }
}
