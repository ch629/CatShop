package uk.ac.brighton.uni.ch629.catshop;

import javafx.scene.image.Image;
import uk.ac.brighton.uni.ch629.catshop.model.Product;

public class BackEndModel {
    public static Image defaultProductImage = new Image(BackEndModel.class.getResourceAsStream("/images/none.jpg")); //TODO: Maybe put this in Product.
    private Product selectedProduct;

    public void removeSelectedProduct() {
        selectedProduct = null;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public boolean updateProduct(String description, float price, String imageLocation) {
        selectedProduct.setDescription(description);
        selectedProduct.setPrice(price);
        selectedProduct.setImage(imageLocation);
//        return selectedProduct.update();
        return false; //TODO: Update on the serverside
    }

    /*public ObservableList<ProductTableRow> getProductsAsRows() {
        return FXCollections.observableList(Product.getAll().stream().map(
                ProductTableRow::getRowFromProduct).collect(Collectors.toList()));
    }*/

    public String getImageLocation() {
        return selectedProduct != null ? "/images/" + selectedProduct.getImage() : null;
    }
}