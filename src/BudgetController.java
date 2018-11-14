import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Database;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BudgetController implements Initializable {

    private String currentlyChangingParameter;
    private Database database;

    @FXML public Label checkingAndSavingsLabel;
    @FXML public Label incomeSourcesLabel;
    @FXML public Label expensesLabel;
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

    @FXML
    public void handleCheckingAndSavingsAddButtonClicked() {
        currentlyChangingParameter = "CheckingAndSavings";
        openAccountsAmountChanger("CheckingAndSavings", true);
    }

    @FXML
    public void handleIncomeSourcesAddButtonClicked() {
        currentlyChangingParameter = "IncomeSources";
        openIncomeAmountChanger("IncomeSources", true);
    }

    @FXML
    public void handleExpensesAddButtonClicked() {
        currentlyChangingParameter = "Expenses";
        openExpensesAmountChanger("Expenses", true);
    }

    @FXML
    public void handleSubscriptionsAddButtonClicked() {
        currentlyChangingParameter = "Subscriptions";
        openSubscriptionsAmountChanger("Subscriptions", true);
    }

    @FXML
    public void handleDepositsAddButtonClicked() {
        currentlyChangingParameter = "Deposits";
        openDepositsAmountChanger("Deposits", true);
    }

    @FXML
    public void handleWithdrawalsAddButtonClicked() {
        currentlyChangingParameter = "Withdrawals";
        openWithdrawalsAmountChanger("Withdrawals", true);
    }

    public void openAccountsAmountChanger(String valueToChange, boolean isAddInstance) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AccountsChangeForm.fxml"));
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

    public void openIncomeAmountChanger(String valueToChange, boolean isAddInstance) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("IncomeChangeForm.fxml"));
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

    public void openExpensesAmountChanger(String valueToChange, boolean isAddInstance) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ExpensesChangeForm.fxml"));
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

    public void openSubscriptionsAmountChanger(String valueToChange, boolean isAddInstance) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SubscriptionsChangeForm.fxml"));
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

    public void openDepositsAmountChanger(String valueToChange, boolean isAddInstance) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DepositsChangeForm.fxml"));
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

    public void openWithdrawalsAmountChanger(String valueToChange, boolean isAddInstance) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("WithdrawalsChangeForm.fxml"));
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
        database = Database.getDatabaseInstance();
        int[] amounts = database.getCurrentBudget();

        String accounts = String.valueOf(amounts[0]/100);
        String income = String.valueOf(amounts[1]/100);
        String expenses = String.valueOf(amounts[2]/100);
        String subscriptions = String.valueOf(amounts[3]/100);
        String deposits = String.valueOf(amounts[4]/100);
        String withdrawals = String.valueOf(amounts[5]/100);

        int sum = amounts[0] + amounts[1] - amounts[2] - amounts[3] + amounts[4] - amounts[5];
        sum = sum/100;

        String monthlyBalanceString = String.valueOf(sum);

        checkingAndSavingsBalance.setText(accounts);
        incomeSourcesBalance.setText(income);
        expensesBalance.setText(expenses);
        subscriptionsBalance.setText(subscriptions);
        depositsBalance.setText(deposits);
        withdrawalsBalance.setText(withdrawals);
        monthlyBalance.setText(monthlyBalanceString);
    }
}
