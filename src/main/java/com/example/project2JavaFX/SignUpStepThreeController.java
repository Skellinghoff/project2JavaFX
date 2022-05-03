package com.example.project2JavaFX;

import com.example.project2JavaFX.Classes.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpStepThreeController implements Initializable {
    @FXML
    Button signUpButton;
    @FXML
    TextField answerTextField;
    @FXML
    ComboBox<String> questionComboBox = new ComboBox<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        questionComboBox.getItems().addAll(
                "What city were you born in?",
                "What is your oldest sibling’s middle name?",
                "What was the first concert you attended?",
                "What was the make and model of your first car?",
                "In what city or town did your parents meet?",
                "What is your oldest cousin's first and last name? ",
                "In what town or city did you meet your spouse or partner?"
        );
    }

    @FXML
    protected void onKeyTypedTextField() {
        System.out.println(questionComboBox.getValue());
        signUpButton.setDisable(answerTextField.getText().isBlank());
    }

    @FXML
    protected void onSignUpButton() throws IOException {
        CustomerHolder customerHolder = CustomerHolder.getInstance();

        if (customerHolder.getUser() != null) {
            User user = customerHolder.getUser();
            PersonalDetails personalDetails = customerHolder.getPersonDetails();
            BankAccount bankAccount = customerHolder.getBankAccount();
            personalDetails.setSecurityQuestion(questionComboBox.getValue());
            personalDetails.setSecurityAnswer(answerTextField.getText());

            Customer customer = new Customer(user, personalDetails, bankAccount);
            FileManagement.addCustomer(customer);

            customerHolder.setCustomer(customer);
            Stage stage = (Stage) signUpButton.getScene().getWindow();
            StageManagement.showOnSameStage(this, stage, "main-controller.fxml");
            StageManagement.showOnNewStage(this, "welcome-view.fxml");
        } else {
            SupplierHolder supplierHolder = SupplierHolder.getInstance();
            User user = supplierHolder.getUser();
            PersonalDetails personalDetails = supplierHolder.getPersonDetails();
            BankAccount bankAccount = supplierHolder.getBankAccount();
            personalDetails.setSecurityQuestion(questionComboBox.getValue());
            personalDetails.setSecurityAnswer(answerTextField.getText());

            Supplier supplier = new Supplier(user, personalDetails, bankAccount);
            FileManagement.addCustomer(supplier);

            supplierHolder.setCustomer(supplier);
            Stage stage = (Stage) signUpButton.getScene().getWindow();
            StageManagement.showOnSameStage(this, stage, "main-controller.fxml");
            StageManagement.showOnNewStage(this, "welcome-view.fxml");

        }

    }

    @FXML
    public void onCancelButton() throws IOException {
        Stage stage = (Stage) signUpButton.getScene().getWindow();
        StageManagement.showOnSameStage(this, stage, "log-on-controller.fxml");
    }
}
