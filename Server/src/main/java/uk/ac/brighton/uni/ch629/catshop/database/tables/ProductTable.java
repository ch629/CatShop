package uk.ac.brighton.uni.ch629.catshop.database.tables;

import uk.ac.brighton.uni.ch629.catshop.database.CatShop;

public class ProductTable {
    private static final String TABLE_NAME = "Product";
    private final CatShop database;

    public ProductTable(CatShop database) {
        this.database = database;
    }
}