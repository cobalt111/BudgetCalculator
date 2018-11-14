import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Database;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ExpensesChangeController implements Initializable {

    private Database database;
    @FXML public Label enterDollarAmountLabel;
    @FXML public TextField submissionAmountField;
    @FXML public Button submitChangeButton;

    @FXML public void handleChangeSubmitButtonClicked() {
        onSubmitChange();
    }

    private void onSubmitChange() {
        database = Database.getDatabaseInstance();
        database.updateBudget("Expenses", Integer.parseInt(submissionAmountField.getText())*100);
        openBudgetLoader();
    }

    public void openBudgetLoader() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SimpleBudgetForm.fxml"));
        Parent managerRoot = null;
        try {
            managerRoot = loader.load();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        Stage managerStage = new Stage();
        managerStage.initModality(Modality.APPLICATION_MODAL);
        managerStage.setTitle("Monthly Budget Calculator");
        managerStage.setScene(new Scene(managerRoot));
        managerStage.setResizable(false);
        managerStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
