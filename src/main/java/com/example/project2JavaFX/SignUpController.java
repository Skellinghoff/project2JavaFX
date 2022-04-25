package com.example.project2JavaFX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    @FXML private TextField idTextField;
    @FXML private ImageView idIcon;
    @FXML private PasswordField passPasswordField;
    @FXML private ImageView passIcon;
    @FXML private PasswordField confirmPassPasswordField;
    @FXML private ImageView confirmPassIcon;
    @FXML private TextField nameTextField;
    @FXML private TextField addressTextField;
    @FXML private TextField creditCardTextField;
    @FXML private TextField securityAnswerTextField;
    @FXML private ComboBox<String> securityQuestionsComboBox = new ComboBox<>();
    @FXML private Button signUpButton;
    private Image cancelImage;
    private Image checkedImage;
    private Image optionsImage;
    private boolean isIDEmpty = true;
    private boolean isPassEmpty = true;
    private boolean isConfirmPassEmpty = true;
    private boolean isNameEmpty = true;
    private boolean isAddressEmpty = true;
    private boolean isCCEmpty = true;
    private boolean isSecurityAnswerEmpty = true;
    private boolean arePasswordsValid = false;
    private String passString = "";
    private String confirmPassString = "";




    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Sign Up Controller Initialized");
        ObservableList<String> questions =
                FXCollections.observableArrayList(
                        "What city were you born in?",
                        "What is your oldest sibling’s middle name?",
                        "What was the first concert you attended?",
                        "What was the make and model of your first car?",
                        "In what city or town did your parents meet?"
                );
        securityQuestionsComboBox.getItems().addAll(questions);
        try {
            FileInputStream cancelInputStream = new FileInputStream("src/main/resources/com/example/project2JavaFX/icons/cancel-svgrepo-com.png");
            FileInputStream checkedInputStream = new FileInputStream("src/main/resources/com/example/project2JavaFX/icons/checked-svgrepo-com.png");
            FileInputStream optionsInputStream = new FileInputStream("src/main/resources/com/example/project2JavaFX/icons/options-svgrepo-com.png");
            cancelImage = new Image(cancelInputStream, idIcon.getFitWidth(), 0, true, true);
            checkedImage = new Image(checkedInputStream, 37, 0, true, true);
            optionsImage = new Image(optionsInputStream, 37, 0, true, true);
        } catch (FileNotFoundException | NullPointerException e) {
            throw new RuntimeException(e);
        }

        idIcon.setOpacity(0);
        passIcon.setOpacity(0);
        confirmPassIcon.setOpacity(0);
        signUpButton.setDisable(true);
        passPasswordField.setDisable(true);
        confirmPassPasswordField.setDisable(true);
        nameTextField.setDisable(true);
        addressTextField.setDisable(true);
        creditCardTextField.setDisable(true);
        securityQuestionsComboBox.setDisable(true);
        securityAnswerTextField.setDisable(true);

    }
    @FXML protected void onKeyTypedTextField(KeyEvent event) {
        TextField n = (TextField) event.getSource();
        String source = n.getId();
        String content = n.getText();

        switch (source) {
            case "idTextField" -> {
                isIDEmpty = content.isBlank();
            }
            case "nameTextField" -> isNameEmpty = content.isBlank();
            case "addressTextField" -> isAddressEmpty = content.isBlank();
            case "creditCardTextField" -> isCCEmpty = content.isBlank();
            case "securityAnswer" -> isSecurityAnswerEmpty = content.isBlank();
        }

        System.out.println(content);
        enableSignUpButton();
    }
    @FXML protected void onKeyTypePasswordFields(KeyEvent event) {
        PasswordField n = (PasswordField) event.getSource();
        String source = n.getId();
        String content = n.getText();

        switch (source) {
            case "passPasswordField" -> {
                isPassEmpty = content.isBlank();
                passString = content;
                if (isPasswordValid(content)) {
                    passIcon.setOpacity(1);
                    passIcon.setImage(checkedImage);
                } else if (isPassEmpty) {
                    passIcon.setOpacity(0);
                } else if (!isPasswordValid(content)) {
                    passIcon.setOpacity(1);
                    passIcon.setImage(cancelImage);
                }
            }
            case "confirmPassPasswordField" -> {
                isConfirmPassEmpty = content.isBlank();
                confirmPassString = content;

            }
        }

        if (!isPassEmpty && !isConfirmPassEmpty) {
            if (Objects.equals(passString, confirmPassString)) {
                confirmPassIcon.setOpacity(1);
                confirmPassIcon.setImage(checkedImage);
                arePasswordsValid = true;
            } else {
                confirmPassIcon.setOpacity(1);
                confirmPassIcon.setImage(cancelImage);
                arePasswordsValid = false;
            }
        } else {
            arePasswordsValid = false;
            confirmPassIcon.setOpacity(0);
        }
        enableSignUpButton();
    }

    protected void enableSignUpButton() {
        signUpButton.setDisable(isIDEmpty || !arePasswordsValid || isNameEmpty || isAddressEmpty || isCCEmpty || isSecurityAnswerEmpty);
    }

    protected boolean isPasswordValid(String password) {
        boolean hasDigit = false;
        boolean hasSpecialChar = false;
        boolean hasUpper = false;
        char[] specialChars = {'@', '#', '$', '%', '&', '*', '!', '^', '<', '>', '?'};

        char[] ch = password.toCharArray();

        if (ch.length < 6)
            return false;

        for (char c : ch) {
            if (Character.isDigit(c))
                hasDigit = true;
            else if (Character.isUpperCase(c))
                hasUpper = true;
            for (char s : specialChars)
                if (c == s) {
                    hasSpecialChar = true;
                    break;
                }
        }

        return hasDigit && hasUpper && hasSpecialChar;
    }

}
