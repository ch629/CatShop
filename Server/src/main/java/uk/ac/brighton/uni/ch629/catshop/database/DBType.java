package uk.ac.brighton.uni.ch629.catshop.database;

import uk.ac.brighton.uni.ch629.catshop.Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum DBType {
    MYSQL("jdbc:mysql://", "localhost:3306/catshop?useSSL=false", "root", "pass"),
    SQLITE("jdbc:sqlite:", "catshop.db", null, null),
    DERBY("jdbc:derby:", "catshop.db", null, null),
    ODBC("jdbc:odbc:", "catshop.db", null, null);

    private String connectionPrefix, url, username, password;

    DBType(String connectionPrefix, String url, String username, String password) {
        this.connectionPrefix = connectionPrefix;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    /**
     * Get a new <code>Connection</code> to the Database
     *
     * @return
     */
    public Connection getConnection() { //TODO: Maybe just keep throws SQLException, as the statement throws one anyway
        try {
            return DriverManager.getConnection(connectionPrefix + url, username, password);
        } catch (SQLException e) {
            Server.getLogger().error("Unable to create Connection to Database with URL: {}.", url);
        }
        return null;
    }

    public Connection getConnectionException() throws SQLException {
        return DriverManager.getConnection(connectionPrefix + url, username, password);
    }
}