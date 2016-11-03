package uk.ac.brighton.uni.ch629.catshop.database.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "AUTH_TOKEN")
@JsonAutoDetect
public class AuthToken {
    @Id
    @Column(name = "TOKEN")
    private String token;

    @Column(name = "ACCEPTED")
    private boolean accepted;

    @Column(name = "DATE_REQUESTED", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date requestDate = new Date(); //Initialize it to the Date created.

    public AuthToken() {
        this.accepted = false;
    }

    public AuthToken(String token) {
        this(token, false);
    }

    @JsonCreator
    public AuthToken(@JsonProperty("token") String token,
                     @JsonProperty("accepted") boolean accepted) {
        this.token = token;
        this.accepted = accepted;
    }

    public String getToken() {
        return token;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public AuthToken setAccepted(boolean accepted) {
        this.accepted = accepted;
        return this;
    }
}
