package uk.ac.brighton.uni.ch629.catshop.spring.test;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "product")
@JsonAutoDetect
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductNumber")
    @JsonProperty("productNumber")
    private int productNumber;

    @Column(name = "ProductImage")
    @JsonProperty("image")
    private String image;

    @Column(name = "ProductDescription")
    @JsonProperty("description")
    private String description;

    @Column(name = "ProductPrice")
    @JsonProperty("price")
    private double price;

    @Column(name = "ProductStock")
    @JsonProperty("stock")
    private int stock;

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

    public void setProductNumber(int productNumber) { //TODO: Remove this if possible
        this.productNumber = productNumber;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}