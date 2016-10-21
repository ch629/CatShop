package uk.ac.brighton.uni.ch629.catshop.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The Database
 */
public class CatShop { //TODO: ThreadPool for SQL requests? -> Only do it for updates and stuff sent from clients
    private DBType dbType = DBType.MYSQL; //TODO: Dynamically pick which type of database? Using the Database.txt like Mike's? -> Will need the drivers and to change some of the SQL (SQLite uses AUTOINCREMENT and INTEGER rather thant AUTO_INCREMENT and INT)
    //TODO: Might just be best to stick this on a PaaS to work on, so I can always use MySQL. (Maybe add NoSQL)
    //TODO: Stick Orders in the Database? Would mean if the server or any client restarted, it would still be processing.

    /**
     * Execute an Update in the Database.
     *
     * @param sql The SQL of the Update
     * @return The number of rows updated
     */
    public int executeUpdate(String sql) {
        try (Connection c = createConnection(); Statement stmt = c.createStatement()) {
            return stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Executes a formatted Update
     * @param sql The SQL to execute
     * @param args The format specifiers of the format
     * @return The number of rows updated
     */
    public int executeUpdate(String sql, Object... args) {
        return executeUpdate(String.format(sql, args));
    }

    /**
     * Creates a new <code>Connection</code> to access the Database.
     * @return The new <code>Connection</code> created from the Database
     * @throws SQLException
     */
    public Connection createConnection() throws SQLException {
        return dbType.getConnection();
    }
}
