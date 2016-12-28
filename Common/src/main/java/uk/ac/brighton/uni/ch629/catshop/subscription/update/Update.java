package uk.ac.brighton.uni.ch629.catshop.subscription.update;

public abstract class Update {
    private UpdateType type;

    public Update(UpdateType type) {
        this.type = type;
    }

    public UpdateType getType() {
        return type;
    }
}
