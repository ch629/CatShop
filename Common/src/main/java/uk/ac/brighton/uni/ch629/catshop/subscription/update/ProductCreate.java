package uk.ac.brighton.uni.ch629.catshop.subscription.update;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import uk.ac.brighton.uni.ch629.catshop.data.Product;

public class ProductCreate implements Update {
    private final Product product;

    @JsonCreator
    public ProductCreate(@JsonProperty("product") Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public UpdateType getType() {
        return UpdateType.PRODUCT_CREATE;
    }
}