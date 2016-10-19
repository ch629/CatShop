package uk.ac.brighton.uni.ch629.catshop.database;

public class RequestTask implements Runnable {
    private final CatShop database;

    public RequestTask(CatShop database) {
        this.database = database;
    }

    @Override
    public void run() {

    }
}