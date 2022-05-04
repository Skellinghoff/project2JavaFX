package com.example.project2JavaFX;

import com.example.project2JavaFX.Classes.Order;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProcessOrderController implements Initializable {
    Order[] orders;
    @FXML
    private TableView<Order> orderTableView;
    @FXML
    private TableColumn<Order, String> dateColumn;
    @FXML
    private TableColumn<Order, String> productsColumn;
    @FXML
    private TableColumn<Order, String> quantityColumn;
    @FXML
    private TableColumn<Order, String> priceColumn;
    @FXML
    private TableColumn<Order, String> stockAvailableColumn;
    @FXML
    private TableColumn<Order, String> orderStatus;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            orders = FileManagement.getOrders();
        } catch (IOException | ClassNotFoundException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        long max = 2;
        for (Order order : orders) {
            String str = order.getItemsString();
            long count = str.chars().filter(ch -> ch == '\n').count();
            max = Math.max(max, count);
        }

        dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate()));
        productsColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getItemsString()));
        quantityColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getQuantitiesString()));
        priceColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTotalsString()));

        orderTableView.setItems(FXCollections.observableArrayList(orders));
        orderTableView.setFixedCellSize(max * 25);



    }
}
