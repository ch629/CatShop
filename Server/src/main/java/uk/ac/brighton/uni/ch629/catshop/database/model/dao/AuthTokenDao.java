package uk.ac.brighton.uni.ch629.catshop.database.model.dao;

import org.hibernate.Session;
import uk.ac.brighton.uni.ch629.catshop.database.HibernateUtil;
import uk.ac.brighton.uni.ch629.catshop.database.model.AuthToken;
import uk.ac.brighton.uni.ch629.catshop.database.model.dao.interfaces.IAuthTokenDao;

import java.util.List;

public class AuthTokenDao implements IAuthTokenDao {
    @Override
    public List<AuthToken> getAuthTokens() {
        return HibernateUtil.getAll("AuthToken");
    }

    @Override
    public AuthToken getAuthToken(String token) {
        return HibernateUtil.get(AuthToken.class, token);
    }

    @Override
    public void updateAuthToken(AuthToken token) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        AuthToken oldToken = session.load(AuthToken.class, token.getToken());
        oldToken.setAccepted(token.isAccepted());
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteAuthToken(String token) {
        HibernateUtil.delete(AuthToken.class, token);
    }

    @Override
    public void deleteAuthToken(AuthToken token) {
        deleteAuthToken(token.getToken());
    }
}
