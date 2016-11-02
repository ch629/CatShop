package uk.ac.brighton.uni.ch629.catshop.database.model.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uk.ac.brighton.uni.ch629.catshop.database.model.AuthToken;

@Repository
public interface AuthTokenRepository extends JpaRepository<AuthToken, String> {
//    AuthToken findByToken(String token);
}
