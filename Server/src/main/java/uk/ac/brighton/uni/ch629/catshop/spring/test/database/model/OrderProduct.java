package uk.ac.brighton.uni.ch629.catshop.spring.test.database.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ORDER_PRODUCT")
public class OrderProduct implements Serializable {
    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PRODUCT_NUMBER", insertable = false, updatable = false, nullable = false)
    private Product product;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ORDER_ID", insertable = false, updatable = false, nullable = false)
    private Order order;

    @Column(name = "QUANTITY")
    private int quantity;

    public Product getProduct() {
        return product;
    }

    public Order getOrder() {
        return order;
    }

    public int getQuantity() {
        return quantity;
    }
}
