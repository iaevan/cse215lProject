/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.*;

public class SalespersonViewController {

    private ComboBox<Product> productComboBox;
    private TextField quantityField;
    private TableView<OrderItem> cartTable;
    private ObservableList<OrderItem> cartData;

    public Pane createView(Stage primaryStage) {

        productComboBox = new ComboBox<>();
        productComboBox.setItems(FXCollections.observableArrayList(DataStorage.getProducts()));

        quantityField = new TextField();
        quantityField.setPromptText("Quantity");

        Button addToCartButton = new Button("Add to Cart");
        addToCartButton.setOnAction(e -> {
            Product selectedProduct = productComboBox.getValue();
            int quantity;
            try {
                quantity = Integer.parseInt(quantityField.getText());
                addToCart(selectedProduct, quantity);
            } catch (NumberFormatException ex) {
                System.err.println("Invalid quantity entered: " + quantityField.getText());
            }
        });

        HBox productSelectionBox = new HBox(10, productComboBox, quantityField, addToCartButton);

        cartTable = new TableView<>();
        cartData = FXCollections.observableArrayList();
        cartTable.setItems(cartData);

        TableColumn<OrderItem, String> productNameColumn = new TableColumn<>("Product"); //name not working
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("product"));

        TableColumn<OrderItem, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        cartTable.getColumns().addAll(productNameColumn, quantityColumn);

        Button completeSaleButton = new Button("Complete Sale");
        completeSaleButton.setOnAction(e -> {

            String orderNumber = generateOrderNumber();

            double totalAmount = 0;
            for (OrderItem item : cartData) {
                totalAmount += item.getProduct().getPrice() * item.getQuantity();
            }
            Customer customer = new Customer("default_customer", "password"); // Default customer
            Order order = new Order(customer, new ArrayList<>(cartData), totalAmount);

            for (OrderItem item : cartData) {
                Product product = item.getProduct();
                int newStock = product.getStockQuantity() - item.getQuantity();
                if (newStock >= 0) {
                    product.setStockQuantity(newStock);
                } else {

                    System.err.println("Insufficient stock for " + product.getName());
                    return;
                }
            }

            DataStorage.getOrders().add(order);

            String orderSummary = new Salesperson("temp_salesperson", "password").getOrderSummary(order);
            System.out.println("Order Number: " + orderNumber);
            System.out.println(orderSummary);

            cartData.clear();
            cartTable.refresh();
        });

        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> {
            try {
                Scene loginScene = new Scene(new App().createLoginScene(primaryStage), 300, 200);
                primaryStage.setScene(loginScene);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        VBox root = new VBox(10,
                new Label("Salesperson POS"),
                productSelectionBox,
                cartTable,
                completeSaleButton,
                logoutButton); // Added logoutButton here
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        return root;
    }

    private void addToCart(Product product, int quantity) {
        for (OrderItem item : cartData) {
            if (item.getProduct().getName().equals(product.getName())) {
                item.setQuantity(item.getQuantity() + quantity);
                cartTable.refresh();
                return;
            }
        }

        cartData.add(new OrderItem(product, quantity));
    }

    private String generateOrderNumber() {
        return "ORDER-" + System.currentTimeMillis() / 10000;
    }
}
