package uk.ac.brighton.uni.ch629.catshop;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.ac.brighton.uni.ch629.catshop.communication.Response;
import uk.ac.brighton.uni.ch629.catshop.communication.ResponseCode;
import uk.ac.brighton.uni.ch629.catshop.database.tables.records.Product;

import static spark.Spark.*;

public class Server { //TODO: Authentication using POST requests.
    public static void main(String[] args) { //TODO: Cache images with Google Guava?
        createDummyData(); //TODO: Use a request system to create correct JSON for the Request similar to HitBox's way (Use ResponseCode and maybe a StatusCode to check if the request was successful on client)
        get("/product/all", (req, res) -> {
            JsonArray array = new JsonArray();
            Product.getAll().forEach(product -> array.add(product.toJsonObject()));
            return new Response(ResponseCode.SUCCESS, array);
        });

        get("/product/:id", (req, res) -> {
            String idParam = req.params(":id");
            if (!isInt(idParam))
                return new Response(ResponseCode.INVALID_PARAMETER, "Expected an integer in parameter ':id' but received %s", idParam);
            Product product = Product.getProduct(Integer.parseInt(idParam));
            if (product == null)
                return new Response(ResponseCode.PRODUCT_NOT_FOUND, "No product found with id: %s", idParam);
            return new Response(ResponseCode.SUCCESS, product.toJsonObject());
        });

        boolean authenticated = true; //TODO: Authentication for /protected/ routes. (https://github.com/pac4j/spark-pac4j)
        before("/product/*", (req, res) -> {
            if (!authenticated) halt(401, "Unauthorised Access!");
        });

        post("/protected/product/add", (req, res) -> {
            Response response = Response.fromJson((JsonObject) new JsonParser().parse(req.body()));
            System.out.println(response.getResponseCode().toString());
            return "";
        });

        delete("/protected/product/:id", (req, res) -> {
            String productId = req.params(":id");
            Product product = Product.getProduct(Integer.parseInt(productId));
            if (product != null) {
                product.delete();
                return new Response(ResponseCode.SUCCESS, "Product %s was deleted successfully!", productId);
            }
            return new Response(ResponseCode.PRODUCT_NOT_FOUND, "No product found with id: %s", productId);
        });
    }

    public static void createDummyData() {
        Product.dropTable();
        Product.createTable();
        new Product("40 inch LED HD TV", 269.00d, 90, "pic0001.jpg").create();
        new Product("DAB Radio", 29.99d, 20, "pic0002.jpg").create();
        new Product("Toaster", 19.99d, 33, "pic0003.jpg").create();
        new Product("Watch", 29.99d, 10, "pic0004.jpg").create();
        new Product("Digital Camera", 89.99d, 17, "pic0005.jpg").create();
        new Product("MP3 Player", 7.99d, 15, "pic0006.jpg").create();
        new Product("32GB USB2 Drive", 6.99d, 1, "pic0007.jpg").create();
    }

    public static Logger getLogger() {
        return LoggerFactory.getLogger(Server.class);
    }

    private static boolean isInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}