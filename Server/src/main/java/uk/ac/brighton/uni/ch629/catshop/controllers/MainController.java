package uk.ac.brighton.uni.ch629.catshop.controllers;

import org.springframework.web.bind.annotation.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import uk.ac.brighton.uni.ch629.catshop.Application;
import uk.ac.brighton.uni.ch629.catshop.database.model.Product;

import java.util.List;

@RestController
public class MainController { //TODO: Look into Security
    @GetMapping(value = "/product")
    public Product findProduct(@RequestParam(value = "id") int id) {
        Product product = Application.getProductDao().getProduct(id);
        if (product == null) {
            //TODO: This should probably return a response rather than a product.
        }
        return product != null ? product : null;
    }

    @GetMapping(value = "/product/all")
    public List<Product> getAllProducts() {
        return Application.getProductDao().getProducts();
    }

    @PostMapping(value = "/protected/product")
    public void addProduct(@RequestBody Product product) {
        Application.create(product);
    }

    @DeleteMapping(value = "/protected/product")
    public void removeProduct(@RequestParam(value = "id") int id) {
        Application.getProductDao().deleteProduct(id);
    }

    @PostMapping(value = "/auth/add")
    public void addAuth() { //TODO: @RequestBody
        throw new NotImplementedException();
    }

    @GetMapping(value = "/auth/add")
    public void acceptAuth() {
        throw new NotImplementedException();
    }

    @PostMapping(value = "subscribe")
    public void subscribe() {
        throw new NotImplementedException();
    }

    /* Old Route Code
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
     */
}