package uk.ac.brighton.uni.ch629.catshop;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import uk.ac.brighton.uni.ch629.catshop.data.RequestUtil;

public class CollectionItemCell extends ListCell<Integer> {
    private HBox hBox = new HBox();
    private Label label = new Label("(empty)");
    private Pane pane = new Pane();
    private Button button = new Button("Collected");

    public CollectionItemCell(CollectionController controller) {
        super();
        hBox.getChildren().addAll(label, pane, button);
        HBox.setHgrow(pane, Priority.ALWAYS);
        button.setOnAction(event -> {
            RequestUtil.collectOrder(getItem());
            controller.removeOrder(getItem());
        });
        updateItem(null, true); //TODO: Check this
    }

    @Override
    protected void updateItem(Integer item, boolean empty) {
        super.updateItem(item, empty);
        setText(null);
        if (empty) {
            setItem(null);
            setGraphic(null);
        } else {
            setItem(item);
            if (item != null) {
                label.setText(String.valueOf(getItem()));
            } else label.setText("<null>");
            setGraphic(hBox);
        }
    }
}