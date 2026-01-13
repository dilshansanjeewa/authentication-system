package cintroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardFormController {

    @FXML
    private Button btnLogOut;

    @FXML
    private Label lblUserName;

    public void transferUserName(String firstName, String lastName) {
        lblUserName.setText(firstName+" "+lastName);
    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) {
        Stage stage = (Stage) btnLogOut.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/sign-in-form.fxml"))));
            stage.setTitle("Sign In");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
