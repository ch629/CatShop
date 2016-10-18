package uk.ac.brighton.uni.ch629.catshop;

import com.google.gson.JsonObject;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import uk.ac.brighton.uni.ch629.catshop.database.tables.Product;

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

                JsonObject jsonObject = new JsonObject();
                for (Record r : result) {
                    JsonObject object = new JsonObject();
                    object.addProperty("description", r.getValue(Product.PRODUCT.DESCRIPTION));
                    object.addProperty("image", r.getValue(Product.PRODUCT.IMAGE));
                    object.addProperty("stock", r.getValue(Product.PRODUCT.STOCK));
                    jsonObject.add(r.getValue(Product.PRODUCT.PRODUCT_NUMBER).toString(), object);
                }
                return jsonObject;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        });

        get("/product/:id", (req, res) -> {
            try (Connection conn = DriverManager.getConnection(url, userName, password)) {
                DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
                Result<Record> result = create.select().from(Product.PRODUCT).where(Product.PRODUCT.PRODUCT_NUMBER.equal(Integer.valueOf(req.params(":id")))).fetch();

                JsonObject jsonObject = new JsonObject();
                for (Record r : result) {
                    JsonObject object = new JsonObject();
                    object.addProperty("description", r.getValue(Product.PRODUCT.DESCRIPTION));
                    object.addProperty("image", r.getValue(Product.PRODUCT.IMAGE));
                    object.addProperty("stock", r.getValue(Product.PRODUCT.STOCK));
                    return object;
//                    jsonObject.add(r.getValue(Product.PRODUCT.PRODUCT_NUMBER).toString(), object);
                }
                return jsonObject;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        });
    }
}