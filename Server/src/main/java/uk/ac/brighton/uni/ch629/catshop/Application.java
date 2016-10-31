package uk.ac.brighton.uni.ch629.catshop;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import uk.ac.brighton.uni.ch629.catshop.database.model.Product;
import uk.ac.brighton.uni.ch629.catshop.database.model.dao.AuthTokenDao;
import uk.ac.brighton.uni.ch629.catshop.database.model.dao.OrderDao;
import uk.ac.brighton.uni.ch629.catshop.database.model.dao.ProductDao;
import uk.ac.brighton.uni.ch629.catshop.database.model.dao.interfaces.IAuthTokenDao;
import uk.ac.brighton.uni.ch629.catshop.database.model.dao.interfaces.IOrderDao;
import uk.ac.brighton.uni.ch629.catshop.database.model.dao.interfaces.IProductDao;
import uk.ac.brighton.uni.ch629.catshop.database.model.data.ProductRepository;
import uk.ac.brighton.uni.ch629.catshop.database.model.data.ProductService;

@SpringBootApplication
public class Application {
    private static final IProductDao PRODUCT_DAO = new ProductDao(); //TODO: Move these to a better location, maybe HibernateUtil?
    private static final IOrderDao ORDER_DAO = new OrderDao();
    private static final IAuthTokenDao AUTH_TOKEN_DAO = new AuthTokenDao();
    private static SessionFactory sessionFactory;

    private static ProductService productService;

    @Autowired
    public Application(ProductService productService) {
        Application.productService = productService;
    }

    private static void setUp() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    private static void createDummyData() {
        System.out.println("CREATING DUMMY DATA");
        productService.create(new Product("40 inch LED HD TV", 269.00d, 90, "pic0001.jpg"));
        productService.create(new Product("DAB Radio", 29.99d, 20, "pic0002.jpg"));
        productService.create(new Product("Toaster", 19.99d, 33, "pic0003.jpg"));
        productService.create(new Product("Watch", 29.99d, 10, "pic0004.jpg"));
        productService.create(new Product("Digital Camera", 89.99d, 17, "pic0005.jpg"));
        productService.create(new Product("MP3 Player", 7.99d, 15, "pic0006.jpg"));
        productService.create(new Product("32GB USB2 Drive", 6.99d, 1, "pic0007.jpg"));
        System.out.println("DONE CREATING DUMMY DATA");

        /*System.out.println("CREATING DUMMY DATA"); NOTE: OLD
        HibernateUtil.create(new Product("40 inch LED HD TV", 269.00d, 90, "pic0001.jpg"));
        HibernateUtil.create(new Product("DAB Radio", 29.99d, 20, "pic0002.jpg"));
        HibernateUtil.create(new Product("Toaster", 19.99d, 33, "pic0003.jpg"));
        HibernateUtil.create(new Product("Watch", 29.99d, 10, "pic0004.jpg"));
        HibernateUtil.create(new Product("Digital Camera", 89.99d, 17, "pic0005.jpg"));
        HibernateUtil.create(new Product("MP3 Player", 7.99d, 15, "pic0006.jpg"));
        HibernateUtil.create(new Product("32GB USB2 Drive", 6.99d, 1, "pic0007.jpg"));
        System.out.println("DONE CREATING DUMMY DATA");*/


    }

    //Old with just Hibernate
    /*public static void main(String[] args) {
        setUp();
        createDummyData();
        SpringApplication.run(Application.class, args);
        Product product = PRODUCT_DAO.getProduct(1);
        if (product != null) System.out.println(product.getDescription());
        else System.out.println("PRODUCT IS NULL!");
    }*/

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println(productService.findByNumber(1).getDescription());
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void createMany(Product... products) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        for (Product p : products) session.save(p);
        session.getTransaction().commit();
        session.close();
    }

    public static int create(Product p) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.save(p);
        session.getTransaction().commit();
        session.close();
        return p.getProductNumber();
    }

    public static IProductDao getProductDao() {
        return PRODUCT_DAO;
    }

    public static IOrderDao getOrderDao() {
        return ORDER_DAO;
    }

    public static IAuthTokenDao getAuthTokenDao() {
        return AUTH_TOKEN_DAO;
    }

    @Bean
    public CommandLineRunner demo(ProductRepository repository) {
        return (args) -> {
            repository.save(new Product("40 inch LED HD TV", 269.00d, 90, "pic0001.jpg"));
            repository.findAll().forEach(product -> System.out.println(product.getDescription()));
        };
    }
}

/*
TODO: Spring DAO & ORM
TODO: Spring Data (Can work with Hibernate)
TODO: Spring Autowired Annotation (with Hibernate)
 */