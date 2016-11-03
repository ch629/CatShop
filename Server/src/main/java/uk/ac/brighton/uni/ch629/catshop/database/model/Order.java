package uk.ac.brighton.uni.ch629.catshop.database.model;

import com.fasterxml.jackson.annotation.*;
import javafx.util.Pair;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "ORDERS") //NOTE: Order is a reserved word.
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private int orderID;

    @OneToMany(mappedBy = "order"/*, fetch = FetchType.EAGER*/, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<OrderProduct> orderProducts = new HashSet<>();

    public Order() {
    }

    public Order(OrderProduct... orderProducts) {
        this.orderProducts = new HashSet<>(Arrays.asList(orderProducts));
    }

    @JsonGetter("orderID")
    public int getOrderID() {
        return orderID;
    }

    @JsonGetter("products")
    public Set<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    @JsonSetter("products")
    public Order setOrderProducts(Set<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
        return this;
    }

    public Order addProduct(Product product, int quantity) {
        orderProducts.add(new OrderProduct(product, this, quantity));
        return this;
    }

    public Order addOrderProduct(OrderProduct orderProduct) {
        orderProducts.size();
        orderProducts.add(orderProduct);
        return this;
    }

    @JsonIgnore
    public List<Pair<Product, Integer>> getProducts() {
        return orderProducts.stream().map(OrderProduct::getAsPair).collect(Collectors.toList());
    }

    @JsonIgnore
    public Order setProducts(Set<Pair<Product, Integer>> products) {
        orderProducts.clear();
        products.forEach(product -> orderProducts.add(new OrderProduct(product.getKey(), this, product.getValue())));
        return this;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (orderID != order.orderID) return false;
        return orderProducts != null ? orderProducts.equals(order.orderProducts) : order.orderProducts == null;
    }

    @Override
    public int hashCode() {
        return orderID;
    }
}