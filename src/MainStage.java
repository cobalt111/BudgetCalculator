import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Database;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainStage extends Application implements Initializable {

    private Database database;

    @FXML public TextField usernameField;
    @FXML public PasswordField passwordField;
    @FXML public Button loginButton;

    @FXML
    public void handleLoginButtonClicked() {
        if (usernameField.getText().isEmpty()) {
            showFieldEmptyAlert("Username");
        }
        else if (passwordField.getText().isEmpty()) {
            showFieldEmptyAlert("Password");
        }
        else onLogin();
    }

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

    public void showInvalidPasswordAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Incorrect login credentials");
        alert.setContentText("Either the username or password is incorrect");
        alert.showAndWait();
    }

    public void onLogin() {
        openBudgetManager();
        String username = usernameField.getText();
        String password = passwordField.getText();
        database.setCredentials(username, password);
        database.startConnection();

//        view.showInvalidPasswordAlert();
    }

    public void openBudgetManager() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ManagerForm.fxml"));
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

