package uk.ac.brighton.uni.ch629.catshop.database.model.data.services.interfaces;

import uk.ac.brighton.uni.ch629.catshop.database.model.AuthToken;

import java.util.List;

public interface AuthTokenService {
    AuthToken create(AuthToken authToken);

    AuthToken delete(String token);

    List<AuthToken> findAll();

    AuthToken update(AuthToken authToken);

    AuthToken findByToken(String token);
}
