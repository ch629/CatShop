package uk.ac.brighton.uni.ch629.catshop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CashierApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Cashier.fxml"));
        loader.setControllerFactory(t -> new CashierController(new CashierModel()));

        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.show();
    }
}
