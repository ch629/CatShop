package uk.ac.brighton.uni.ch629.catshop.connections;

/**
 * Time To Live Runnable
 * Runs in a separate thread to run a method after a set time
 */
public class TTLRunnable implements Runnable {
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

    @Override
    public void run() {
        while (timeToLive > 0) {
            timeToLive--;
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        onFinish.run();
    }
}