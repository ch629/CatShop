package uk.ac.brighton.uni.ch629.catshop.subscription.update;

/**
 * The type of update (Either Creating a new Product/Order, Updating Images, Stocks etc and Deleting Products/Orders)
 */
public enum UpdateType {
    ORDER_CREATE, ORDER_UPDATE, ORDER_DELETE,
    PRODUCT_CREATE, PRODUCT_UPDATE, PRODUCT_DELETE
}