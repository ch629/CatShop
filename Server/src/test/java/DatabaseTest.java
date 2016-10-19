import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import uk.ac.brighton.uni.ch629.catshop.database.tables.records.Product;

import static org.assertj.core.api.Assertions.assertThat;

public class DatabaseTest {

    @Before
    public void createTable() {
        Product.createTable();
    }

    private Product addProduct() {
        Product product = new Product("Testing123", 5.4d, "Image.jpg");
        product.create();
        return product;
    }

    @After
    public void dropTable() {
        Product.dropTable();
    }

    @Test
    public void testThatProductNumberGenerates() {
        Product p = new Product("Test", 2.5d, "Image.jpg");
        assertThat(p.getProductNumber()).isEqualTo(-1);
        p.create();
        assertThat(p.getPrice()).isNotEqualTo(-1);
    }

    @Test
    public void testThatDataInserts() {
        assertThat(Product.getProduct(1)).isNull(); //NOTE: This is fine because the table is empty, and the next id will always be 0
        Product p = addProduct();
        System.out.println("Generated with ID: " + p.getProductNumber());
        assertThat(Product.getProduct(p.getProductNumber())).isNotNull();
    }

    @Test
    public void testThatDataUpdates() { //NOTE: This won't run without inserts
        Product p = addProduct();
        double originalPrice = p.getPrice();
        p.setPrice(originalPrice + 1);
        p.update();
        assertThat(Product.getProduct(p.getProductNumber()).getPrice()).isNotEqualTo(originalPrice);
    }

    @Test
    public void testThatDataDeletes() { //NOTE: This won't run without inserts
        Product p = addProduct();
        p.delete();
        assertThat(Product.getProduct(p.getProductNumber())).isNull();
    }
}
