package com.example.project2JavaFX;

import com.example.project2JavaFX.Classes.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
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
        CustomerHolder holder = CustomerHolder.getInstance();
        User user = holder.getUser();
        PersonalDetails personalDetails = holder.getPersonDetails();
        BankAccount bankAccount = holder.getBankAccount();
        personalDetails.setSecurityQuestion(questionComboBox.getValue());
        personalDetails.setSecurityAnswer(answerTextField.getText());
        Customer customer = new Customer(user, personalDetails, bankAccount);
        FileManagement.addCustomer(customer);

    }

    @FXML
    public void onCancelButton() throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("log-on-view.fxml")));
        Stage stage = (Stage) signUpButton.getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }
}
