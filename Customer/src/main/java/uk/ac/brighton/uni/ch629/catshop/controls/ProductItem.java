package uk.ac.brighton.uni.ch629.catshop.controls;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import uk.ac.brighton.uni.ch629.catshop.data.Product;

public class ProductItem { //Not sure whether this should extend Control
    @FXML
    private ImageView image;

    @FXML
    private Label name;

    @FXML
    private Label price;

    @FXML
    private Label stock;

    public void setValues(Product product) {
        //Set image

        name.setText(product.getDescription());
        price.setText(Float.toString(product.getPrice())); //Might need formatting to 2dp
        stock.setText(Integer.toString(product.getStock()));
    }
}
