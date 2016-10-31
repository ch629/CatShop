package uk.ac.brighton.uni.ch629.catshop.database.model.data.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.ac.brighton.uni.ch629.catshop.database.model.AuthToken;

import java.util.List;

@Repository
public interface AuthTokenRepository extends CrudRepository<AuthToken, String> {
    List<AuthToken> findAll();

    AuthToken findByToken(String token);
}
