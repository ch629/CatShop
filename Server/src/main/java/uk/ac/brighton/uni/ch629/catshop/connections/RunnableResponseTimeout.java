package uk.ac.brighton.uni.ch629.catshop.connections;

public class RunnableResponseTimeout implements Runnable {
    private static final int TIMEOUT = 100;
    private final ServerUpdateResponse serverUpdateResponse;
    private final Subscription subscription;

    public RunnableResponseTimeout(ServerUpdateResponse serverUpdateResponse, Subscription subscription) {
        this.serverUpdateResponse = serverUpdateResponse;
        this.subscription = subscription;
    }

    @Override
    public void run() {
        for (int i = 0; i < TIMEOUT; i++) {
        }
    }
}
