package com.example.project2JavaFX;

import com.example.project2JavaFX.Classes.*;
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
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;

public class LogOnController implements Initializable {
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
    private boolean isIDEmpty = true;
    private boolean isPassEmpty = true;
    private Customer[] customers;
    private Supplier[] suppliers;

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Log on Controller Initialized.");
        logInButton.setDisable(true);

        try {
            suppliers = FileManagement.getSuppliers();
            customers = FileManagement.getCustomers();
            System.out.println(Arrays.toString(suppliers));
            System.out.println(Arrays.toString(customers));
        } catch (IOException | ClassNotFoundException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onSignUpButton() throws IOException {
        Stage stage = (Stage) signUpButton.getScene().getWindow();
        StageManagement.showOnSameStage(this, stage, "sign-up-step-one-controller.fxml");
    }

    @FXML
    protected void onLogInButton() throws IOException {
        String idString = idTextField.getText();
        String passString = passPasswordField.getText();
        User tempUser = new User(idString, passString);
        CustomerHolder customerHolder = CustomerHolder.getInstance();
        SupplierHolder supplierHolder = SupplierHolder.getInstance();


        if (idMatches(tempUser)) {
            System.out.println(tempUser.getId() + " does exist.");
            if (passMatches(tempUser)) {
                String ans = supplierOrCustomer(tempUser);
                if (ans.equals("Customer")) {
                    Customer customer = findCustomer(tempUser);
                    customerHolder.setCustomer(customer);
                    supplierHolder.empty();
                } else if (ans.equals("Supplier")) {
                    Supplier supplier = findSupplier(tempUser);
                    supplierHolder.setSupplier(supplier);
                    customerHolder.empty();
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

    private String supplierOrCustomer(User user) {
        for (Customer c : customers) {
            if (Objects.equals(c.getUser().getId(), user.getId())) {
                return "Customer";
            }
        }
        for (Supplier s : suppliers) {
            if (Objects.equals(s.getUser().getId(), user.getId())) {
                return "Supplier";
            }
        }

        return "N";

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

    protected boolean idMatches(User user) {
        try {
            for (Customer c : customers) {
                if (Objects.equals(c.getUser().getId(), user.getId())) {
                    return true;
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        try {
            for (Supplier s : suppliers) {
                System.out.println(s.getUser());
                if (Objects.equals(s.getUser().getId(), user.getId())) {
                    return true;
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected boolean passMatches(User user) {
        try {
            for (Customer c : customers) {
                if (Objects.equals(c.getUser().getPassword(), user.getPassword())) {
                    return true;
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        try {
            for (Supplier s : suppliers) {
                if (Objects.equals(s.getUser().getPassword(), user.getPassword())) {
                    return true;
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected Customer findCustomer(User user) {
        Customer customer = null;
        for (Customer c : customers) {
            if (Objects.equals(c.getUser().getId(), user.getId())) {
                customer = c;
            }
        }
        return customer;
    }

    protected Supplier findSupplier(User user) {
        Supplier supplier = null;
        for (Supplier s : suppliers) {
            if (Objects.equals(s.getUser().getId(), user.getId())) {
                supplier = s;
            }
        }
        return supplier;
    }

    protected void updateTryLabel() {
        tries -= 1;
        if (tries == 0) {
            StageManagement.createDialog("Too many log in attempts!","Program terminating...", true);
        } else if (tries == 1) {
            tryLabel.setTextFill(Color.RED);
        }

        tryLabel.setText("Tries Left: " + tries);
    }

    protected void flashWelcomeLabel() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0), new KeyValue(welcomeLabel.textFillProperty(), Color.RED)),
                new KeyFrame(Duration.seconds(0.25), new KeyValue(welcomeLabel.textFillProperty(), Color.BLACK))
        );
        timeline.setCycleCount(3);
        timeline.setAutoReverse(true);
        timeline.play();

    }

}