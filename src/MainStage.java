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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainStage extends Application implements Initializable {

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
    @FXML public Label checkingAndSavingsAddButton;
    @FXML public Label incomeSourcesAddButton;
    @FXML public Label expensesAddButton;
    @FXML public Label subscriptionsAddButton;
    @FXML public Label depositsAddButton;
    @FXML public Label withdrawalsAddButton;
    @FXML public Label checkingAndSavingsRemoveButton;
    @FXML public Label incomeSourcesRemoveButton;
    @FXML public Label expensesRemoveButton;
    @FXML public Label subscriptionsRemoveButton;
    @FXML public Label depositsRemoveButton;
    @FXML public Label withdrawalsRemoveButton;
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
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (database.credentialsAreValid(username, password)) {
            database.setLoginCredentials(username, password);
            openBudgetManager();
        }
//        else showInvalidPasswordAlert();


    }

    public void onSetupSubmit() {

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

        final String zeroDollars = "$0.00";

        checkingAndSavingsBalance.setText(zeroDollars);
        incomeSourcesBalance.setText(zeroDollars);
        expensesBalance.setText(zeroDollars);
        subscriptionsBalance.setText(zeroDollars);
        depositsBalance.setText(zeroDollars);
        withdrawalsBalance.setText(zeroDollars);

//        ObservableList<ListItem> incomeSources = FXCollections.observableArrayList(),
//            deposits = FXCollections.observableArrayList(),
//            withdrawals = FXCollections.observableArrayList(),
//            subscriptions = FXCollections.observableArrayList(),
//            checkingAndSavingsAccounts = FXCollections.observableArrayList(),
//            expenses = FXCollections.observableArrayList();
//
//        ListView<ListItem> incomeSourcesView = new ListView<>(incomeSources),
//            depositsView = new ListView<>(deposits),
//            withdrawalsView = new ListView<>(withdrawals),
//            subscriptionsView = new ListView<>(subscriptions),
//            checkingSavingsView = new ListView<>(checkingAndSavingsAccounts),
//            expensesView = new ListView<>(expenses);
//
//        incomeSourcesView.setCellFactory(param -> new ManagerListCell());
//        depositsView.setCellFactory(param -> new ManagerListCell());
//        withdrawalsView.setCellFactory(param -> new ManagerListCell());
//        subscriptionsView.setCellFactory(param -> new ManagerListCell());
//        checkingSavingsView.setCellFactory(param -> new ManagerListCell());
//        expensesView.setCellFactory(param -> new ManagerListCell());

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

