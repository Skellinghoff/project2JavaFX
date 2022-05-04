package com.example.project2JavaFX;

import com.example.project2JavaFX.Classes.Customer;
import com.example.project2JavaFX.Classes.CustomerHolder;
import com.example.project2JavaFX.Classes.Supplier;
import com.example.project2JavaFX.Classes.SupplierHolder;
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
        try {
            Customer customer = CustomerHolder.getInstance().getCustomer();
            welcomeLabel.setText("Welcome " + customer.getPersonDetails().getName() + "!");
        } catch (NullPointerException e) {
            System.out.println("no customer");
        }
        try {
            Supplier supplier = SupplierHolder.getInstance().getSupplier();
            welcomeLabel.setText("Welcome " + supplier.getPersonDetails().getName() + "!");
        } catch (NullPointerException e) {
            System.out.println("no supplier");
        }
    }

    @FXML
    protected void onOKButton() {
        Stage stage = (Stage) welcomeLabel.getScene().getWindow();
        stage.close();
    }
}
