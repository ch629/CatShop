package uk.ac.brighton.uni.ch629.catshop.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@JsonAutoDetect
public class Product implements Serializable { //TODO: Add methods to get product from the server and to update products
    private static final long serialVersionUID = 1L;
    private int productNumber, stock;
    private float price;
    private String image, description;
    @JsonBackReference
    private Set<OrderProduct> orders = new HashSet<>();

    @JsonCreator
    public Product(int productNumber, float price, int stock, String image, String description) {
        this.productNumber = productNumber;
        this.price = price;
        this.stock = stock;
        this.image = image;
        this.description = description;
    }

    public int getProductNumber() {
        return productNumber;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<OrderProduct> getOrders() {
        return orders;
    }
}
