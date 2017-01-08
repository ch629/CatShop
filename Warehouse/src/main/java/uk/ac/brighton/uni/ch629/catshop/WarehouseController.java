package uk.ac.brighton.uni.ch629.catshop;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import uk.ac.brighton.uni.ch629.catshop.data.Order;
import uk.ac.brighton.uni.ch629.catshop.subscriptions.SubscriptionCreator;
import uk.ac.brighton.uni.ch629.catshop.update.AddOrderNew;

public class WarehouseController {
    private WarehouseModel model;
    private ObservableList<Order> orders;

    @FXML
    private ListView<Order> listView;

    public WarehouseController(WarehouseModel warehouseModel) {
        this.model = warehouseModel;
    }

    @FXML
    private void initialize() {
        System.out.println("INITIALIZE");
        orders = FXCollections.observableArrayList();
        listView.setItems(orders);
        listView.setCellFactory(param -> new WarehouseItemCell(this));

        new SubscriptionCreator(AddOrderNew.class, updateWrapper -> addOrderLater(((AddOrderNew) updateWrapper.getUpdate()).getOrder()));
    }

    /**
     * This method is used to add an order in the JavaFX thread.
     *
     * @param order The order to add
     */
    public void addOrderLater(Order order) {
        Platform.runLater(() -> addOrder(order));
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
    }
}