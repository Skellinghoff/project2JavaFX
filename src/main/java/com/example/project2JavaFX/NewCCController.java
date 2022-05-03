package com.example.project2JavaFX;

import com.example.project2JavaFX.Classes.BankAccount;
import com.example.project2JavaFX.Classes.Customer;
import com.example.project2JavaFX.Classes.CustomerHolder;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class NewCCController implements Initializable {
    @FXML
    private TextField CCTextField;
    @FXML
    private Button nextButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    protected void onNextButton() throws IOException {
        CustomerHolder customerHolder = CustomerHolder.getInstance();
        Customer customer = customerHolder.getCustomer();
        customer.setBankAccount(new BankAccount(CCTextField.getText()));
        customerHolder.setCustomer(customer);
        Stage stage = (Stage) nextButton.getScene().getWindow();
        StageManagement.showOnSameStage(this, stage, "make-order-view.fxml");
    }
    @FXML
    protected void onKeyTypedTextField(KeyEvent event) {
        TextField n = (TextField) event.getSource();
        String source = n.getId();
        String content = n.getText();

        if (Objects.equals(source, "CCTextField")) {
            if (!content.matches("\\d*")) {
                n.setText(content.replaceAll("\\D", ""));
            }
        }
        nextButton.setDisable(CCTextField.getText().isBlank());
    }

    @FXML
    protected void onCancelButton() throws IOException {
        Stage stage = (Stage) nextButton.getScene().getWindow();
        StageManagement.showOnSameStage(this, stage, "make-order-view.fxml");
    }
}
