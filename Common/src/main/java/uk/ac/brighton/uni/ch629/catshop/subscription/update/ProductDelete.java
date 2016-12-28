package uk.ac.brighton.uni.ch629.catshop.subscription.update;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductDelete extends Update {
    private final int productNumber;

    @JsonCreator
    public ProductDelete(@JsonProperty("productNumber") int productNumber) {
        super(UpdateType.DELETE);
        this.productNumber = productNumber;
    }

    public int getProductNumber() {
        return productNumber;
    }
}