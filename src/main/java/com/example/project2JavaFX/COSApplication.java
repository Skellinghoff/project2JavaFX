package com.example.project2JavaFX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class COSApplication extends Application {
    private Stage primaryStage;
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("log-on-view.fxml")));
        Scene scene = new Scene(parent);
        primaryStage.setTitle("COSApplication");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml)));

        primaryStage.getScene().setRoot(pane);
    }
}