package uk.ac.brighton.uni.ch629.catshop.connections;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import uk.ac.brighton.uni.ch629.catshop.update.Update;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

public class SubscriptionManager { //TODO: Potentially it may be easier to redo most of this, to clean it all up and make it as efficient as possible (Especially objects sent between server and client)
    public static final SubscriptionManager INSTANCE = new SubscriptionManager(); //TODO: Could also look into Google Guava's EventBus, but that would be more just client based rather than Client-Server
    private static final int TIMEOUT = 120;
    private final Set<Subscription> responses = new ConcurrentSkipListSet<>();
    private final Multimap<String, Subscription> subscriptionsNew = HashMultimap.create(); //HashSet values TODO: Might need a LIFO Queue to handle sending updates

    private SubscriptionManager() {
    }

    public static SubscriptionManager getInstance() {
        return INSTANCE;
    }

    public void addSubscription(Subscription subscription) {
        subscription.getUpdateTypes().forEach(type -> subscriptionsNew.put(type, subscription));
    }

    private boolean removeSubscriptions(String ip, int port) {
        boolean ret = false;

        synchronized (subscriptionsNew) {
            Multimap<String, Subscription> filteredSubs = Multimaps.filterEntries(subscriptionsNew, sub -> {
                assert sub != null;
                return sub.getValue().getIP().equals(ip) && sub.getValue().getPort() == port;
            });
            filteredSubs.entries().forEach(entry -> subscriptionsNew.remove(entry.getKey(), entry.getValue()));
        }
        return ret; //TODO: Check this return, as it's always false
    }

    private boolean removeSubscription(Subscription subscription) {
        boolean ret = false;

        synchronized (subscriptionsNew) {
            subscription.getUpdateTypes().forEach(subType -> subscriptionsNew.remove(subType, subscription));
        }
        return ret;
    }

    private void sendUpdateToSubscription(Subscription subscription, Update update) {
        subscription.sendUpdate(update);
        TTLRunnable.makeThread(TIMEOUT, () -> {
            if (!hadResponse(subscription)) removeSubscription(subscription);
        });
    }

    public void addResponse(Subscription subscription) {
        synchronized (responses) {
            responses.add(subscription);
        }
    }

    private boolean hadResponse(Subscription subscription) {
        return responses.remove(subscription);
    }

    private void sendUpdate(Update update) {
        synchronized (subscriptionsNew) {
            subscriptionsNew.get(update.getType().getSimpleName()).forEach(subscription -> subscription.sendUpdate(update));
        }
    }
}