package uk.ac.brighton.uni.ch629.catshop.spring.test.database.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
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
    @JsonIgnore
    private List<OrderProduct> orderProducts = new ArrayList<>();

    public int getOrderID() {
        return orderID;
    }

    public List<Product> getProducts() {
        return orderProducts.stream().map(OrderProduct::getProduct).collect(Collectors.toList());
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }
}