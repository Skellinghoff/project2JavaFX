package com.example.project2JavaFX;

import com.example.project2JavaFX.Classes.Customer;
import com.example.project2JavaFX.Classes.CustomerHolder;
import com.example.project2JavaFX.Classes.Order;
import com.example.project2JavaFX.Classes.OrderHolder;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    @FXML
    private Button selectItemsButton;
    @FXML
    private Button makeOrderButton;
    @FXML
    private Button viewOrdersButton;
    @FXML
    private Button logOutButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        OrderHolder orderHolder = OrderHolder.getInstance();
        Order order = orderHolder.getOrder();

        CustomerHolder customerHolder = CustomerHolder.getInstance();
        Customer customer = customerHolder.getCustomer();
        System.out.println(customer);
        Order[] orders = customer.getOrders();

        makeOrderButton.setDisable(order == null);
        viewOrdersButton.setDisable(orders == null || orders.length == 0);

    }

    @FXML
    protected void onSelectItemsButton() throws IOException {
        Stage stage = (Stage) selectItemsButton.getScene().getWindow();
        StageManagement.showOnSameStage(this, stage, "select-items-view.fxml");
    }

    @FXML
    protected void onMakeOrderButton() throws IOException {
        Stage stage = (Stage) selectItemsButton.getScene().getWindow();
        StageManagement.showOnSameStage(this, stage, "make-order-controller.fxml");

    }

    @FXML
    protected void onViewOrdersButton() throws IOException {
        Stage stage = (Stage) selectItemsButton.getScene().getWindow();
        StageManagement.showOnSameStage(this, stage, "view-orders-view.fxml");

    }

    @FXML
    protected void onLogoutButton() throws IOException {
        CustomerHolder customerHolder = CustomerHolder.getInstance();
        customerHolder.setCustomer(new Customer());
        Stage stage = (Stage) logOutButton.getScene().getWindow();
        StageManagement.showOnSameStage(this, stage, "log-on-controller.fxml");
    }
}
