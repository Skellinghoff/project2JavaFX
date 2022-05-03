package com.example.project2JavaFX;

import com.example.project2JavaFX.Classes.Customer;
import com.example.project2JavaFX.Classes.CustomerHolder;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CustomerHolder customerHolder = CustomerHolder.getInstance();
        customer = customerHolder.getCustomer();
        setQuestionLabel();
    }

    protected void setQuestionLabel() {
        String question = customer.getPersonDetails().getSecurityQuestion();
        questionLabel.setText(question);
    }

    @FXML
    protected void onCancelButton() throws IOException {
        Stage stage = (Stage) submitButton.getScene().getWindow();
        StageManagement.showOnSameStage(this, stage, "log-on-view.fxml");
    }

    @FXML
    protected void onSubmitButton() throws IOException {
        String answer = customer.getPersonDetails().getSecurityAnswer();

        if (Objects.equals(answer, answerTextField.getText())) {
            CustomerHolder customerHolder = CustomerHolder.getInstance();
            customerHolder.setCustomer(customer);
            Stage stage = (Stage) submitButton.getScene().getWindow();
            StageManagement.showOnSameStage(this, stage, "main-view.fxml");
            StageManagement.showOnNewStage(this, "welcome-view.fxml");
        } else {
            StageManagement.showOnNewStage(this, "wrong-answer-view.fxml");
        }
    }
}
