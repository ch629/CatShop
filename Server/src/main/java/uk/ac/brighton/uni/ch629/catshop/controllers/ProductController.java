package uk.ac.brighton.uni.ch629.catshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.ac.brighton.uni.ch629.catshop.data.Product;
import uk.ac.brighton.uni.ch629.catshop.data.services.interfaces.ProductService;

import java.util.List;

@RestController("product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = {"/product/{id}", "/products/{id}"})
    public Product getProduct(@PathVariable("id") int id) {
        return productService.findByProductNumber(id);
    }

    @GetMapping(value = {"/product", "/products", "/product/all"})
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @DeleteMapping(value = {"/product", "/product/delete"})
    public void removeProduct(@RequestParam(value = "id") int id) {
        productService.delete(id);
    }

    @PostMapping(value = {"/product", "/product/add"})
    public void addProduct(@RequestBody Product product) {
        productService.create(product);
    }
}
