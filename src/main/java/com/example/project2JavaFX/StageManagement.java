package com.example.project2JavaFX;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class StageManagement {
    public static void showOnNewStage(Object object, String resource) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(object.getClass().getResource(resource)));
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();

    }

    public static void showOnSameStage(Object object, Stage stage, String resource) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(object.getClass().getResource(resource)));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }
}
