package uk.ac.brighton.uni.ch629.catshop;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import uk.ac.brighton.uni.ch629.catshop.model.Order;
import uk.ac.brighton.uni.ch629.catshop.model.Product;

import java.util.HashMap;

public class Basket extends HashMap<Product, Integer> {

    public void add(Product product, int quantity) {
        if (quantity > 0) {
            int productQuantity = quantity;
            if (containsKey(product)) productQuantity += get(product);
            put(product, productQuantity);
        }
    }

    public void add(Product product) {
        add(product, 1);
    }

    public void removeProduct(Product product) {
        remove(product);
    }

    public void removeQuantity(Product product, int quantity) {
        if (containsKey(product)) {
            int productQuantity = get(product) - quantity;
            if (productQuantity <= 0) remove(product);
            else put(product, productQuantity);
        }
    }

    public void setQuantity(Product product, int quantity) {
        if (quantity > 0 && containsKey(product)) put(product, quantity);
    }

    public Order toOrder() {
        throw new NotImplementedException();
    }

    public void sendToServer() { //NOTE: Should I use this method? It will just send the basket as an order to the server, which is then sent to the Warehouse
        throw new NotImplementedException();
    }
}