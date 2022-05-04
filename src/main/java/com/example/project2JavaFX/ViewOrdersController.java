package com.example.project2JavaFX;

import com.example.project2JavaFX.Classes.Customer;
import com.example.project2JavaFX.Classes.CustomerHolder;
import com.example.project2JavaFX.Classes.Order;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewOrdersController implements Initializable {
    private Customer customer;
    private Order[] orders;
    @FXML
    private TableView<Order> orderTableView;
    @FXML
    private TableColumn<Order, String> dateColumn;
    @FXML
    private TableColumn<Order, String> productsColumn;
    @FXML
    private TableColumn<Order, String> quantitiesColumn;
    @FXML
    private TableColumn<Order, String> totalsColumn;
    @FXML
    private TableColumn<Order, String> orderStatusColumn;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initialize();
    }

    @FXML
    public void initialize() {
        CustomerHolder customerHolder = CustomerHolder.getInstance();
        customer = customerHolder.getCustomer();
        orders = customer.getOrders();
        long max = 0;
        for (Order order : orders) {
            String str = order.getItemsString();
            long count = str.chars().filter(ch -> ch == '\n').count();
            max = Math.max(max, count);
        }
        System.out.println(max);

        dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate()));
        productsColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getItemsString()));
        quantitiesColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getQuantitiesString()));
        totalsColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTotalsString()));
        orderStatusColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));

        orderTableView.setItems(FXCollections.observableArrayList(orders));
        orderTableView.setFixedCellSize(max * 25);
    }

    @FXML
    protected void onCloseButton() throws IOException {
        Stage stage = (Stage) orderTableView.getScene().getWindow();
        StageManagement.showOnSameStage(this, stage, "main-controller.fxml");
    }
}
