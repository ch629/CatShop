package uk.ac.brighton.uni.ch629.catshop.database.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import javafx.util.Pair;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "ORDERS") //NOTE: Order is a reserved word.
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private int orderID;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    public Order() {
    }

    public Order(OrderProduct... orderProducts) {
        this.orderProducts = Arrays.asList(orderProducts);
    }

    @JsonGetter("orderID")
    public int getOrderID() {
        return orderID;
    }

    @JsonGetter("products")
    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    @JsonSetter("products")
    public Order setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
        return this;
    }

    public Order addProduct(Product product, int quantity) {
        orderProducts.add(new OrderProduct(product, this, quantity));
        return this;
    }

    public Order addOrderProduct(OrderProduct orderProduct) {
        orderProducts.add(orderProduct);
        return this;
    }

    public List<Pair<Product, Integer>> getProducts() {
        return orderProducts.stream().map(OrderProduct::getAsPair).collect(Collectors.toList());
    }

    public Order setProducts(List<Pair<Product, Integer>> products) {
        orderProducts.clear();
        products.forEach(product -> orderProducts.add(new OrderProduct(product.getKey(), this, product.getValue())));
        return this;
    }
}