package uk.ac.brighton.uni.ch629.catshop.database.model.dao.interfaces;

import uk.ac.brighton.uni.ch629.catshop.database.model.AuthToken;

import java.util.List;

public interface IAuthTokenDao {
    List<AuthToken> getAuthTokens();

    AuthToken getAuthToken(String token);

    void updateAuthToken(AuthToken token);

    void deleteAuthToken(String token);

    void deleteAuthToken(AuthToken token);
}
