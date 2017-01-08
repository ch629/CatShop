package uk.ac.brighton.uni.ch629.catshop;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import uk.ac.brighton.uni.ch629.catshop.data.Product;
import uk.ac.brighton.uni.ch629.catshop.data.RequestUtil;

public class CashierController {
    private CashierModel model;
    private ObservableList<CashierTableRow> productQuantities;
    private float price = 0;

    @FXML
    private TextField search;

    @FXML
    private Label productName;

    @FXML
    private TableView basket;

    @FXML
    private Label totalPrice;

    public CashierController(CashierModel cashierModel) {
        this.model = cashierModel;
    }

    @FXML
    private void initialize() {
        basket.setEditable(false);
        productQuantities = FXCollections.observableArrayList();

        basket.setItems(productQuantities);

        TableColumn nameColumn = (TableColumn) basket.getColumns().get(0);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn stockColumn = (TableColumn) basket.getColumns().get(1);
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        TableColumn quantityColumn = (TableColumn) basket.getColumns().get(2);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        TableColumn priceColumn = (TableColumn) basket.getColumns().get(3);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
    }

    @FXML
    public void onCheck(ActionEvent event) {
        Product product = RequestUtil.getProduct(Integer.parseInt(search.getText()));
        if (product != null) productName.setText(product.getDescription());
        else productName.setText("No Product Found");
    }

    @FXML
    public void onAdd(ActionEvent event) {
        Product product = RequestUtil.getProduct(Integer.parseInt(search.getText()));
        if (product != null) {
            addProduct(product);
//            model.addItem(product);
//            productQuantities.add(new CashierTableRow(product, 1)); //TODO: Merge existing products
        }
    }

    private void addProduct(Product product) {
        model.addItem(product);
        int productIndex = indexOf(product);
        if (productIndex >= 0) {
            CashierTableRow row = productQuantities.get(productIndex);
            row.setQuantity(row.getQuantity() + 1);
            productQuantities.set(productIndex, row); //TODO: Would be nicer to just draw it from the Basket but not sure how to do that correctly, would need observers
        } else productQuantities.add(new CashierTableRow(product, 1));

        increaseTotalPrice(product.getPrice());
    }

    private boolean hasProduct(Product product) {
        return productQuantities.stream().anyMatch(p -> p.getName().equals(product.getDescription())); //TODO: Maybe contain ProductID
    }

    private int indexOf(Product product) {
        if (hasProduct(product)) {
            for (int i = 0; i < productQuantities.size(); i++) {
                if (productQuantities.get(i).getName().equals(product.getDescription())) return i;
            }
        }
        return -1;
    }

    @FXML
    public void onBuy(ActionEvent event) {
        final int[] orderID = {0};
        Platform.runLater(() -> {
            orderID[0] = RequestUtil.addOrder(model);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Your Order ID Is: " + orderID[0]);
            alert.showAndWait();
        });
        onClear(event);
    }

    @FXML
    public void onClear(ActionEvent event) {
        model.empty();
        productQuantities.clear();
    }

    public void increaseTotalPrice(float amount) {
        price += amount;
        totalPrice.setText("Total Price: £" + price);
    }
}
