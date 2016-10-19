package uk.ac.brighton.uni.ch629.catshop;

import com.google.gson.JsonArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.ac.brighton.uni.ch629.catshop.database.tables.records.Product;

import static spark.Spark.get;

public class Server {
    public static void main(String[] args) { //TODO: Cache images with Google Guava?
        //TODO: Use new ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue)
        createDummyData();
        get("/product/all", (req, res) -> {
            JsonArray array = new JsonArray();
            Product.getAll().forEach(product -> array.add(product.toJsonObject()));
            return array;
        });

        get("/product/:id", (req, res) -> {
            Product product = Product.getProduct(Integer.parseInt(req.params(":id")));
            return product != null ? product.toJsonObject() : "{ }"; //Returns the data or an empty json object if null
        });
    }

    public static void createDummyData() {
        Product.dropTable();
        Product.createTable();
        new Product("40 inch LED HD TV", 269.00d, 90, "pic0001.jpg").create();
        new Product("DAB Radio", 19.99d, 20, "pic0002.jpg").create();
        new Product("Toaster", 19.99d, 33, "pic0003.jpg").create();
        new Product("Watch", 269.00d, 10, "pic0004.jpg").create();
        new Product("Digital Camera", 89.99d, 17, "pic0005.jpg").create();
        new Product("MP3 Player", 7.99d, 15, "pic0006.jpg").create();
        new Product("32GB USB2 Drive", 6.99d, 1, "pic0007.jpg").create();
    }

    public static Logger getLogger() {
        return LoggerFactory.getLogger(Server.class);
    }
}