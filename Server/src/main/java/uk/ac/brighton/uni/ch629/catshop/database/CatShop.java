package uk.ac.brighton.uni.ch629.catshop.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The Database
 */
public class CatShop { //TODO: ThreadPool for SQL requests? -> Only do it for updates and stuff sent from clients
    private DBType dbType = DBType.MYSQL; //TODO: Dynamically pick which type of database? Using the Database.txt like Mike's?

    /**
     * Creates a new <code>Connection</code> to access the Database.
     * @return
     */
    public Connection createConnection() {
        return dbType.getConnection();
    }

    public int executeUpdate(String sql) {
        try (Connection c = createConnectionException(); Statement stmt = c.createStatement()) {
            return stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }


    public Connection createConnectionException() throws SQLException {
        return dbType.getConnectionException();
    }
}
