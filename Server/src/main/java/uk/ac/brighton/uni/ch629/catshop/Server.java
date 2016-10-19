package uk.ac.brighton.uni.ch629.catshop;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import uk.ac.brighton.uni.ch629.catshop.database.tables.Product;
import uk.ac.brighton.uni.ch629.catshop.database.tables.records.ProductRecord;

import java.sql.Connection;
import java.sql.DriverManager;

import static spark.Spark.get;

public class Server {
    public static void main(String[] args) {
        String userName = "root", password = "pass", url = "jdbc:mysql://localhost:3306/catshop";

        get("/product/all", (req, res) -> {
            try (Connection conn = DriverManager.getConnection(url, userName, password)) {
                DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
                Result<Record> result = create.select().from(Product.PRODUCT).fetch();

                JsonArray array = new JsonArray();
                for (Record r : result) {
                    ProductRecord record = (ProductRecord) r;
                    JsonObject object = new JsonObject();

                    object.addProperty("number", record.getProductNumberInt());
                    object.addProperty("description", record.getProductDescription());
                    object.addProperty("image", record.getProductImage());
                    object.addProperty("stock", record.getProductStockInt());
//                    object.addProperty("price", record.getProductPriceDouble()); //NOTE: Commented out until values have been added to the db
                    array.add(object);
                }
                return array;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        });

        get("/product/:id", (req, res) -> {
            try (Connection conn = DriverManager.getConnection(url, userName, password)) {
                DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
                Result<Record> result = create.select().from(Product.PRODUCT).where(Product.PRODUCT.PRODUCT_NUMBER.equal(Integer.valueOf(req.params(":id")))).fetch();

                for (Record r : result) {
                    ProductRecord record = (ProductRecord) r;
                    JsonObject object = new JsonObject();
                    object.addProperty("description", record.getProductDescription());
                    object.addProperty("image", record.getProductImage());
                    object.addProperty("stock", record.getProductStockInt());
//                    object.addProperty("price", record.getProductPriceDouble());
                    return object;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "None Found";
        });
    }
}