package com.example.project2JavaFX;

import com.example.project2JavaFX.Classes.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SignUpStepTwoController implements Initializable {
    @FXML
    private CheckBox supplierCheckBox;
    @FXML
    private Button nextButton;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField CCTextField;

    private User user;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CustomerHolder customerHolder = CustomerHolder.getInstance();
        user = customerHolder.getUser();
        System.out.println(user);
        nextButton.setDisable(true);
    }


    @FXML
    protected void onNextButton() throws IOException {
        CustomerHolder customerHolder = CustomerHolder.getInstance();
        if (!supplierCheckBox.isSelected()) {
            PersonalDetails personalDetails = new PersonalDetails(nameTextField.getText(), addressTextField.getText());
            customerHolder.setBankAccount(new BankAccount(CCTextField.getText()));
            customerHolder.setPersonDetails(personalDetails);
            customerHolder.setUser(user);
        } else {
            SupplierHolder supplierHolder = SupplierHolder.getInstance();
            PersonalDetails personalDetails = new PersonalDetails(nameTextField.getText(), addressTextField.getText());
            if (!CCTextField.getText().isBlank())
                supplierHolder.setBankAccount(new BankAccount(CCTextField.getText()));
            supplierHolder.setPersonDetails(personalDetails);
            supplierHolder.setUser(user);
            customerHolder.empty();
        }
        Stage stage = (Stage) nextButton.getScene().getWindow();
        StageManagement.showOnSameStage(this, stage, "sign-up-step-three-controller.fxml");
    }

    @FXML
    protected void onKeyTypedTextField(@NotNull KeyEvent event) {
        TextField n = (TextField) event.getSource();
        String source = n.getId();
        String content = n.getText();

        if (Objects.equals(source, "CCTextField")) {
            if (!content.matches("\\d*")) {
                n.setText(content.replaceAll("\\D", ""));
            }
        }
        enableNextButton();
    }

    @FXML
    protected void onCancelButton() throws IOException {
        Stage stage = (Stage) nextButton.getScene().getWindow();
        StageManagement.showOnSameStage(this, stage, "log-on-controller.fxml");
    }

    @FXML
    protected void onCheckBox() {
        enableNextButton();
    }

    protected void enableNextButton() {
        if (supplierCheckBox.isSelected())
            nextButton.setDisable(nameTextField.getText().isBlank() || addressTextField.getText().isBlank());
        else
            nextButton.setDisable(nameTextField.getText().isBlank() || addressTextField.getText().isBlank() || CCTextField.getText().isBlank());
    }
}
