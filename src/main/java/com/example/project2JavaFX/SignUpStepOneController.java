package com.example.project2JavaFX;

import com.example.project2JavaFX.Classes.Customer;
import com.example.project2JavaFX.Classes.CustomerHolder;
import com.example.project2JavaFX.Classes.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SignUpStepOneController implements Initializable {
    public PasswordField confirmPassPasswordField;
    @FXML
    private TextField idTextField;
    @FXML
    private ImageView idIcon;
    @FXML
    private PasswordField passPasswordField;
    @FXML
    private ImageView passIcon;
    @FXML
    private ImageView confirmPassIcon;
    @FXML
    private Button nextButton;
    private Image cancelImage;
    private Image checkedImage;
    private Image optionsImage;
    private boolean isConfirmPassEmpty = true;
    private boolean arePasswordsValid = false;
    private boolean isIDValid = false;
    private String passString = "";
    private String confirmPassString = "";
    private Customer[] customers;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            customers = FileManagement.getCustomers();
        } catch (IOException | ClassNotFoundException | InvocationTargetException e) {
            customers = null;
            throw new RuntimeException(e);
        }
        System.out.println("Sign Up Controller Initialized");
        try {
            FileInputStream cancelInputStream = new FileInputStream("src/main/java/com/example/project2JavaFX/Icons/cancel.png");
            FileInputStream checkedInputStream = new FileInputStream("src/main/java/com/example/project2JavaFX/Icons/checked.png");
            FileInputStream optionsInputStream = new FileInputStream("src/main/java/com/example/project2JavaFX/Icons/options.png");
            cancelImage = new Image(cancelInputStream, idIcon.getFitWidth(), 0, true, true);
            checkedImage = new Image(checkedInputStream, 37, 0, true, true);
            optionsImage = new Image(optionsInputStream, 37, 0, true, true);
        } catch (FileNotFoundException | NullPointerException e) {
            throw new RuntimeException(e);
        }

        idIcon.setOpacity(0);
        passIcon.setOpacity(0);
        confirmPassIcon.setOpacity(0);
        nextButton.setDisable(true);

    }

    @FXML
    protected void onKeyTypedTextField(KeyEvent event) {
        TextField n = (TextField) event.getSource();
        String source = n.getId();
        String content = n.getText();

        if ("idTextField".equals(source)) {
            if (content.isBlank()) {
                setIcon(idIcon, optionsImage, 1);
            }
            if (customers != null) {
                for (Customer c : customers) {
                    setIcon(idIcon, optionsImage, 1);
                    String id = c.getUser().getId();
                    System.out.println(id);
                    if (content.equals(id)) {
                        setIcon(idIcon, cancelImage, 1);
                        isIDValid = false;
                        break;
                    } else {
                        setIcon(idIcon, checkedImage, 1);
                        isIDValid = true;
                    }
                }
            }
        }

        System.out.println(content);
        enableNextButton();
    }

    @FXML
    protected void onKeyTypePasswordFields(KeyEvent event) {
        PasswordField n = (PasswordField) event.getSource();
        String source = n.getId();
        String content = n.getText();

        switch (source) {
            case "passPasswordField" -> {
                boolean isPassEmpty = content.isBlank();
                passString = content;
                if (isPasswordValid(content)) {
                    setIcon(passIcon, checkedImage, 1);
                } else if (isPassEmpty) {
                    setIcon(passIcon, checkedImage, 0);
                } else if (!isPasswordValid(content)) {
                    setIcon(passIcon, cancelImage, 1);
                }
            }
            case "confirmPassPasswordField" -> {
                isConfirmPassEmpty = content.isBlank();
                confirmPassString = content;

            }
        }

        if (isPasswordValid(content) && !isConfirmPassEmpty) {
            if (Objects.equals(passString, confirmPassString)) {
                setIcon(confirmPassIcon, checkedImage, 1);
                arePasswordsValid = true;
            } else {
                setIcon(confirmPassIcon, cancelImage, 1);
                arePasswordsValid = false;
            }
        } else {
            arePasswordsValid = false;
            setIcon(confirmPassIcon, cancelImage, 1);
        }
        enableNextButton();
    }

    protected void setIcon(ImageView imageView, Image image, double opacity) {
        imageView.setOpacity(opacity);
        imageView.setImage(image);
    }


    protected void enableNextButton() {
        nextButton.setDisable(!isIDValid || !arePasswordsValid);
    }

    @FXML
    protected void onNextButton() throws IOException {
        User u = new User(idTextField.getText(), passPasswordField.getText());
        CustomerHolder holder = CustomerHolder.getInstance();
        holder.setUser(u);
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sign-up-step-two-controller.fxml")));
        Stage stage = (Stage) nextButton.getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
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

    @FXML
    protected void onCancelButton() throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("log-on-controller.fxml")));
        Stage stage = (Stage) nextButton.getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

}
