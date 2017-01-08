package uk.ac.brighton.uni.ch629.catshop;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import uk.ac.brighton.uni.ch629.catshop.data.Product;

public class CashierTableRow {
    private final SimpleStringProperty name;
    private final SimpleIntegerProperty quantity;
    private final SimpleIntegerProperty stock;
    private final SimpleFloatProperty totalPrice;
    private float pricePer;

    public CashierTableRow(Product product, int quantity) {
        name = new SimpleStringProperty(product.getDescription());
        this.quantity = new SimpleIntegerProperty(quantity);
        this.stock = new SimpleIntegerProperty(product.getStock());
        pricePer = product.getPrice();
        totalPrice = new SimpleFloatProperty(pricePer * quantity);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getQuantity() {
        return quantity.get();
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
        totalPrice.set(pricePer * quantity);
    }

    public float getTotalPrice() {
        return totalPrice.get();
    }

    public float getPricePer() {
        return pricePer;
    }

    public void setPricePer(float price) {
        this.pricePer = price;
        totalPrice.set(pricePer * quantity.get());
    }

    public int getStock() {
        return stock.get();
    }

    public void setStock(int stock) {
        this.stock.set(stock);
    }
}