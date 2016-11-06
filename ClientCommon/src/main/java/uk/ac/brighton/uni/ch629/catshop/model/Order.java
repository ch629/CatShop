package uk.ac.brighton.uni.ch629.catshop.model;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@JsonAutoDetect
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    private int orderID;

    @JsonBackReference
    private Set<OrderProduct> products = new HashSet<>();

    @JsonCreator
    public Order(int orderID, Set<OrderProduct> products) {
        this.orderID = orderID;
        this.products = products;
    }

    public int getOrderID() {
        return orderID;
    }

    public Set<OrderProduct> getOrderProducts() {
        return products;
    }

    private boolean hasProduct(Product product) {
        return products.stream().anyMatch(p -> p.getProduct().equals(product));
    }

    private OrderProduct getOrderProductWithProduct(Product product) {
        return getOrderProductWithProductNumber(product.getProductNumber());
    }

    private OrderProduct getOrderProductWithProductNumber(int productNumber) {
        return products.stream().filter(p -> p.getProduct().getProductNumber() == productNumber).findFirst().orElse(null);
    }

    public void addProduct(Product product, int quantity) {
        OrderProduct orderProduct = getOrderProductWithProduct(product);
        if (orderProduct != null) orderProduct.changeQuantity(quantity);
        else orderProduct = new OrderProduct(this, product, quantity);
        products.add(orderProduct);
    }

    public boolean removeProduct(Product product, int quantity) {
        OrderProduct orderProduct = getOrderProductWithProduct(product);
        if (orderProduct != null) {
            orderProduct.changeQuantity(-quantity);
            if (orderProduct.getQuantity() <= 0) removeProduct(product);
            return true;
        }
        return false;
    }

    public boolean removeProduct(Product product) {
        if (hasProduct(product)) {
            Iterator<OrderProduct> itr = products.iterator();
            while (itr.hasNext()) {
                OrderProduct orderProduct = itr.next();
                if (orderProduct.getProduct().equals(product)) {
                    itr.remove();
                    return true;
                }
            }
        }
        return false;
    }

    public List<Product> getProducts() {
        return products.stream().map(OrderProduct::getProduct).collect(Collectors.toList());
    }

    public void setProducts(Set<OrderProduct> products) {
        this.products = products;
    }
}
