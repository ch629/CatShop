package uk.ac.brighton.uni.ch629.catshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uk.ac.brighton.uni.ch629.catshop.database.model.AuthToken;
import uk.ac.brighton.uni.ch629.catshop.database.model.Order;
import uk.ac.brighton.uni.ch629.catshop.database.model.Product;
import uk.ac.brighton.uni.ch629.catshop.database.model.data.services.interfaces.AuthTokenService;
import uk.ac.brighton.uni.ch629.catshop.database.model.data.services.interfaces.OrderService;
import uk.ac.brighton.uni.ch629.catshop.database.model.data.services.interfaces.ProductService;

@SpringBootApplication
public class Application {
    private static ProductService productService;
    private static OrderService orderService;
    private static AuthTokenService authTokenService;

    @Autowired
    public Application(ProductService productService, OrderService orderService, AuthTokenService authTokenService) {
        Application.productService = productService;
        Application.orderService = orderService;
        Application.authTokenService = authTokenService;
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

        authTokenService.create(new AuthToken("abc123"));

        Order testOrder = new Order();
        testOrder.addProduct(productService.findByProductNumber(1), 5);
        testOrder.addProduct(productService.findByProductNumber(2), 2);

        System.out.println("CREATING ORDER");
        orderService.create(testOrder);
        System.out.println("FINISHED CREATING ORDER");
    }
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        createDummyData();
        System.out.println(productService.findByProductNumber(1).getDescription());
    }
}