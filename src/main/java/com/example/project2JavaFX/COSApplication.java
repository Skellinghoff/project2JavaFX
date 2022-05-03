package com.example.project2JavaFX;

import com.example.project2JavaFX.Exceptions.NegativeStartingBalanceException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class COSApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("log-on-view.fxml")));
        Scene scene = new Scene(parent);
        primaryStage.setTitle("COSApplication");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) throws NegativeStartingBalanceException, IOException {
//        FileManagement.setExampleCustomers();
//        FileManagement.setProducts();
        launch();
    }
}