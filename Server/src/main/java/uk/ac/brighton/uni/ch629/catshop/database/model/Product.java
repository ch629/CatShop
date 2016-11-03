package uk.ac.brighton.uni.ch629.catshop.database.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PRODUCT")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

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

//    @OneToMany(mappedBy = "product")
//    @NotFound(action = NotFoundAction.IGNORE)
//    @JsonIgnore //Not sure why this isn't just ignoring the lazy loading, so have to put JsonIgnoreProperties annotation
//    @JsonManagedReference
//    private List<OrderProduct> orderProducts = new ArrayList<>(); //NOTE: Could remove this, as product's don't really need to know about orders directly; maybe need it to see how many have been sold.

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

    @JsonGetter("productNumber")
    public int getProductNumber() {
        return productNumber;
    }

    @JsonGetter("image")
    public String getImage() {
        return image;
    }

    @JsonSetter("image")
    public Product setImage(String image) {
        this.image = image;
        return this;
    }

    @JsonGetter("description")
    public String getDescription() {
        return description;
    }

    @JsonSetter("description")
    public Product setDescription(String description) {
        this.description = description;
        return this;
    }

    @JsonGetter("price")
    public double getPrice() {
        return price;
    }

    @JsonSetter("price")
    public Product setPrice(double price) {
        this.price = price;
        return this;
    }

    @JsonGetter("stock")
    public int getStock() {
        return stock;
    }

    @JsonSetter("stock")
    public Product setStock(int stock) {
        this.stock = stock;
        return this;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productNumber=" + productNumber +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (productNumber != product.productNumber) return false;
        if (Double.compare(product.price, price) != 0) return false;
        if (stock != product.stock) return false;
        if (image != null ? !image.equals(product.image) : product.image != null) return false;
        return description != null ? description.equals(product.description) : product.description == null;

    }

    @Override
    public int hashCode() {
        return productNumber;
    }
}