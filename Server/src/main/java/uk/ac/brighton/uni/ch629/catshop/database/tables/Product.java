package uk.ac.brighton.uni.ch629.catshop.database.tables;

import org.jooq.*;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;
import uk.ac.brighton.uni.ch629.catshop.database.CatShop;
import uk.ac.brighton.uni.ch629.catshop.database.Keys;
import uk.ac.brighton.uni.ch629.catshop.database.tables.records.ProductRecord;

import javax.annotation.Generated;
import java.util.Arrays;
import java.util.List;

/**
 * This class is generated by jOOQ.
 */
@Generated(value = {"http://www.jooq.org", "3.4.1"},
        comments = "This class is generated by jOOQ")
public class Product extends TableImpl<ProductRecord> {

    /**
     * The singleton instance of <code>catshop.product</code>
     */
    public static final Product PRODUCT = new Product();
    private static final long serialVersionUID = -1916101091;
    /**
     * The column <code>catshop.product.ProductNumber</code>.
     */
    public final TableField<ProductRecord, Integer> PRODUCT_NUMBER = createField("ProductNumber", SQLDataType.INTEGER.nullable(false), this, "");
    /**
     * The column <code>catshop.product.ProductDescription</code>.
     */
    public final TableField<ProductRecord, String> DESCRIPTION = createField("ProductDescription", SQLDataType.VARCHAR.length(45).nullable(false), this, "");
    /**
     * The column <code>catshop.product.ProductImage</code>.
     */
    public final TableField<ProductRecord, String> IMAGE = createField("ProductImage", SQLDataType.VARCHAR.length(45).nullable(false), this, "");
    /**
     * The column <code>catshop.product.ProductStock</code>.
     */
    public final TableField<ProductRecord, Integer> STOCK = createField("ProductStock", SQLDataType.INTEGER.defaulted(true), this, "");
    /**
     * The column <code>catshop.product.ProductPrice</code>.
     */
    public final TableField<ProductRecord, java.lang.Double> PRICE = createField("ProductPrice", SQLDataType.DOUBLE, this, "");

    /**
     * Create a <code>catshop.product</code> table reference
     */
    public Product() {
        this("product", null);
    }

    /**
     * Create an aliased <code>catshop.product</code> table reference
     */
    public Product(String alias) {
        this(alias, Product.PRODUCT);
    }

    private Product(String alias, Table<ProductRecord> aliased) {
        this(alias, aliased, null);
    }

    private Product(String alias, Table<ProductRecord> aliased, Field<?>[] parameters) {
        super(alias, CatShop.CAT_SHOP, aliased, parameters, "");
    }

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ProductRecord> getRecordType() {
        return ProductRecord.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<ProductRecord, Integer> getIdentity() {
        return Keys.IDENTITY_PRODUCT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<ProductRecord> getPrimaryKey() {
        return Keys.KEY_PRODUCT_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<ProductRecord>> getKeys() {
        return Arrays.<UniqueKey<ProductRecord>>asList(Keys.KEY_PRODUCT_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Product as(String alias) {
        return new Product(alias, this);
    }

    /**
     * Rename this table
     */
    public Product rename(String name) {
        return new Product(name, null);
    }
}
