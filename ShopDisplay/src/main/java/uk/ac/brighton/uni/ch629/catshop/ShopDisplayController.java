package uk.ac.brighton.uni.ch629.catshop;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class ShopDisplayController {
    private ShopDisplayModel model;

    @FXML
    private ListView listView;

    public ShopDisplayController(ShopDisplayModel shopDisplayModel) {
        this.model = shopDisplayModel;
    }

    @FXML
    private void initialize() {

    }
}