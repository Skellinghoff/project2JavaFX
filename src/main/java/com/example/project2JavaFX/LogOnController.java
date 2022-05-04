package com.example.project2JavaFX;

import com.example.project2JavaFX.Classes.*;
import com.example.project2JavaFX.Classes.UserType;
import com.example.project2JavaFX.Exceptions.NegativeStartingBalanceException;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LogOnController implements Initializable {
    private UserType userType;
    @FXML
    private Label welcomeLabel;
    @FXML
    private Label tryLabel;
    private int tries = 3;
    @FXML
    private TextField idTextField;
    @FXML
    private PasswordField passPasswordField;
    @FXML
    private Button logInButton;
    @FXML
    private Button signUpButton;
    private Customer[] customers;
    private Supplier[] suppliers;

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Log on Controller Initialized.");
        logInButton.setDisable(true);

        try {
            customers = FileManagement.getCustomers();
        } catch (IOException | ClassNotFoundException | InvocationTargetException e) {
            System.out.println("Something went wrong opening Customers.dat");
            System.out.println("Loading example data");
            try {
                FileManagement.setExampleCustomers();
                customers = FileManagement.getCustomers();
            } catch (IOException | NegativeStartingBalanceException | ClassNotFoundException | InvocationTargetException ex) {
                ex.printStackTrace();
            }
        }

        try {
            suppliers = FileManagement.getSuppliers();
        } catch (IOException | ClassNotFoundException | InvocationTargetException e) {
            System.out.println("Something went wrong opening Suppliers.dat");
            System.out.println("Loading example data");
            try {
                FileManagement.setExampleSuppliers();
                suppliers = FileManagement.getSuppliers();
            } catch (IOException | ClassNotFoundException | InvocationTargetException ex) {
                ex.printStackTrace();
            }
        }
    }

    @FXML
    private void onSignUpButton() throws IOException {
        Stage stage = (Stage) signUpButton.getScene().getWindow();
        StageManagement.showOnSameStage(this, stage, "sign-up-step-one-controller.fxml");
    }

    @FXML
    private void onLogInButton() throws IOException {
        CustomerHolder customerHolder = CustomerHolder.getInstance();
        SupplierHolder supplierHolder = SupplierHolder.getInstance();

        String idString = idTextField.getText();
        String passString = passPasswordField.getText();
        User tempUser = new User(idString, passString);
        this.userType = idMatches(tempUser);

        if (this.userType != UserType.INVALID) {
            System.out.println(tempUser.getId() + " does exist.");
            if (passMatches(tempUser)) {
                switch (this.userType) {
                    case CUSTOMER -> {
                        Customer customer = findCustomer(tempUser);
                        customerHolder.setCustomer(customer);
                        supplierHolder.empty();
                    }
                    case SUPPLER -> {
                        Supplier supplier = findSupplier(tempUser);
                        supplierHolder.setSupplier(supplier);
                        customerHolder.empty();
                    }
                }
                Stage stage = (Stage) signUpButton.getScene().getWindow();
                StageManagement.showOnSameStage(this, stage, "security-question-controller.fxml");
            } else {
                updateTryLabel();
                welcomeLabel.setText("Incorrect ID/password!");
                flashWelcomeLabel();
                System.out.println("password does not match");
            }
        } else {
            welcomeLabel.setText("Incorrect ID/password!");
            flashWelcomeLabel();
            System.out.println(tempUser.getId() + " does not exist.");
        }
    }

    @FXML
    private void onKeyTypedIDTextField() {
        enableLogInButton();

    }

    @FXML
    private void onKeyTypedPassPasswordField() {
        enableLogInButton();
    }

    private void enableLogInButton() {
        logInButton.setDisable(idTextField.getText().isEmpty() || passPasswordField.getText().isEmpty());
    }

    @FXML
    private void onCancelButton() {
        System.out.println("Application Closed.");
        javafx.application.Platform.exit();
    }

    private UserType idMatches(User user) {
        for (Customer c : customers) {
            if (Objects.equals(c.getUser().getId(), user.getId())) {
                return UserType.CUSTOMER;
            }
        }
        for (Supplier s : suppliers) {
            if (Objects.equals(s.getUser().getId(), user.getId())) {
                return UserType.SUPPLER;
            }
        }
        return UserType.INVALID;
    }

    private boolean passMatches(User user) {
        switch (this.userType) {
            case CUSTOMER -> {
                for (Customer c : customers) {
                    if (Objects.equals(c.getUser().getPassword(), user.getPassword())) {
                        return true;
                    }
                }
            }
            case SUPPLER -> {
                for (Supplier s : suppliers) {
                    if (Objects.equals(s.getUser().getPassword(), user.getPassword())) {
                        return true;
                    }
                }
            }
        }   return false;
    }

    private Customer findCustomer(User user) {
        Customer customer = null;
        for (Customer c : customers) {
            if (Objects.equals(c.getUser().getId(), user.getId())) {
                customer = c;
            }
        }
        return customer;
    }

    private Supplier findSupplier(User user) {
        Supplier supplier = null;
        for (Supplier s : suppliers) {
            if (Objects.equals(s.getUser().getId(), user.getId())) {
                supplier = s;
            }
        }
        return supplier;
    }

    private void updateTryLabel() {
        tries -= 1;
        if (tries == 0) {
            StageManagement.createDialog("Too many log in attempts!","Program terminating...", true);
        } else if (tries == 1) {
            tryLabel.setTextFill(Color.RED);
        }

        tryLabel.setText("Tries Left: " + tries);
    }

    private void flashWelcomeLabel() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0), new KeyValue(welcomeLabel.textFillProperty(), Color.RED)),
                new KeyFrame(Duration.seconds(0.25), new KeyValue(welcomeLabel.textFillProperty(), Color.BLACK))
        );
        timeline.setCycleCount(3);
        timeline.setAutoReverse(true);
        timeline.play();

    }
}