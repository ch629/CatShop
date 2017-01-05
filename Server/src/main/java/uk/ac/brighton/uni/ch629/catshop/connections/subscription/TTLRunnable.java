package uk.ac.brighton.uni.ch629.catshop.connections.subscription;

/**
 * Time To Live Runnable
 * Runs in a separate thread to run a method after a set time
 */
public class TTLRunnable implements Runnable { //TODO: This may not be used anymore so could be deprecated
    private int timeToLive;
    private Runnable onFinish;

    /**
     * A Runnable to run a method after <code>timeToLive</code> seconds
     *
     * @param timeToLive In seconds
     * @param onFinish   Runnable to run when the time is over
     */
    public TTLRunnable(int timeToLive, Runnable onFinish) {
        this.timeToLive = timeToLive;
        this.onFinish = onFinish;
    }

    public static void makeThread(int timeToLive, Runnable onFinish) {
        new Thread(new TTLRunnable(timeToLive, onFinish)).start();
    }

    @Override
    public void run() {
        for (; timeToLive >= 0; timeToLive--) {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        onFinish.run();
    }
}