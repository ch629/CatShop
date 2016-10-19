import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import uk.ac.brighton.uni.ch629.catshop.database.tables.records.Product;

import static org.assertj.core.api.Assertions.assertThat;

public class DatabaseTest {


    @BeforeClass
    public static void createTable() {
        Product.createTable();
    }

    private Product addProduct() {
        Product product = new Product("Testing123", 5.4d);
        product.create();
        return product;
    }

    @After
    public void cleanDatabase() {
        Product.deleteAll();
    }

    @Test
    public void testThatDataInserts() {
        assertThat(Product.getProduct(0)).isNull(); //NOTE: This is fine because the table is empty, and the next id will always be 0
        addProduct();
        assertThat(Product.getProduct(0)).isNotNull();
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
