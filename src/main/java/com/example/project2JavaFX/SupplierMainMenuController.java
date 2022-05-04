package com.example.project2JavaFX;

import com.example.project2JavaFX.Classes.Supplier;
import com.example.project2JavaFX.Classes.SupplierHolder;
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
    @FXML
    public void onProcessOrderButton() throws IOException {
        Stage stage = (Stage) processOrderButton.getScene().getWindow();
        StageManagement.showOnSameStage(this, stage, "process-order-controller.fxml");
    }

    @FXML
    public void onShipOrderButton() throws IOException {
        Stage stage = (Stage) shipOrderButton.getScene().getWindow();
        StageManagement.showOnSameStage(this, stage, "ship-order-controller.fxml");
    }

    @FXML
    public void onViewStockButton() throws IOException {
        Stage stage = (Stage) viewStockButton.getScene().getWindow();
        StageManagement.showOnSameStage(this, stage, "view-stock-controller.fxml");
    }

    @FXML
    public void onLogoutButton() throws IOException {
        SupplierHolder.getInstance().setSupplier(new Supplier());
        Stage stage = (Stage) logOutButton.getScene().getWindow();
        StageManagement.showOnSameStage(this, stage, "log-on-controller.fxml");
    }
}
