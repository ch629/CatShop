package uk.ac.brighton.uni.ch629.catshop.subscription.update;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductDelete implements Update {
    private final int productNumber;

    @JsonCreator
    public ProductDelete(@JsonProperty("productNumber") int productNumber) {
        this.productNumber = productNumber;
    }

    public int getProductNumber() {
        return productNumber;
    }

    @Override
    public UpdateType getType() {
        return UpdateType.PRODUCT_DELETE;
    }
}