package uk.ac.brighton.uni.ch629.catshop.database.model.data.services.implementations;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.ac.brighton.uni.ch629.catshop.database.model.AuthToken;
import uk.ac.brighton.uni.ch629.catshop.database.model.data.repositories.AuthTokenRepository;
import uk.ac.brighton.uni.ch629.catshop.database.model.data.services.interfaces.AuthTokenService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AuthTokenServiceImpl implements AuthTokenService {
    @Resource
    private AuthTokenRepository authTokenRepository;

    @Override
    @Transactional
    public AuthToken create(AuthToken authToken) {
        return authTokenRepository.save(authToken);
    }

    @Override
    @Transactional
    public AuthToken delete(String token) {
        AuthToken deletedToken = authTokenRepository.findOne(token);
        if (deletedToken == null) return null;
        authTokenRepository.delete(deletedToken);
        return deletedToken;
    }

    @Override
    @Transactional
    public List<AuthToken> findAll() {
        return authTokenRepository.findAll();
    }

    @Override
    @Transactional
    public AuthToken update(AuthToken authToken) {
        AuthToken updatedToken = authTokenRepository.findOne(authToken.getToken());
        updatedToken.setAccepted(authToken.isAccepted());
        return updatedToken;
    }

    @Override
    @Transactional
    public AuthToken findByToken(String token) {
        return authTokenRepository.findOne(token);
    }
}
