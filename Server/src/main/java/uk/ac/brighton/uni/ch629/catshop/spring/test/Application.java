package uk.ac.brighton.uni.ch629.catshop.spring.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uk.ac.brighton.uni.ch629.catshop.spring.test.database.Product;

import javax.persistence.Query;
import java.util.List;

@SpringBootApplication
public class Application {
    private static SessionFactory sessionFactory;

    private static void setUp() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try {
//            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    private static void createDummyData() {
        create(new Product("40 inch LED HD TV", 269.00d, 90, "pic0001.jpg"));
        create(new Product("DAB Radio", 29.99d, 20, "pic0002.jpg"));
        create(new Product("Toaster", 19.99d, 33, "pic0003.jpg"));
        create(new Product("Watch", 29.99d, 10, "pic0004.jpg"));
        create(new Product("Digital Camera", 89.99d, 17, "pic0005.jpg"));
        create(new Product("MP3 Player", 7.99d, 15, "pic0006.jpg"));
        create(new Product("32GB USB2 Drive", 6.99d, 1, "pic0007.jpg"));
    }

    public static void main(String[] args) {
        setUp();
        createDummyData();
        SpringApplication.run(Application.class, args);
        System.out.println(findByProductNumber(1).getDescription());
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static int create(Product p) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.save(p);
        session.getTransaction().commit();
        session.close();
        return p.getProductNumber();
    }

    public static List<Product> read() {
        Session session = getSessionFactory().openSession();
        List<Product> products = session.createQuery("FROM PRODUCT").list();
        session.close();
        return products;
    }

    public static void update(Product p) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        Product pr = session.load(Product.class, p.getProductNumber());
        pr.setImage(p.getImage());
        pr.setDescription(p.getDescription());
        pr.setPrice(p.getPrice());
        pr.setStock(p.getStock());
        session.getTransaction().commit();
        session.close();
    }

    public static void delete(int productNumber) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        Product p = findByProductNumber(productNumber);
        session.delete(p);
        session.getTransaction().commit();
        session.close();
    }

    public static Product findByProductNumber(int productNumber) {
        Session session = getSessionFactory().openSession();
        Product p = session.load(Product.class, productNumber);
        session.close();
        return p;
    }

    public static void deleteAll() {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("DELETE FROM PRODUCT");
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}