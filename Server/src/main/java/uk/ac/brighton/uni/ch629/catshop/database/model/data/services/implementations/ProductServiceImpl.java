package uk.ac.brighton.uni.ch629.catshop.database.model.data.services.implementations;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.ac.brighton.uni.ch629.catshop.database.model.Product;
import uk.ac.brighton.uni.ch629.catshop.database.model.data.repositories.ProductRepository;
import uk.ac.brighton.uni.ch629.catshop.database.model.data.services.interfaces.ProductService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductRepository productRepository;

    @Override
    @Transactional
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product delete(int id) {
        Product deletedProduct = productRepository.findOne(id);
        if (deletedProduct == null) return null; //Throw exception?
        productRepository.delete(deletedProduct);
        return deletedProduct;
    }

    @Override
    @Transactional
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public Product update(Product product) {
        Product updatedProduct = productRepository.findOne(product.getProductNumber());
        if (updatedProduct == null) return null; //Throw exception?
        updatedProduct.setDescription(product.getDescription());
        updatedProduct.setImage(product.getImage());
        updatedProduct.setPrice(product.getPrice());
        updatedProduct.setStock(product.getStock());
        return updatedProduct;
    }

    @Override
    @Transactional
    public Product findByNumber(int id) {
        return productRepository.findByProductNumber(id);
    }
}
