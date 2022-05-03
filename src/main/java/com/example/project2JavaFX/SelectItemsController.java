package com.example.project2JavaFX;

import com.example.project2JavaFX.Classes.Item;
import com.example.project2JavaFX.Classes.Order;
import com.example.project2JavaFX.Classes.OrderHolder;
import com.example.project2JavaFX.Classes.Product;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SelectItemsController implements Initializable {
    private Item[] items;
    private Product[] products;
    @FXML
    private TableView<Item> productTableView;
    @FXML
    private TableColumn<Item, String> productName;
    @FXML
    private TableColumn<Item, String> productDescription;
    @FXML
    private TableColumn<Item, String> productPrice;
    @FXML
    private TableColumn<Item, Item> productAddToCart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            products = FileManagement.getProducts();
        } catch (IOException | ClassNotFoundException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        items = new Item[products.length];
        for (int i = 0; i < products.length; i++) {
            items[i] = new Item(products[i], 0);
        }


        productName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProduct().getName()));
        wrapText(productName, true);
        productDescription.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProduct().getDescription()));
        wrapText(productDescription, false);
        productPrice.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProduct().getRegularPriceString()));
        productAddToCart.setCellValueFactory(cd -> Bindings.createObjectBinding(cd::getValue));

        productAddToCart.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Item, Item> call(TableColumn<Item, Item> param) {

                return new TableCell<>() {

                    private final Spinner<Integer> count;

                    private final SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory;
                    private final ChangeListener<Number> valueChangeListener;

                    {
                        valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10);
                        count = new Spinner<>(valueFactory);
                        setGraphic(count);
                        valueChangeListener = (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
                            valueFactory.setValue(newValue.intValue());
                        };
                        count.valueProperty().addListener((obs, oldValue, newValue) -> {
                            if (getItem() != null) {
                                // write new value to table item
                                getItem().setCount(newValue);
                            }
                        });
                    }

                    @Override
                    public void updateItem(Item item, boolean empty) {

                        // unbind old values
                        valueFactory.maxProperty().unbind();
                        if (getItem() != null) {
                            getItem().itemCountProperty().removeListener(valueChangeListener);
                        }

                        super.updateItem(item, empty);

                        if (empty || item == null) {
                            count.setVisible(false);
                        } else {
                            valueFactory.setValue(item.getCount());
                            item.itemCountProperty().addListener(valueChangeListener);
                            count.setVisible(true);
                        }

                    }
                };
            }
        });

        productTableView.setItems(FXCollections.observableArrayList(items));

    }

    private void wrapText(TableColumn<Item, String> column, boolean center) {
        column.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Item, String> call(TableColumn<Item, String> productStringTableColumn) {
                return new TableCell<>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                            Text text = new Text(item);
                            if (center) text.setTextAlignment(TextAlignment.CENTER);
                            text.setWrappingWidth(column.getWidth()); // Setting the wrapping width to the Text
                            setGraphic(text);
                        }
                    }
                };
            }
        });
    }

    private Item[] getTableViewValues() {
        ArrayList<Item> order = new ArrayList<>();
        ObservableList<TableColumn<Item, ?>> columns = productTableView.getColumns();
        ObservableList<Item> countArray = productTableView.getItems();

        for (Item item : countArray) {
            if (item.getCount() > 0) {
                order.add(item);
                System.out.println("Product: " + item.getProduct().getName() + ", quantity: " + item.getCount());
            }
        }
        return order.toArray(new Item[0]);
    }

    @FXML
    protected void onDoneButton() {
        Item[] items = getTableViewValues();
        OrderHolder orderHolder = OrderHolder.getInstance();
        if (items.length > 0) {
            orderHolder.setOrder(new Order(items));
        } else {
            orderHolder.setOrder(null);
        }

        Stage stage = (Stage) productTableView.getScene().getWindow();
        try {
            StageManagement.showOnSameStage(this, stage, "main-controller.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
