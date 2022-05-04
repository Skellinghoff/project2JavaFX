package com.example.project2JavaFX;

import com.example.project2JavaFX.Classes.Customer;
import com.example.project2JavaFX.Classes.CustomerHolder;
import com.example.project2JavaFX.Classes.Supplier;
import com.example.project2JavaFX.Classes.SupplierHolder;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SecurityQuestionController implements Initializable {
    @FXML
    Button submitButton;
    @FXML
    TextField answerTextField;
    @FXML
    Label questionLabel;
    Customer customer;
    Supplier supplier;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CustomerHolder customerHolder = CustomerHolder.getInstance();
        SupplierHolder supplierHolder = SupplierHolder.getInstance();
        customer = customerHolder.getCustomer();
        supplier = supplierHolder.getSupplier();
        if (supplier != null)
            setQuestionLabel(supplier);
        else
            setQuestionLabel(customer);
    }

    protected void setQuestionLabel(Customer customer) {
        String question = customer.getPersonDetails().getSecurityQuestion();
        questionLabel.setText(question);
    }

    @FXML
    protected void onCancelButton() throws IOException {
        Stage stage = (Stage) submitButton.getScene().getWindow();
        StageManagement.showOnSameStage(this, stage, "log-on-controller.fxml");
    }

    @FXML
    protected void onSubmitButton() throws IOException {
        String answer;
        if (supplier != null) {
            answer = supplier.getPersonDetails().getSecurityAnswer();
        } else {
            answer = customer.getPersonDetails().getSecurityAnswer();
        }


        if (Objects.equals(answer, answerTextField.getText())) {
            Stage stage = (Stage) submitButton.getScene().getWindow();

            if (supplier != null) {
                SupplierHolder supplierHolder = SupplierHolder.getInstance();
                supplierHolder.setSupplier(supplier);
                StageManagement.showOnSameStage(this, stage, "supplier-main-menu-controller.fxml");
            } else {
                CustomerHolder customerHolder = CustomerHolder.getInstance();
                customerHolder.setCustomer(customer);
                StageManagement.showOnSameStage(this, stage, "main-controller.fxml");
            }
            StageManagement.showOnNewStage(this, "welcome-controller.fxml");
        } else {
            StageManagement.createDialog("Incorrect Answer","Program Terminating...", true);
        }
    }
}
