package uk.ac.brighton.uni.ch629.catshop.data;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

@JsonAutoDetect
public class Basket {
    private final Map<Product, Integer> items;

    public Basket() {
        items = new HashMap<>();
    }

    @JsonCreator
    public Basket(@JsonProperty("items") Map<Product, Integer> items) {
        this.items = items;
    }

    /**
     * Add multiple items to the basket
     *
     * @param product  The product to add to the basket
     * @param quantity The amount of product to add to the basket
     */
    public void addItem(Product product, int quantity) {
        if (items.containsKey(product)) items.put(product, items.get(product) + quantity);
        else items.put(product, quantity);
    }

    /**
     * Add an item to the basket
     *
     * @param product The product to add to the basket
     */
    public void addItem(Product product) {
        addItem(product, 1);
    }

    /**
     * Removes the item completely from the basket
     *
     * @param product The product to remove
     * @return Whether a product was found
     */
    public boolean removeItem(Product product) {
        return items.remove(product) > 0;
    }

    /**
     * Removes a specified quantity from the Product in the Basket
     *
     * @param product  The Product to reduce the quantity
     * @param quantity The quantity to reduce by
     * @return Whether an item was removed
     */
    public boolean removeItem(Product product, int quantity) {
        if (items.containsKey(product)) {
            int oldQuantity = items.get(product);
            if (oldQuantity <= quantity) items.remove(product);
            else items.put(product, oldQuantity - quantity);
            return true;
        }
        return false;
    }

    /**
     * Reduces the quantity of an item by one
     *
     * @param product The Product to reduce the quantity
     * @return Whether the Product exists in the Basket
     */
    public boolean reduceQuantity(Product product) {
        if (items.containsKey(product)) {
            int quantity = items.get(product);
            if (quantity > 1) items.put(product, quantity - 1);
            else items.remove(product);
            return true;
        }
        return false;
    }

    /**
     * Increases the quantity of an item by one, if the item does not exist in the basket, it will be added.
     *
     * @param product The Product to increase the quantity
     */
    public void increaseQuantity(Product product) {
        if (items.containsKey(product)) {
            items.put(product, items.get(product) + 1); //TODO: Potentially check if there is enough stock
        } else addItem(product);
    }

    /**
     * Empties the Basket of all items
     */
    public void empty() {
        items.clear();
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public Order asOrder() {
        return new Order(getItems());
    }
}