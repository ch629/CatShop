package uk.ac.brighton.uni.ch629.catshop.database.model.data;

import uk.ac.brighton.uni.ch629.catshop.database.model.Product;

import java.util.List;

public interface ProductService {
    Product create(Product product);

    Product delete(int id);

    List<Product> findAll();

    Product update(Product product);

    Product findByNumber(int id);
}
