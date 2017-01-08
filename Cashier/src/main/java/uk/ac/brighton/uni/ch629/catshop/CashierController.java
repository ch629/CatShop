package uk.ac.brighton.uni.ch629.catshop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import uk.ac.brighton.uni.ch629.catshop.data.Product;
import uk.ac.brighton.uni.ch629.catshop.data.RequestUtil;

public class CashierController {
    private CashierModel model;

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

    }

    @FXML
    public void onCheck(ActionEvent event) {
        Product product = RequestUtil.getProduct(Integer.parseInt(search.getText()));
        if (product != null) productName.setText(product.getDescription());
        else productName.setText("No Product Found");
    }

    @FXML
    public void onAdd(ActionEvent event) {

    }

    @FXML
    public void onBuy(ActionEvent event) {

    }

    @FXML
    public void onClear(ActionEvent event) {

    }
}
