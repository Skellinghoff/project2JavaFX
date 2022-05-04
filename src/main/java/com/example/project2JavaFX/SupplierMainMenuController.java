package com.example.project2JavaFX;

import com.example.project2JavaFX.Classes.Customer;
import com.example.project2JavaFX.Classes.CustomerHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SupplierMainMenuController implements Initializable {
    @FXML
    private Button processOrderButton;
    @FXML
    private Button shipOrderButton;
    @FXML
    private Button viewStockButton;
    @FXML
    private Button logOutButton;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onSelectItemsButton(ActionEvent event) {
    }

    public void onMakeOrderButton(ActionEvent event) {
    }

    public void onViewOrdersButton(ActionEvent event) {
    }

    public void onLogoutButton() throws IOException {
        CustomerHolder customerHolder = CustomerHolder.getInstance();
        customerHolder.setCustomer(new Customer());
        Stage stage = (Stage) logOutButton.getScene().getWindow();
        StageManagement.showOnSameStage(this, stage, "log-on-controller.fxml");
    }
}
