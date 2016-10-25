package uk.ac.brighton.uni.ch629.catshop.spring.test.controllers;

import org.springframework.web.bind.annotation.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import uk.ac.brighton.uni.ch629.catshop.database.tables.records.Product;

import java.util.Set;

@RestController
public class MainController {
    @GetMapping(value = "/product")
    public Product findProduct(@RequestParam(value = "id") int id) {
        throw new NotImplementedException();
    }

    @GetMapping(value = "/product/all")
    public Set<Product> getAllProducts() {
        throw new NotImplementedException();
    }

    @PostMapping(value = "/protected/product")
    public void addProduct(@RequestBody Product product) {
        throw new NotImplementedException();
    }

    @DeleteMapping(value = "/protected/product")
    public void removeProduct(@RequestParam(value = "id") int id) {
        throw new NotImplementedException();
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