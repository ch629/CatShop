package uk.ac.brighton.uni.ch629.catshop.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@JsonAutoDetect
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    private int productNumber, price, stock;
    private String image, description;
    @JsonBackReference
    private Set<OrderProduct> orders = new HashSet<>();

    @JsonCreator
    public Product(int productNumber, int price, int stock, String image, String description) {
        this.productNumber = productNumber;
        this.price = price;
        this.stock = stock;
        this.image = image;
        this.description = description;
    }

    public int getProductNumber() {
        return productNumber;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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
