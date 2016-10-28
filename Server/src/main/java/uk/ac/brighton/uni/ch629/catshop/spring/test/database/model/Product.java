package uk.ac.brighton.uni.ch629.catshop.spring.test.database.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "PRODUCT")
@JsonAutoDetect
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_NUMBER")
    private int productNumber;

    @Column(name = "PRODUCT_IMAGE")
    private String image;

    @Column(name = "PRODUCT_DESCRIPTION")
    private String description;

    @Column(name = "PRODUCT_PRICE")
    private double price;

    @Column(name = "PRODUCT_STOCK")
    private int stock;

    @ManyToMany
    private Set<Order> orders;

    public Product() {
    }

    public Product(String description, double price, int stock, String image) {
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.image = image;
    }

    @JsonCreator
    public Product(@JsonProperty("productNumber") int productNumber,
                   @JsonProperty("description") String description,
                   @JsonProperty("price") double price,
                   @JsonProperty("stock") int stock,
                   @JsonProperty("image") String image) {
        this.productNumber = productNumber;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.image = image;

    }

    public int getProductNumber() {
        return productNumber;
    }

    public String getImage() {
        return image;
    }

    public Product setImage(String image) {
        this.image = image;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Product setDescription(String description) {
        this.description = description;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public Product setPrice(double price) {
        this.price = price;
        return this;
    }

    public int getStock() {
        return stock;
    }

    public Product setStock(int stock) {
        this.stock = stock;
        return this;
    }
}