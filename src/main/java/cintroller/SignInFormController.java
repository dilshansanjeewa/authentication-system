package cintroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.dto.UserDTO;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import service.SignInService;
import service.impl.SignInServiceImpl;

import java.io.IOException;

public class SignInFormController {

    SignInService signInService = new SignInServiceImpl();

    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);

    @FXML
    private Button btnSignIn;

    @FXML
    private Label lblError;

    @FXML
    private Hyperlink linkSignUp;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    void btnSignInOnAction(ActionEvent event) {
        if(isValid()){
            UserDTO user = signInService.isValidateUser(txtEmail.getText(), txtPassword.getText());
            if(user == null){
                showErrorAlert("User Not Found... Please Try again");
            }else {
                if(signInService.isCorrectPassword(txtPassword.getText(), user.getPassword())){
                    showSuccessAlert();
                    loadDashboard(user.getFirstName(), user.getLastName());
                }else {
                    showErrorAlert("Incorrect Password");
                }
            }
        }
    }

    @FXML
    void linkSignUpOnAction(ActionEvent event) {
        loadSignUpForm();
    }

    private boolean isValid(){
        String email = txtEmail.getText();
        String password = txtPassword.getText();

        if(email.isEmpty()){
            showErrorAlert("Please Enter Email Address");
            return false;
        } else if (password.isEmpty()) {
            showErrorAlert("Please Enter password");
            return false;
        }
        return true;
    }

    private void loadDashboard(String firstName, String lastName){

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dashboard-form.fxml"));
            Parent root = loader.load();
            DashboardFormController controller = loader.getController();
            controller.transferUserName(firstName, lastName);

            Stage stage = (Stage) btnSignIn.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Dashboard");
            stage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void loadSignUpForm(){
        Stage stage = (Stage) btnSignIn.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/sign-up-form.fxml"))));
            stage.setTitle("Sign Up");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showErrorAlert(String message){
        errorAlert.setHeaderText("ERROR");
        errorAlert.setContentText(message);
        errorAlert.show();
    }

    private void showSuccessAlert(){
        successAlert.setHeaderText("ALL DONE!");
        successAlert.setContentText("Sign In Success!");
        successAlert.showAndWait();
    }

}
