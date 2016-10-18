package uk.ac.brighton.uni.ch629.catshop;

import com.google.gson.JsonObject;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.util.derby.sys.Sys;
import uk.ac.brighton.uni.ch629.catshop.database.tables.Product;

import java.sql.Connection;
import java.sql.DriverManager;

import static spark.Spark.get;

public class Server {
    public static void main(String[] args) {
        String userName = "root", password = "pass", url = "jdbc:mysql://localhost:3306/catshop";

        try(Connection conn = DriverManager.getConnection(url, userName, password)) {
            DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
            Result<Record> result = create.select().from(Product.PRODUCT).fetch();

            for(Record r : result) {
                Integer productNumber = r.getValue(Product.PRODUCT.PRODUCTNUMBER);
                String description = r.getValue(Product.PRODUCT.PRODUCTDESCRIPTION);
                System.out.println(productNumber + ": " + description);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        get("/", (req, res) -> {
            JsonObject jsonObject = new JsonObject();
            JsonObject insideObject = new JsonObject();
            insideObject.addProperty("IP Address", req.ip());
            insideObject.addProperty("name", "Hello");
            jsonObject.add("Test", insideObject);
            jsonObject.addProperty("poop", true);
            return jsonObject;
        });
    }
}