package uk.ac.brighton.uni.ch629.catshop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class BackEndController { //TODO: Refactor some parts to BackDoorModel
    @FXML
    public TableColumn descriptionColumn; //TODO: Stock Adding.
    private BackEndModel model;
    private Stage stage;
    @FXML
    private TableColumn idColumn;
    @FXML
    private TableView productTable;

    @FXML
    private TextField description, price, imageLocation;

    @FXML
    private AnchorPane rightSplit;

    @FXML
    private ImageView image;

    public BackEndController(BackEndModel model, Stage stage) {
        this.model = model;
        this.stage = stage;
    }

    private void fillTable() {
//        productTable.setItems(model.getProductsAsRows());
//        descriptionColumn.setCellValueFactory(new PropertyValueFactory<ProductTableRow, String>("description"));
//        idColumn.setCellValueFactory(new PropertyValueFactory<ProductTableRow, String>("id"));
    }

    private void setImage(String fileLocation) { //TODO: Maybe make a resource class to get resources like Views and Images.
        this.image.setImage(new Image(getClass().getResourceAsStream(fileLocation)));
    }

    @FXML
    private void initialize() { //TODO: Make ImageView for the preview larger.
        rightSplit.setDisable(true);
        image.setImage(BackEndModel.defaultProductImage);
        fillTable();

        /*productTable.setRowFactory(tv -> {
            TableRow<ProductTableRow> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    model.setSelectedProduct(Product.getProduct(row.getItem().getId()));
                    rightSplit.setDisable(false);
                    description.setText(model.getSelectedProduct().getDescription());
                    price.setText("" + model.getSelectedProduct().getPrice());
                    setImage(model.getImageLocation());
                    imageLocation.setText(model.getImageLocation()); //TODO: This may not have /images/ in front of the name of the image because it always starts in that directory
                }
            });
            return row;
        });*/
    }

    @FXML
    void onBrowse(ActionEvent event) { //TODO: Make a custom FileChooser System which shows images in a file-like structure in the set directory for resources (Maybe allow directory uses)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ImageChooser.fxml"));
//        loader.setControllerFactory(t -> new ImageChooserController());

        try {
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onSave(ActionEvent event) {
        model.updateProduct(description.getText(), Float.parseFloat(price.getText()), imageLocation.getText());
        fillTable();

        new Alert(Alert.AlertType.INFORMATION, "Updating Product: " + model.getSelectedProduct().getProductNumber()).showAndWait(); //TODO: Maybe make this a confirmation one
    }

    @FXML
    void onCancel(ActionEvent event) {
        model.removeSelectedProduct();
        description.setText("");
        price.setText("");
        imageLocation.setText("");
        image.setImage(BackEndModel.defaultProductImage);
        rightSplit.setDisable(true);
    }
}