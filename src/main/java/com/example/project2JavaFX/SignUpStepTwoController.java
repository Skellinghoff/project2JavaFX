package com.example.project2JavaFX;

import com.example.project2JavaFX.Classes.BankAccount;
import com.example.project2JavaFX.Classes.CustomerHolder;
import com.example.project2JavaFX.Classes.PersonalDetails;
import com.example.project2JavaFX.Classes.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SignUpStepTwoController implements Initializable {
    @FXML
    Button nextButton;
    @FXML
    TextField nameTextField;
    @FXML
    TextField addressTextField;
    @FXML
    TextField CCTextField;

    private User user;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CustomerHolder holder = CustomerHolder.getInstance();
        user = holder.getUser();
        System.out.println(user);
        nextButton.setDisable(true);
    }


    @FXML
    protected void onNextButton() throws IOException {
        CustomerHolder holder = CustomerHolder.getInstance();
        PersonalDetails personalDetails = new PersonalDetails(nameTextField.getText(), addressTextField.getText());
        holder.setBankAccount(new BankAccount(CCTextField.getText()));
        holder.setPersonDetails(personalDetails);
        holder.setUser(user);
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sign-up-step-three-view.fxml")));
        Stage stage = (Stage) nextButton.getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
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
        nextButton.setDisable(nameTextField.getText().isBlank() || addressTextField.getText().isBlank() || CCTextField.getText().isBlank());
    }

    @FXML
    protected void onCancelButton() throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("log-on-view.fxml")));
        Stage stage = (Stage) nextButton.getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }
}
