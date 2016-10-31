package uk.ac.brighton.uni.ch629.catshop.database.model.data;

import org.springframework.data.repository.CrudRepository;
import uk.ac.brighton.uni.ch629.catshop.database.model.Product;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    List<Product> findAll();

    Product findByProductNumber(int productNumber);
}
