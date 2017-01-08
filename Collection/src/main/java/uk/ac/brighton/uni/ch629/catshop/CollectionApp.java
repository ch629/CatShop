package uk.ac.brighton.uni.ch629.catshop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CollectionApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Collection.fxml"));

        loader.setControllerFactory(t -> new CollectionController(new CollectionModel()));

        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.show();
    }
}