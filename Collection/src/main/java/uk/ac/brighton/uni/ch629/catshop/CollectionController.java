package uk.ac.brighton.uni.ch629.catshop;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import uk.ac.brighton.uni.ch629.catshop.subscriptions.SubscriptionCreator;
import uk.ac.brighton.uni.ch629.catshop.update.PickOrder;

public class CollectionController {
    private CollectionModel model;
    private ObservableList<Integer> orders;

    @FXML
    private ListView<Integer> listView;

    public CollectionController(CollectionModel collectionModel) {
        this.model = collectionModel;
    }

    @FXML
    private void initialize() {
        System.out.println("INITIALIZE");
        orders = FXCollections.observableArrayList();
        listView.setItems(orders);
        listView.setCellFactory(param -> new CollectionItemCell(this));

        new SubscriptionCreator(PickOrder.class, updateWrapper -> addOrderLater(((PickOrder) updateWrapper.getUpdate()).getOrderID()));
    }

    /**
     * This method is used to add an order in the JavaFX thread.
     *
     * @param orderID The order to add
     */
    public void addOrderLater(int orderID) {
        Platform.runLater(() -> addOrder(orderID));
    }

    public void addOrder(int orderID) {
        orders.add(orderID);
    }

    public void removeOrder(int orderID) {
        orders.remove(orderID);
    }
}
