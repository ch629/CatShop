package uk.ac.brighton.uni.ch629.catshop.database;

import uk.ac.brighton.uni.ch629.catshop.database.tables.ProductTable;

import java.sql.Connection;

/**
 * The Database
 */
public class CatShop { //TODO: ThreadPool for SQL requests?
    private final ProductTable PRODUCT_TABLE = new ProductTable(this);
    private DBType dbType; //TODO: Dynamically pick which type of database? Using the Database.txt like Mike's?

    /**
     * Creates a new <code>Connection</code> to access the Database.
     * @return
     */
    public Connection createConnection() {
        return dbType.getConnection();
    }
}
