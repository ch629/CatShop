package uk.ac.brighton.uni.ch629.catshop;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.ModelAndView;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;
import uk.ac.brighton.uni.ch629.catshop.communication.*;
import uk.ac.brighton.uni.ch629.catshop.database.tables.records.AuthToken;
import uk.ac.brighton.uni.ch629.catshop.database.tables.records.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static spark.Spark.*;

public class Server { //TODO: Spring instead of Spark?
    public static void main(String[] args) { //TODO: Cache images with Google Guava?
        createDummyData(); //TODO: Use a request system to create correct JSON for the Request similar to HitBox's way (Use ResponseCode and maybe a StatusCode to check if the request was successful on client)
        Spark.staticFileLocation("/public");
        ObjectMapper mapper = new ObjectMapper();

        get("/product/all", (req, res) -> {
            ArrayNode array = new ArrayNode(new JsonNodeFactory(false));
            Product.getAll().forEach(array::addPOJO);
            return new Response(ResponseCode.SUCCESS, array);
        });

        get("/product/:id", (req, res) -> {
            String idParam = req.params(":id");
            if (!isInt(idParam))
                return new Response(ResponseCode.INVALID_PARAMETER, "Expected an integer in parameter ':id' but received %s", idParam);
            Product product = Product.getProduct(Integer.parseInt(idParam));
            if (product == null)
                return new Response(ResponseCode.PRODUCT_NOT_FOUND, "No product found with id: %s", idParam);
            return new Response(ResponseCode.SUCCESS, JsonHelper.objectToNode(product));
        });

        before("/protected/*", (req, res) -> { //TODO: Use Request?
            JSONObject object = new JSONObject(req.body()); //TODO: Use Jackson JsonObject
            if (object == null) halt(401, "Not valid JSON request!");
            if (object != null && object.has("auth_token")) {
                if (!AuthToken.hasToken(object.getString("auth_token"))) {
                    AuthToken.addToken(object.getString("auth_token"));
                    halt(401, "Your token has been added, please wait for it to be authorized!");
                } else {
                    if (!AuthToken.isTokenAccepted(object.getString("auth_token"))) {
                        halt(401, "Please wait for your token to be accepted!");
                    }
                }
            }
            if (!object.has("auth_token")) halt(401, "Unauthorised Access, please add your auth_token to the request!");
        });

        post("/protected/product/add", (req, res) -> {
            Request request = JsonHelper.jsonToObject(req.body(), Request.class);
            Product product = JsonHelper.jsonNodeToObject(request.getData(), Product.class);
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
            JsonNode node = mapper.readTree(req.body());
            AuthToken token = AuthToken.getAuthToken(node.get("token").asText());
            token.accept();
            token.update();
            return "";
        });

        post("/subscribe", (req, res) -> { //NOTE: Subscribe to updates (Warehouse and ShopDisplay will need this)
            //NOTE: Hold data about what exactly to listen for (Updates to Product or Orders)
            //NOTE: If the Customer client caches the catalogue for searching, would need to be notified when stock has been decreased
            //TODO: Was thinking about using the Observer pattern, but I don't think this will be easy to implement, because of multiple Product's; which can be duplicates meaning a lot of wasted memory.
            String subscriptionIp = req.ip();
            int subscriptionPort = req.port();
            JsonNode node = mapper.readTree(req.body());
            String subType = node.get("type").asText();
            Subscription.INSTANCE.subscribe(subscriptionIp, subscriptionPort, SubscriptionType.valueOf(subType.toUpperCase()));
            JsonNode data = mapper.createObjectNode();
            Subscription.INSTANCE.sendData(SubscriptionType.PRODUCT, data);
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