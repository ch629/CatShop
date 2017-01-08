package uk.ac.brighton.uni.ch629.catshop;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import uk.ac.brighton.uni.ch629.catshop.data.Order;
import uk.ac.brighton.uni.ch629.catshop.data.RequestUtil;

public class WarehouseItemCell extends ListCell<Order> {
    private HBox hBox = new HBox();
    private Label label = new Label("(empty)");
    private Pane pane = new Pane();
    private Button button = new Button("Collected");

    public WarehouseItemCell(WarehouseController controller) {
        super();
        hBox.getChildren().addAll(label, pane, button);
        HBox.setHgrow(pane, Priority.ALWAYS);
        button.setOnAction(event -> {
            RequestUtil.collectOrder(getItem().getOrderID());
            controller.removeOrder(getItem());
        });
        updateItem(null, true); //TODO: Check this
    }

    @Override
    protected void updateItem(Order item, boolean empty) {
        super.updateItem(item, empty);
        setText(null);
        if (empty) {
            setItem(null);
            setGraphic(null);
        } else {
            setItem(item);
            StringBuilder text = new StringBuilder();
            if (item != null) {
                text.append("#");
                text.append(item.getOrderID());
                text.append(" ");
                item.getProducts().forEach(productQuantity -> {
                    text.append("(");
                    text.append(productQuantity.getProduct().getDescription());
                    text.append(", ");
                    text.append(productQuantity.getQuantity());
                    text.append("), ");
                });
                text.setLength(text.length() - 2); //Remove the comma
                label.setText(text.toString());
            } else label.setText("<null>");
            setGraphic(hBox);
        }
    }
}