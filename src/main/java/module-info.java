module com.example.project2JavaFX {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens com.example.project2JavaFX to javafx.fxml;
    exports com.example.project2JavaFX;
    exports com.example.project2JavaFX.Exceptions;
    exports com.example.project2JavaFX.Classes;
    opens com.example.project2JavaFX.Classes to javafx.fxml;
}