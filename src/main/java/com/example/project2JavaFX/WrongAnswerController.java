package com.example.project2JavaFX;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class WrongAnswerController implements Initializable {
    @FXML
    Label warningLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        warningLabel.setText("INCORRECT ANSWER!\nProgram terminating...");
    }

    @FXML
    protected void onOKButton() {
        System.out.println("Application Closed.");
        javafx.application.Platform.exit();
    }
}
