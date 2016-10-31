package uk.ac.brighton.uni.ch629.catshop.database.model.dao;

import org.hibernate.Session;
import uk.ac.brighton.uni.ch629.catshop.database.HibernateUtil;
import uk.ac.brighton.uni.ch629.catshop.database.model.Product;
import uk.ac.brighton.uni.ch629.catshop.database.model.dao.interfaces.IProductDao;

import java.util.List;

public class ProductDao implements IProductDao {
    @Override
    public List<Product> getProducts() {
        return HibernateUtil.getAll("Product");
    }

    @Override
    public Product getProduct(int productNumber) {
        return HibernateUtil.get(Product.class, productNumber);
    }

    @Override
    public void updateProduct(Product product) {
        Session session = HibernateUtil.getSession();
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
