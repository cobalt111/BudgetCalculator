import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Database;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainStage extends Application implements Initializable {

    public User user;

    @FXML public Button registerButton;

    @FXML
    public void handleRegisterClicked() {
        database = Database.getDatabaseInstance();
        user = new User(usernameField.getText(), passwordField.getText());
        database.setCredentials(user);
        openSetup();
    }

    //region Login Form
    @FXML public TextField usernameField;
    private Database database;
    //region Setup Form
    @FXML public Label checkingAndSavingsSetupLabel;
    @FXML public PasswordField passwordField;
    @FXML public Button loginButton;
    @FXML public Label incomeSourcesSetupLabel;
    //endregion
    @FXML public Label expensesSetupLabel;
    @FXML public Label subscriptionsSetupLabel;
    @FXML public Label depositsSetupLabel;
    @FXML public Label withdrawalsSetupLabel;
    @FXML public TextField checkingAndSavingsSetupBalance;
    @FXML public TextField incomeSourcesSetupBalance;
    @FXML public TextField expensesSetupBalance;
    @FXML public TextField subscriptionsSetupBalance;
    @FXML public TextField depositsSetupBalance;
    @FXML public TextField withdrawalsSetupBalance;
    @FXML public Button submitSetupButton;
    //region Budget Form
    @FXML public Label checkingAndSavingsLabel;
    @FXML public Label incomeSourcesLabel;
    @FXML public Label expensesLabel;
    //endregion
    @FXML public Label subscriptionsLabel;
    @FXML public Label depositsLabel;
    @FXML public Label withdrawalsLabel;
    @FXML public Label monthlyBalanceLabel;
    @FXML public Label checkingAndSavingsBalance;
    @FXML public Label incomeSourcesBalance;
    @FXML public Label expensesBalance;
    @FXML public Label subscriptionsBalance;
    @FXML public Label depositsBalance;
    @FXML public Label withdrawalsBalance;
    @FXML public Label monthlyBalance;
    @FXML public Button checkingAndSavingsAddButton;
    @FXML public Button incomeSourcesAddButton;
    @FXML public Button expensesAddButton;
    @FXML public Button subscriptionsAddButton;
    @FXML public Button depositsAddButton;
    @FXML public Button withdrawalsAddButton;
    @FXML public Button checkingAndSavingsRemoveButton;
    @FXML public Button incomeSourcesRemoveButton;
    @FXML public Button expensesRemoveButton;
    @FXML public Button subscriptionsRemoveButton;
    @FXML public Button depositsRemoveButton;
    @FXML public Button withdrawalsRemoveButton;
    //region Change Amount Form
    @FXML public Label enterDollarAmountLabel;
    @FXML public TextField submissionAmountField;
    @FXML public Button submitChangeButton;
    private String currentlyChangingParameter;

    @FXML
    public void handleLoginButtonClicked() {
        if (usernameField.getText().isEmpty()) {
            showFieldEmptyAlert("Username");
        }
        else if (passwordField.getText().isEmpty()) {
            showFieldEmptyAlert("Password");
        }
        else onLoginClicked();
    }

    @FXML public void handleSetupSubmitButtonClicked() {
        onSetupSubmit();
    }

    @FXML
    public void handleCheckingAndSavingsAddButtonClicked() {
        currentlyChangingParameter = "CheckingAndSavings";
        openAmountChanger("CheckingAndSavings", true);
    }

    @FXML
    public void handleCheckingAndSavingsRemoveButtonClicked() {
        currentlyChangingParameter = "CheckingAndSavings";
        openAmountChanger("CheckingAndSavings", false);
    }

    @FXML
    public void handleIncomeSourcesAddButtonClicked() {
        currentlyChangingParameter = "IncomeSources";
        openAmountChanger("IncomeSources", true);
    }

    @FXML
    public void handleIncomeSourcesRemoveButtonClicked() {
        currentlyChangingParameter = "IncomeSources";

        openAmountChanger("IncomeSources", false);
    }

    @FXML
    public void handleExpensesAddButtonClicked() {
        currentlyChangingParameter = "Expenses";

        openAmountChanger("Expenses", true);
    }

    @FXML
    public void handleExpensesRemoveButtonClicked() {
        currentlyChangingParameter = "Expenses";

        openAmountChanger("Expenses", false);
    }

    @FXML
    public void handleSubscriptionsAddButtonClicked() {
        currentlyChangingParameter = "Subscriptions";

        openAmountChanger("Subscriptions", true);
    }

    @FXML
    public void handleSubscriptionsRemoveButtonClicked() {
        currentlyChangingParameter = "Subscriptions";

        openAmountChanger("Subscriptions", false);
    }

    @FXML
    public void handleDepositsAddButtonClicked() {
        currentlyChangingParameter = "Deposits";

        openAmountChanger("Deposits", true);
    }
    //endregion

    @FXML
    public void handleDepositsRemoveButtonClicked() {
        currentlyChangingParameter = "Deposits";

        openAmountChanger("Deposits", false);
    }

    @FXML
    public void handleWithdrawalsAddButtonClicked() {
        currentlyChangingParameter = "Withdrawals";

        openAmountChanger("Withdrawals", true);
    }

    @FXML
    public void handleWithdrawalsRemoveButtonClicked() {
        currentlyChangingParameter = "Withdrawals";

        openAmountChanger("Withdrawals", false);
    }

    @FXML public void handleChangeSubmitButtonClicked() {
        onSubmitChange();
    }
    //endregion

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        database = Database.getDatabaseInstance();
        Parent loginRoot = FXMLLoader.load(getClass().getResource("LoginForm.fxml"));
        primaryStage.setScene(new Scene(loginRoot, 575, 400));
        primaryStage.setTitle("Monthly Budget Calculator");
        primaryStage.show();
    }

    public void showFieldEmptyAlert(String field) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("No " + field);
        alert.setContentText(field + " is required!");
        alert.showAndWait();
    }

    public void onLoginClicked() {
        User user = new User(usernameField.getText(), passwordField.getText());
        database = Database.getDatabaseInstance();
        if (database.credentialsAreValid(user).equals("TRUE")) {
            database.setCredentials(user);
            openBudgetManager();
        }
//        else showInvalidPasswordAlert();


    }

    public void onSetupSubmit() {
        database = Database.getDatabaseInstance();
        int[] amounts = {Integer.parseInt(checkingAndSavingsSetupBalance.getText())*100,
                Integer.parseInt(incomeSourcesSetupBalance.getText())*100,
                Integer.parseInt(expensesSetupBalance.getText())*100,
                Integer.parseInt(subscriptionsSetupBalance.getText())*100,
                Integer.parseInt(depositsSetupBalance.getText())*100,
                Integer.parseInt(withdrawalsSetupBalance.getText())*100};
        database.setupUser(amounts);
    }

    public void onSubmitChange() {

    }

    public void openSetup() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SetupForm.fxml"));
        Parent setupRoot = null;
        try {
            setupRoot = loader.load();
        }
        catch (IOException e) {
            e.printStackTrace();
        }



        Stage setupStage = new Stage();
        setupStage.initModality(Modality.APPLICATION_MODAL);
        setupStage.setTitle("Initial Setup");
        setupStage.setScene(new Scene(setupRoot));
        setupStage.setResizable(false);
        setupStage.show();
    }

    public void openBudgetManager() {
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

    public void openAmountChanger(String valueToChange, boolean isAddInstance) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ChangeAmountForm.fxml"));
        Parent changeRoot = null;
        try {
            changeRoot = loader.load();
        }
        catch (IOException e) {
            e.printStackTrace();
        }



        Stage changeStage = new Stage();
        changeStage.initModality(Modality.APPLICATION_MODAL);
        changeStage.setTitle("Change amount");
        changeStage.setScene(new Scene(changeRoot));
        changeStage.setResizable(false);
        changeStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

