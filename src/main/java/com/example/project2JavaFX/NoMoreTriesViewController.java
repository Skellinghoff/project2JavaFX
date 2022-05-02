package com.example.project2JavaFX;

import javafx.beans.DefaultProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;
@DefaultProperty("extension")
public class NoMoreTriesViewController implements Initializable {
    public GridPane extension;
    @FXML
    Label warningLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        warningLabel.setText("MAXIMUM TRIES REACHED!\nProgram terminating...");

    }

    @FXML
    protected void onOKButton() {
        System.out.println("Application Closed.");
        javafx.application.Platform.exit();
    }
}
