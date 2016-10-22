package uk.ac.brighton.uni.ch629.catshop;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.ModelAndView;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;
import uk.ac.brighton.uni.ch629.catshop.communication.Request;
import uk.ac.brighton.uni.ch629.catshop.communication.Response;
import uk.ac.brighton.uni.ch629.catshop.communication.ResponseCode;
import uk.ac.brighton.uni.ch629.catshop.database.tables.records.AuthToken;
import uk.ac.brighton.uni.ch629.catshop.database.tables.records.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static spark.Spark.*;

public class Server { //TODO: Authentication using POST requests.
    public static void main(String[] args) { //TODO: Cache images with Google Guava?
        createDummyData(); //TODO: Use a request system to create correct JSON for the Request similar to HitBox's way (Use ResponseCode and maybe a StatusCode to check if the request was successful on client)
        Spark.staticFileLocation("/public");

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

        before("/protected/*", (req, res) -> { //TODO: Use Request?
            JsonObject object = (JsonObject) new JsonParser().parse(req.body());
            if (object == null) halt(401, "Not valid JSON request!");
            if (object != null && object.has("auth_token")) {
                if (!AuthToken.hasToken(object.get("auth_token").getAsString())) {
                    AuthToken.addToken(object.get("auth_token").getAsString());
                    halt(401, "Your token has been added, please wait for it to be authorized!");
                } else {
                    if (!AuthToken.isTokenAccepted(object.get("auth_token").getAsString())) {
                        System.out.println("h");
                        halt(401, "Please wait for your token to be accepted!");
                    }
                }
            }
            if (!object.has("auth_token")) halt(401, "Unauthorised Access, please add your auth_token to the request!");
        });

        post("/protected/product/add", (req, res) -> {
            Request request = Request.fromJson((JsonObject) new JsonParser().parse(req.body()));
            Product product = Product.fromJsonObject(request.getData());
            product.create();
            return "SUCCESS!";
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

        post("/auth/add", (req, res) -> {
            JsonObject json = (JsonObject) new JsonParser().parse(req.body());
            AuthToken token = AuthToken.getAuthToken(json.get("token").getAsString());
            token.accept();
            token.update();
            return "";
        });

        post("/subscribe", (req, res) -> { //NOTE: Subscribe to updates (Warehouse and ShopDisplay will need this)
            //NOTE: Hold data about what exactly to listen for (Updates to Product or Orders)
            //NOTE: If the Customer client caches the catalogue for searching, would need to be notified when stock has been decreased
            //TODO: Was thinking about using the Observer pattern, but I don't think this will be easy to implement, because of multiple Product's; which can be duplicates meaning a lot of wasted memory.
            String subscriptionIp = req.ip();
            return "";
        });

        get("/auth/add", (req, res) -> {
            Map map = new HashMap();
            map.put("requests",
                    AuthToken.getAll()
                            .stream()
                            .filter(token -> !token.isAccepted())
                            .collect(Collectors.toList()));
            return new ModelAndView(map, "addauth.mustache");
        }, new MustacheTemplateEngine());
    }

    public static void createDummyData() {
        Product.dropTable();
        Product.createTable();
        AuthToken.dropTable();
        AuthToken.createTable();
        AuthToken.addToken("test123");
        AuthToken.addToken("test456");
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