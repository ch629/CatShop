package uk.ac.brighton.uni.ch629.catshop.spring.test.database.model.dao;

import org.hibernate.Session;
import uk.ac.brighton.uni.ch629.catshop.spring.test.database.HibernateUtil;
import uk.ac.brighton.uni.ch629.catshop.spring.test.database.model.Product;
import uk.ac.brighton.uni.ch629.catshop.spring.test.database.model.dao.interfaces.IProductDao;

import java.util.List;

import static uk.ac.brighton.uni.ch629.catshop.spring.test.database.HibernateUtil.getSession;

public class ProductDao implements IProductDao {
    @Override
    public List<Product> getProducts() {
        return HibernateUtil.getAll("PRODUCT");
    }

    @Override
    public Product getProduct(int productNumber) {
        return HibernateUtil.get(Product.class, productNumber);
    }

    @Override
    public void updateProduct(Product product) {
        Session session = getSession();
        session.beginTransaction();
        Product oldProduct = session.load(Product.class, product.getProductNumber());
        oldProduct.setDescription(product.getDescription());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setStock(product.getStock());
        oldProduct.setImage(product.getImage());
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteProduct(int productNumber) {
        HibernateUtil.delete(Product.class, productNumber);
    }

    @Override
    public void deleteProduct(Product product) {
        deleteProduct(product.getProductNumber());
    }
}
