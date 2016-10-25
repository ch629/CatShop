package uk.ac.brighton.uni.ch629.catshop.spring.test.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ORDER_PRODUCT")
public class OrderProduct {
    @ManyToOne
    private Order order;

    @ManyToOne
    private Product product;

    @Column(name = "QUANTITY")
    private int quantity;
}
