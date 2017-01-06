package uk.ac.brighton.uni.ch629.catshop.subscriptions;

import uk.ac.brighton.uni.ch629.catshop.update.UpdateWrapper;

public interface SubscriptionClientRunnable {
    void onUpdateReceived(UpdateWrapper updateWrapper);
}