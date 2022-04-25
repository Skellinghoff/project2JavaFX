package com.example.project2JavaFX;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LogOnController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent parent;
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
    private Customer[] customers;
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Log on Controller Initialized.");
        logInButton.setDisable(true);

//        try {
//            customers = getCustomers();
//        } catch (IOException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }

    }

    private static Customer[] getCustomers() throws IOException, ClassNotFoundException {
        final int NUM_ITEMS = 10;

        FileInputStream inStream = new FileInputStream("com/example/project2JavaFX/Customers.dat");

        ObjectInputStream objectInputFile = new ObjectInputStream(inStream);

        Customer[] customers = new Customer[NUM_ITEMS];

        for (int i = 0; i < customers.length; i++) {
            customers[i] = (Customer) objectInputFile.readObject();
        }

        // Close the file.
        objectInputFile.close();
        return customers;
    }

    @FXML
    protected void onSignUpButton() throws IOException {
        parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sign-up-view.fxml")));
        stage = (Stage) signUpButton.getScene().getWindow();
        scene = new Scene(parent);
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