package uk.ac.brighton.uni.ch629.catshop;

import uk.ac.brighton.uni.ch629.catshop.subscriptions.SubscriptionCreator;
import uk.ac.brighton.uni.ch629.catshop.update.ShopDisplayUpdate;

public class Test {
    public static void main(String[] args) {
        new SubscriptionCreator(ShopDisplayUpdate.class, updateWrapper -> System.out.println(((ShopDisplayUpdate) updateWrapper.getUpdate()).getOrderID()));

        while (true) {
        }
    }
}
