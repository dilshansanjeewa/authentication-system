package cintroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignUpFormController {

    @FXML
    private Button btnSignUp;

    @FXML
    private Label lblError;

    @FXML
    private Hyperlink linkLogIn;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private PasswordField txtReEnterdPassword;

    @FXML
    void btnSignUpOnAction(ActionEvent event) {

    }

}
