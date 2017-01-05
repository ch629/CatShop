package uk.ac.brighton.uni.ch629.catshop.update;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import uk.ac.brighton.uni.ch629.catshop.data.Basket;

@JsonAutoDetect
public class AddOrder implements Update { //Cashier -> Server -> Warehouse
    private final Basket basket;

    public AddOrder() {
        basket = new Basket();
    }

    @JsonCreator
    public AddOrder(@JsonProperty("basket") Basket basket) {
        this.basket = basket;
    }

    @Override //TODO: Could JsonIgnore this
    public String getType() {
        return "AddOrder";
    }

    public Basket getBasket() {
        return basket;
    }
}
