package uk.ac.brighton.uni.ch629.catshop.spring.test.database;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ORDER")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "QUANTITY")
    private int quantity;

    @JsonIgnore
    @ManyToMany(mappedBy = "orders")
    private Set<Product> products;
}