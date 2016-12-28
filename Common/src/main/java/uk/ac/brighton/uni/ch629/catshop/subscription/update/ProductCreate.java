package uk.ac.brighton.uni.ch629.catshop.subscription.update;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import uk.ac.brighton.uni.ch629.catshop.data.Product;

public class ProductCreate extends Update {
    private final Product product;

    @JsonCreator
    public ProductCreate(@JsonProperty("product") Product product) {
        super(UpdateType.CREATE);
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
}