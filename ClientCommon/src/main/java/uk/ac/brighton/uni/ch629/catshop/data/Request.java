package uk.ac.brighton.uni.ch629.catshop.data;

import java.io.Serializable;

/**
 * A Request to be sent to the server.
 */
public class Request<T extends Serializable> {
    private String authToken;
    private T data;
}
