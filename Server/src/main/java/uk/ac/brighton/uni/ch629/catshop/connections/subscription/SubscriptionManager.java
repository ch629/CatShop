package uk.ac.brighton.uni.ch629.catshop.connections.subscription;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import uk.ac.brighton.uni.ch629.catshop.update.Update;


public class SubscriptionManager {
    public static final SubscriptionManager INSTANCE = new SubscriptionManager();
    private final Multimap<String, Subscription> subscriptions = HashMultimap.create(); //TODO: Rather than using the String for the UpdateType I could hold them by the type of Client which in turn allows me to send the correct updates, this potentially could be less messy

    private SubscriptionManager() {
    }

    public static SubscriptionManager getInstance() {
        return INSTANCE;
    }

    public void addSubscription(String updateType, Subscription subscription) { //TODO: When adding the subscription after closing it, the server crashes
        synchronized (subscriptions) {
            subscriptions.put(updateType, subscription);
        }
    }

    public void sendUpdate(Update update) { //TODO: If I want to do the Response then add the TTLRunnable here
        synchronized (subscriptions) {
            subscriptions.get(update.getType()).forEach(sub -> sub.sendUpdate(update));
        }
    }

    public boolean removeSubscription(String updateType, Subscription subscription) {
        synchronized (subscriptions) {
            return subscriptions.get(updateType).remove(subscription);
        }
    }

    public void removeSubscription(Subscription subscription) {
        synchronized (subscriptions) {
            subscriptions.keySet().forEach(key -> subscriptions.get(key).remove(subscription));
        }
    }
}