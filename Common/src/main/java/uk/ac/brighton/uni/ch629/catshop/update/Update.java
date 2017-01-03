package uk.ac.brighton.uni.ch629.catshop.update;

public interface Update {
    Class<? extends Update> getType();
}
