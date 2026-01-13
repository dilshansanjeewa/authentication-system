package cintroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.dto.UserDTO;
import service.SignUpService;
import service.impl.SignUpServiceImpl;

import java.io.IOException;

public class SignUpFormController {

    SignUpService signUpService = new SignUpServiceImpl();

    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);

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
        if (isValidate()){
            lblError.setVisible(false);

            if(signUpService.save(getUser())){
                showSuccessAlert();
                loadLogin();

            }else {
                showErrorAlert("Sign Up Process Failed...");
            }
        }
    }

    @FXML
    void linkLogInOnAction(ActionEvent event) {
        loadLogin();
    }

    private UserDTO getUser(){
        return new UserDTO(
                txtFirstName.getText(),
                txtLastName.getText(),
                txtEmail.getText(),
                txtPassword.getText()
        );
    }

    private boolean isValidate(){
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        String reEnterdPassword = txtReEnterdPassword.getText();

        if(firstName.isEmpty()){
            showErrorAlert("Please enter first name");
            return false;
        } else if (lastName.isEmpty()) {
            showErrorAlert("Please enter last name");
            return false;
        } else if (email.isEmpty()) {
            showErrorAlert("Please enter email address");
            return false;
        } else if (!isValidateEmail(email)) {
            showErrorAlert("Please enter valid email address");
            return false;
        } else if (password.isEmpty()) {
            showErrorAlert("Please enter password");
            return false;
        } else if (!isValidPassword(password)) {
            lblError.setVisible(true);
            lblError.setText("Week Password");
            return false;
        } else if (reEnterdPassword.isEmpty()) {
            lblError.setVisible(true);
            showErrorAlert("Please re-enter the password");
            return false;
        } else if (!reEnterdPassword.equals(password)) {
            lblError.setVisible(true);
            lblError.setText("does not match with password");
            return false;
        }

        return true;
    }

    private boolean isValidateEmail(String email){

        try{
            String substring = email.substring(email.length() - 10);
            if (!substring.equals("@gmail.com")) return false;
        } catch (StringIndexOutOfBoundsException e){
            return false;
        }

        return true;
    }

    private boolean isValidPassword(String password) {
        if (password.length()<8) return false;

        if (!checkUppercase(password)) return false;

        if (!checkLowercase(password)) return false;

        if (!isContainsSymbols(password)) return false;

        return true;
    }

    private boolean checkUppercase(String password) {
        for (char ch : password.toCharArray()){
            if(Character.isUpperCase(ch)) return true;
        }
        return false;
    }

    private boolean checkLowercase(String password) {
        for (char ch : password.toCharArray()){
            if(Character.isLowerCase(ch)) return true;
        }
        return false;
    }

    private boolean isContainsSymbols(String password){
        for (char ch : password.toCharArray()){
            switch (ch){
                case '!', '*', '&', '^', '%', '$', '#', '@': return true;
            }
        }
        return false;
    }

    private void loadLogin(){
        Stage stage = (Stage) btnSignUp.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/sign-in-form.fxml"))));
            stage.setTitle("Log In");
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void showErrorAlert(String message){
        errorAlert.setHeaderText("ERROR");
        errorAlert.setContentText(message);
        errorAlert.show();
    }

    private void showSuccessAlert(){
        successAlert.setHeaderText("ALL DONE!");
        successAlert.setContentText("Sign In Success!\n Back to Login");
        successAlert.showAndWait();
    }

}
