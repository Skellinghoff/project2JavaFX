package com.example.project2JavaFX;

import com.example.project2JavaFX.Classes.Customer;
import com.example.project2JavaFX.Classes.CustomerHolder;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeController implements Initializable {
    @FXML
    Label welcomeLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CustomerHolder customerHolder = CustomerHolder.getInstance();
        Customer customer = customerHolder.getCustomer();
        welcomeLabel.setText("Welcome " + customer.getPersonDetails().getName() + "!");
    }

    @FXML
    protected void onOKButton() {
        Stage stage = (Stage) welcomeLabel.getScene().getWindow();
        stage.close();
    }
}
