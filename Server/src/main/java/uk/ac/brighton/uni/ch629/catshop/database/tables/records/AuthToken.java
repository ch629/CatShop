package uk.ac.brighton.uni.ch629.catshop.database.tables.records;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import uk.ac.brighton.uni.ch629.catshop.database.CatShop;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AuthToken {
    private static CatShop database = new CatShop();
    //TODO: Last Used time?
    //TODO: Logging of tasks done by this auth token?
    private String token;
    private boolean accepted = false;

    private AuthToken(String token, boolean accepted) {
        this.token = token;
        this.accepted = accepted;
    }

    public static AuthToken getAuthToken(String token) { //TODO: Maybe this should just be hasToken, because I will already have the token at this point.
        String sql = "SELECT * FROM AuthToken WHERE Token='%s';";
        try (Connection connection = database.createConnection(); Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(String.format(sql, token));
            if (rs.next()) {
                return new AuthToken(rs.getString("Token"), rs.getBoolean("Accepted"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isTokenAccepted(String token) {
        AuthToken authToken = getAuthToken(token);
        return authToken != null && authToken.isAccepted();
    }

    public static void addToken(String token) {
        String sql = "INSERT INTO AuthToken VALUES('%s', %b);";
        AuthToken authToken = new AuthToken(token, false);
        database.executeUpdate(sql, authToken.token, authToken.accepted);
    }

    public static List<AuthToken> getAll() {
        String sql = "SELECT * FROM AuthToken;";
        List<AuthToken> tokens = new ArrayList<>();
        try (Connection connection = database.createConnection(); Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                tokens.add(new AuthToken(rs.getString("Token"), rs.getBoolean("Accepted")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tokens;
    }

    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS AuthToken(Token VARCHAR(16) PRIMARY KEY, Accepted BIT(1) NOT NULL);";
        database.executeUpdate(sql);
    }

    public static void dropTable() {
        String sql = "DROP TABLE IF EXISTS AuthToken;";
        database.executeUpdate(sql);
    }

    public static boolean hasToken(String token) {
        return getAuthToken(token) != null;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public String getToken() {
        return token;
    }

    public void create() {
        throw new NotImplementedException();
    }

    public void update() {
        String sql = "UPDATE AuthToken SET Accepted=%b WHERE Token='%s';";
        database.executeUpdate(sql, accepted, token);
    }

    public void delete() {
        throw new NotImplementedException();
    }

    public void accept() {
        accepted = true;
    }
} //TODO: Auth Access Enum? Allows only specific access to specific pages?