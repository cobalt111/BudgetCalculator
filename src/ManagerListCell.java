import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import model.ListItem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManagerListCell extends ListCell<ListItem> implements Initializable {

    private FXMLLoader loader;

    @FXML
    private Label name;

    @FXML
    private Label amount;

    public ManagerListCell() {
    }

    @Override
    protected void updateItem(ListItem item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        }
        else {
            if (loader == null) {
                loader = new FXMLLoader(getClass().getResource("../scenes/ManagerListCell.fxml"));
                loader.setController(this);
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            name.setText(item.getName());
            amount.setText(String.valueOf(item.getAmount()));
//            setText(null);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
