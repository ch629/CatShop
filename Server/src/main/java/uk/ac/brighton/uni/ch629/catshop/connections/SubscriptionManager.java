package uk.ac.brighton.uni.ch629.catshop.connections;

import uk.ac.brighton.uni.ch629.catshop.subscription.SubscriptionType;
import uk.ac.brighton.uni.ch629.catshop.subscription.update.OrderUpdate;
import uk.ac.brighton.uni.ch629.catshop.subscription.update.Update;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

public class SubscriptionManager {
    public static final SubscriptionManager INSTANCE = new SubscriptionManager();
    private static final int TIMEOUT = 120;
    private final Set<Subscription>
            productSubscriptions = new ConcurrentSkipListSet<>(),
            orderSubscriptions = new ConcurrentSkipListSet<>();
    private final Set<String> responses = new ConcurrentSkipListSet<>();

    private SubscriptionManager() {
    }

    public static SubscriptionManager getInstance() {
        return INSTANCE;
    }

    private Set<Subscription> getSubscriptionSet(SubscriptionType type) {
        switch (type) {
            case PRODUCT:
                return productSubscriptions;
            case ORDER:
                return orderSubscriptions;
        }
        return null;
    }

    public void addSubscription(SubscriptionType type, Subscription subscription) {
        Set<Subscription> subscriptions = getSubscriptionSet(type);

        synchronized (Objects.requireNonNull(subscriptions)) { //This apparently works fine, as it still references the Set.
            subscriptions.add(subscription);
        }
    }

    private boolean removeSubscription(String ip, int port) {
        boolean ret;
        synchronized (productSubscriptions) {
            ret = productSubscriptions.removeIf(subscription -> subscription.getIP().equals(ip) && subscription.getPort() == port);
        }

        synchronized (orderSubscriptions) {
            boolean tmp = orderSubscriptions.removeIf(subscription -> subscription.getIP().equals(ip) && subscription.getPort() == port);
            if (!ret) ret = tmp;
        }
        return ret;
    }

    private boolean removeSubscription(Subscription subscription) {
        boolean ret = false;

        if (subscription.hasType(SubscriptionType.PRODUCT)) { //NOTE: May be more efficient this way, also it may be more efficient to just convert the list into a List then check that list twice, as currently it converts it twice
            synchronized (productSubscriptions) {
                ret = productSubscriptions.remove(subscription);
            }
        }

        if (subscription.hasType(SubscriptionType.ORDER)) {
            synchronized (orderSubscriptions) {
                boolean tmp = orderSubscriptions.remove(subscription);
                if (!ret) ret = tmp;
            }
        }

        /*synchronized (productSubscriptions) {
            ret = productSubscriptions.remove(subscription);
        }

        synchronized (orderSubscriptions) {
            boolean tmp = orderSubscriptions.remove(subscription);
            if (!ret) ret = tmp;
        }*/
        return ret;
    }

    protected boolean timeoutSubscription(String ip, int port) {
        return removeSubscription(ip, port);
    }

    private void sendUpdateToSubscription(Subscription subscription, Update update) {
        subscription.sendUpdate(update);
        TTLRunnable.makeThread(TIMEOUT, () -> {
            if (!hadResponse(subscription)) removeSubscription(subscription);
        });
    }

    public void addResponse(String ip, int port) {
        synchronized (responses) {
            responses.add(ip + ":" + port);
        }
    }

    private boolean hadResponse(Subscription subscription) {
        return responses.remove(subscription.getIP() + ":" + subscription.getPort());
    }

    public void sendUpdate(Update update) {
        if (update instanceof OrderUpdate) {
            synchronized (orderSubscriptions) {
                orderSubscriptions.forEach(subscription -> sendUpdateToSubscription(subscription, update));
            }
        } else {
            synchronized (productSubscriptions) {
                productSubscriptions.forEach(subscription -> sendUpdateToSubscription(subscription, update));
            }
        }
    }
}