package uk.ac.brighton.uni.ch629.catshop.database.tables.records;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Set;

public class Product {
    private int productNumber = -1; //NOTE: Not final because of Auto Increment on the table
    private String description;
    private double price;
    private int stock;

    public Product(int productNumber, String description, double price, int stock) {
        this(description, price, stock);
        this.productNumber = productNumber;
    }

    public Product(String description, double price, int stock) {
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public Product(String description, double price) {
        this(description, price, 0);
    }

    public static void deleteAll() { //NOTE: Only really for testing
        throw new NotImplementedException();
    }

    public static Product getProduct(int productNumber) {
        throw new NotImplementedException();
    }

    public static Set<Product> getAll() {
        throw new NotImplementedException();
    }

    public static void createTable() {
        throw new NotImplementedException();
    }

    public static void dropTable() {
        throw new NotImplementedException();
    }

    public int getProductNumber() {
        return productNumber;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //NOTE: To get the generated productNumber can use stmt.executeUpdate.("INSERT", Statement.RETURN_GENERATED_KEYS)
    //NOTE: and stmt.getGeneratedKeys() as a ResultSet to receive it
    //NOTE: https://dev.mysql.com/doc/connector-j/5.1/en/connector-j-usagenotes-last-insert-id.html
    public void create() {
        throw new NotImplementedException();
    }

    public void update() {
        throw new NotImplementedException();
    }

    public void delete() {
        throw new NotImplementedException();
    }
}