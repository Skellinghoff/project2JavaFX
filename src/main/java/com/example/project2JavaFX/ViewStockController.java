package com.example.project2JavaFX;

import com.example.project2JavaFX.Classes.Product;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewStockController implements Initializable {
    Product[] products;

    @FXML
    private TableView<Product> productTableView;
    @FXML
    private TableColumn<Product, String> productColumn;
    @FXML
    private TableColumn<Product, String> quantityColumn;
    @FXML
    private TableColumn<Product, String> availableColumn;
    @FXML
    private TableColumn<Product, String> reservedColumn;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            products = FileManagement.getProducts();
        } catch (IOException | ClassNotFoundException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        productColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        quantityColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTotalStockString()));
        availableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStockAvailableString()));
        reservedColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStockReservedString()));

        productTableView.setItems(FXCollections.observableArrayList(products));
    }

    @FXML
    protected void onDoneButton() {
        Stage stage = (Stage) productTableView.getScene().getWindow();
        try {
            StageManagement.showOnSameStage(this, stage, "supplier-main-menu-controller.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
