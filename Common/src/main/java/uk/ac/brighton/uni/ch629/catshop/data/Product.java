package uk.ac.brighton.uni.ch629.catshop.data;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@JsonAutoDetect
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    private int productNumber, stock;
    private float price;
    private String description, image;

    @JsonIgnore
    private Set<OrderProduct> orderProducts = new HashSet<>();

    public Product() {
    }

    @JsonCreator
    public Product(@JsonProperty("description") String description,
                   @JsonProperty("price") float price,
                   @JsonProperty("stock") int stock,
                   @JsonProperty("image") String image) {
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.image = image;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(int productNumber) {
        this.productNumber = productNumber;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Set<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(Set<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }
}