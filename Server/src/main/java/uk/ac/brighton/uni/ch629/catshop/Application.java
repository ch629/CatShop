package uk.ac.brighton.uni.ch629.catshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import uk.ac.brighton.uni.ch629.catshop.database.model.Product;
import uk.ac.brighton.uni.ch629.catshop.database.model.data.repositories.ProductRepository;
import uk.ac.brighton.uni.ch629.catshop.database.model.data.services.interfaces.ProductService;

@SpringBootApplication
public class Application {
    private static ProductService productService;

    @Autowired
    public Application(ProductService productService) {
        Application.productService = productService;
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
    }
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        createDummyData();
        System.out.println(productService.findByNumber(1).getDescription());
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