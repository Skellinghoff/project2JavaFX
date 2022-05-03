package com.example.project2JavaFX;

import com.example.project2JavaFX.Classes.Customer;
import com.example.project2JavaFX.Classes.CustomerHolder;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    public Button selectItemsButton;
    public Button makeOrderButton;
    public Button viewOrderButton;
    public Button logOutButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    protected void onSelectItemsButton() throws IOException {
        Stage stage = (Stage) selectItemsButton.getScene().getWindow();
        StageManagement.showOnSameStage(this, stage, "select-items-view.fxml");
    }

    @FXML
    protected void onMakeOrderButton() {

    }

    @FXML
    protected void onViewOrderButton() {

    }

    @FXML
    protected void onLogoutButton() throws IOException {
        CustomerHolder customerHolder = CustomerHolder.getInstance();
        customerHolder.setCustomer(new Customer());
        Stage stage = (Stage) logOutButton.getScene().getWindow();
        StageManagement.showOnSameStage(this, stage, "log-on-view.fxml");
    }
}
