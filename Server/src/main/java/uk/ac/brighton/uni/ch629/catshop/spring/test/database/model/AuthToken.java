package uk.ac.brighton.uni.ch629.catshop.spring.test.database.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AUTH_TOKEN")
@JsonAutoDetect
public class AuthToken {
    @Id
    @Column(name = "TOKEN")
    private String token;

    @Column(name = "ACCEPTED")
    private boolean accepted;

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
