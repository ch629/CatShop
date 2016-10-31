package uk.ac.brighton.uni.ch629.catshop.database.model.dao.interfaces;


import uk.ac.brighton.uni.ch629.catshop.database.model.Product;

import java.util.List;

public interface IProductDao {
    List<Product> getProducts();

    Product getProduct(int productNumber);

    void updateProduct(Product product);

    void deleteProduct(int productNumber);

    void deleteProduct(Product product);
}
