package com.example.project2JavaFX;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class LogOnController implements Initializable {
    @FXML public Button cancelButton;
    @FXML
    private Label infoLabel;
    @FXML
    private TextField idTextField;
    @FXML
    private PasswordField passPasswordField;
    @FXML
    private Button logInButton;
    @FXML
    private Button signUpButton;
    private boolean isIDEmpty = true;
    private boolean isPassEmpty = true;
    private ArrayList<Customer> customers;
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Log on Controller Initialized.");
        logInButton.setDisable(true);

        try {
            customers = FileManagement.getCustomers();
        } catch (IOException | ClassNotFoundException e) {
            customers = null;
            throw new RuntimeException(e);
        }

    }

    @FXML
    protected void onSignUpButton() throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sign-up-step-one-view.fxml")));
        Stage stage = (Stage) signUpButton.getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    protected void onLogInButton() {
        String id_string = idTextField.getText();
        String pass_string = passPasswordField.getText();
    }
    @FXML
    protected void onKeyTypedIDTextField() {
        String id_string = idTextField.getText();
        isIDEmpty = id_string.isBlank();
        enableLogInButton();
        System.out.println("isIDEmpty: " + isIDEmpty);

    }
    @FXML
    protected void onKeyTypedPassPasswordField() {
        String pass_string = passPasswordField.getText();
        isPassEmpty = pass_string.isBlank();
        enableLogInButton();
        System.out.println("isPassEmpty: " + isPassEmpty);
    }
    protected void enableLogInButton() {
        logInButton.setDisable(isIDEmpty || isPassEmpty);
    }
    @FXML
    protected void onCancelButton() {
        System.out.println("Application Closed.");
        javafx.application.Platform.exit();
    }

}