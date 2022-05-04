package com.example.project2JavaFX;

import com.example.project2JavaFX.Exceptions.NegativeStartingBalanceException;
import javafx.stage.Stage;

import java.io.IOException;

public class OPSApplication extends COSApplication {
    @Override
    public void start(Stage primaryStage) throws IOException, NegativeStartingBalanceException {
        super.start(primaryStage);
        primaryStage.setTitle("OPS");
    }
    public static void main(String[] args) {
        launch();
    }
}
