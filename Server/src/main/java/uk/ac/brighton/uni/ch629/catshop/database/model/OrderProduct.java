package uk.ac.brighton.uni.ch629.catshop.database.model;

import com.fasterxml.jackson.annotation.*;
import javafx.util.Pair;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ORDER_PRODUCT")
public class OrderProduct implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @ManyToOne
    @JoinColumn(name = "PRODUCT_NUMBER")
    private Product product;

    @Id
    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    @JsonBackReference
    private Order order;

    @Column(name = "QUANTITY")
    private int quantity;

    public OrderProduct() {
    }

    @JsonCreator
    public OrderProduct(@JsonProperty("product") Product product,
                        @JsonProperty("order") Order order,
                        @JsonProperty("quantity") int quantity) {
        this.product = product;
        this.order = order;
        this.quantity = quantity;
    }

    @JsonGetter("product")
    public Product getProduct() {
        return product;
    }

    @JsonSetter("product")
    public void setProduct(Product product) {
        this.product = product;
    }

    @JsonIgnore
    public Pair<Product, Integer> getAsPair() {
        return new Pair<>(product, quantity);
    }

    @JsonIgnore
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @JsonGetter("quantity")
    public int getQuantity() {
        return quantity;
    }

    @JsonSetter("quantity")
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
