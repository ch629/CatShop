package uk.ac.brighton.uni.ch629.catshop.spring.test.controllers;

import org.springframework.web.bind.annotation.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import uk.ac.brighton.uni.ch629.catshop.spring.test.Application;
import uk.ac.brighton.uni.ch629.catshop.spring.test.database.model.Product;

import java.util.List;
@RestController
public class MainController {
    @GetMapping(value = "/product")
    public Product findProduct(@RequestParam(value = "id") int id) {
        Product product = Application.getProductDao().getProduct(id);
        if (product == null) {
            //TODO: This should probably return a response rather than a product.
        }
        return product != null ? product : null;
    }

    @GetMapping(value = "/product/all")
    public List<Product> getAllProducts() {
        return Application.getProductDao().getProducts();
    }

    @PostMapping(value = "/protected/product")
    public void addProduct(@RequestBody Product product) {
        Application.create(product);
    }

    @DeleteMapping(value = "/protected/product")
    public void removeProduct(@RequestParam(value = "id") int id) {
        Application.getProductDao().deleteProduct(id);
    }

    @PostMapping(value = "/auth/add")
    public void addAuth() { //TODO: @RequestBody
        throw new NotImplementedException();
    }

    @GetMapping(value = "/auth/add")
    public void acceptAuth() {
        throw new NotImplementedException();
    }

    @PostMapping(value = "subscribe")
    public void subscribe() {
        throw new NotImplementedException();
    }
}