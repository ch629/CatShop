package uk.ac.brighton.uni.ch629.catshop.database;

import java.sql.Connection;

/**
 * The Database
 */
public class CatShop { //TODO: ThreadPool for SQL requests? -> Only do it for updates and stuff sent from clients
    private DBType dbType; //TODO: Dynamically pick which type of database? Using the Database.txt like Mike's?

    /**
     * Creates a new <code>Connection</code> to access the Database.
     * @return
     */
    public Connection createConnection() {
        return dbType.getConnection();
    }
}
