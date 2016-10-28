package uk.ac.brighton.uni.ch629.catshop.spring.test.database.model.dao.interfaces;


import uk.ac.brighton.uni.ch629.catshop.spring.test.database.model.Product;

import java.util.List;

public interface IProductDao {
    List<Product> getProducts();

    Product getProduct(int productNumber);

    void updateProduct(Product product);

    void deleteProduct(int productNumber);

    void deleteProduct(Product product);
}
