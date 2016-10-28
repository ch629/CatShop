package uk.ac.brighton.uni.ch629.catshop.spring.test.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AUTH_TOKEN")
public class AuthToken {
    @Id
    @Column(name = "TOKEN")
    private String token;

    @Column(name = "ACCEPTED")
    private boolean accepted;
}
