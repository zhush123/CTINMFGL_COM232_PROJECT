import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUpController {

    @FXML
    private TextField addressTextfield;

    @FXML
    private Label addressTextlabel;

    @FXML
    private TextField contactTextfield;

    @FXML
    private Label contactTextlabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Button registerButton;

    @FXML
    private Button returnbutton;

    @FXML
    private Label usernameLabel;

    @FXML
    private TextField usernameTextField;

    public void returnbuttonHandler(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Welcome.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) returnbutton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    private void loginbuttonHandler(ActionEvent event) {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String city = addressTextfield.getText();
        String contactnumber = contactTextfield.getText();
        
        // Trim input values to remove leading and trailing spaces
        username = username.trim();
        password = password.trim();
        city = city.trim();
        contactnumber = contactnumber.trim();

        // if (username.isEmpty()) {
        //     showAlert("Empty username");
        //     return;
        // }
        if (username.length() == 0) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Username Entry");
            alert.setContentText("Please fill in username");
            alert.showAndWait();
            return;
        }

        // if (password.isEmpty()) {
        //     showAlert("Empty password");
        //     return;

        if (password.length() == 0) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Password Entry");
            alert.setContentText("Please fill in email");
            alert.showAndWait();
            return;
        }

        if (city.isEmpty()) {
            showAlert("Empty city");
            return;
        }

        if (contactnumber.isEmpty()) {
            showAlert("Empty contact number");
            return;
        }

        // Create the user object
        User user = new User("", username, password, city, contactnumber, "");

        // Add user to the database
        if (DatabaseHandler.addUser(user)) {
            showAlert("Account created!", AlertType.INFORMATION);

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Welcome.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) registerButton.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            showAlert("Cannot create new user");
        }
    }

    // Utility method to show an error alert
    private void showAlert(String message) {
        showAlert(message, AlertType.ERROR);
    }

    // Overloaded utility method to show alerts with different types
    private void showAlert(String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
