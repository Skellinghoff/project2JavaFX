package com.example.project2JavaFX;

import com.example.project2JavaFX.Classes.*;
import com.example.project2JavaFX.Exceptions.NegativeWithdrawalException;
import com.example.project2JavaFX.Exceptions.OverdrawException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class MakeOrderController implements Initializable {
    private final DecimalFormat dfMoney = FileManagement.dfMoney;
    private double grandTotal;
    private double itemTotal;
    private Customer customer;
    private Order order;
    private final double shippingRate = 3.00;
    @FXML
    private TableView<Item> productTableView;
    @FXML
    private TableColumn<Item, String> productQuantity;
    @FXML
    private TableColumn<Item, String> productPrice;
    @FXML
    private TableColumn<Item, String> productName;
    @FXML
    private TableColumn<Item, String> itemSubtotalLabel;
    @FXML
    private Label shippingTotalLabel;
    @FXML
    private Label itemTotalLabel;
    @FXML
    private Label beforeTaxLabel;
    @FXML
    private Label taxTotalLabel;
    @FXML
    private Label orderTotalLabel;
    @FXML
    private Label paymentMethodLabel;
    @FXML
    private Label shippingAddressLabel;
    @FXML
    private Button placeOrderButton;
    @FXML
    private Button cancelButton;
    @FXML
    private RadioButton pickUpRadioButton;
    @FXML
    private RadioButton shipByMailRadioButton;
    private final ToggleGroup toggleGroup = new ToggleGroup();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pickUpRadioButton.setToggleGroup(toggleGroup);
        pickUpRadioButton.setSelected(true);
        shipByMailRadioButton.setToggleGroup(toggleGroup);
        shipByMailRadioButton.setText("Ship via Mail ($" + dfMoney.format(shippingRate) + ")");

        customer = CustomerHolder.getInstance().getCustomer();
        order = OrderHolder.getInstance().getOrder();

        productName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProduct().getName()));
        productPrice.setCellValueFactory(cellData -> new SimpleStringProperty("$" + cellData.getValue().getProduct().getRegularPriceString()));
        productQuantity.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCountString()));
        itemSubtotalLabel.setCellValueFactory(cellData -> new SimpleStringProperty("$" + cellData.getValue().getItemTotal()));

        productTableView.setItems(FXCollections.observableArrayList(order.getItems()));
        setItemTotalLabel();
        setLabels();
    }

    protected void setItemTotalLabel() {
        Item[] items = order.getItems();

        double sum = 0.0;
        for (Item item : items) {
            sum += Double.parseDouble(item.getItemTotal());
        }
        itemTotalLabel.setText("$" + dfMoney.format(sum));
        this.itemTotal = sum;
    }

    @FXML
    protected void onRadioButton() {
        setLabels();
    }

    protected void setLabels() {
        paymentMethodLabel.setText("CC#:\n" + customer.getBankAccount().getCreditCardNumber());
        double taxRate = 0.0625;
        if (toggleGroup.getSelectedToggle().equals(pickUpRadioButton)) {
            shippingTotalLabel.setText("FREE");
            beforeTaxLabel.setText(itemTotalLabel.getText());
            taxTotalLabel.setText("$" + dfMoney.format(itemTotal * taxRate));
            this.grandTotal = itemTotal * (1.00 + taxRate);
            orderTotalLabel.setText("$" + dfMoney.format(itemTotal * (1.00 + taxRate)));
            shippingAddressLabel.setText("PICK UP @ STORE");
        } else if (toggleGroup.getSelectedToggle().equals(shipByMailRadioButton)) {
            shippingTotalLabel.setText("$" + dfMoney.format(shippingRate));
            beforeTaxLabel.setText("$" + dfMoney.format(shippingRate + itemTotal));
            taxTotalLabel.setText("$" + dfMoney.format((shippingRate + itemTotal) * taxRate));
            this.grandTotal = (shippingRate + itemTotal) * (1.00 + taxRate);
            orderTotalLabel.setText("$" + dfMoney.format((shippingRate + itemTotal) * (1.00 + taxRate)));
            shippingAddressLabel.setText(customer.getPersonDetails().getAddress());
        }
    }

    @FXML
    protected void setPlaceOrderButton() throws IOException {
        BankAccount bankAccount = customer.getBankAccount();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date date = new Date();
        try {

            bankAccount.withdraw(grandTotal);
            String confirmationNumber = bankAccount.getConfirmationNumber();
            order.setAuthNumber(confirmationNumber);
            order.setDate(formatter.format(date));
            order.setTotal(dfMoney.format(grandTotal));
            order.setCustomerID(customer.getUser().getId());
            customer.addOrder(order);

            OrderHolder.getInstance().setOrder(null);

            Stage stage = (Stage) cancelButton.getScene().getWindow();
            StageManagement.showOnSameStage(this, stage, "main-controller.fxml");

        } catch (NegativeWithdrawalException | OverdrawException e) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("New Credit Card");
            dialog.setHeaderText("Something went wrong...");
            dialog.setContentText("Enter in new Credit Card Number:");
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                customer.setBankAccount(new BankAccount(result.get()));
                FileManagement.updateCustomer(customer);
                CustomerHolder.getInstance().setCustomer(customer);
                setLabels();
            }
        }
    }

    @FXML
    protected void onCancelButton() throws IOException {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        StageManagement.showOnSameStage(this, stage, "main-controller.fxml");
    }
}
