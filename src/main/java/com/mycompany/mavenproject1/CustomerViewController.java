/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Collections;

public class CustomerViewController {

    public Pane createView(Stage primaryStage) {

        TilePane productGrid = new TilePane();
        productGrid.setHgap(10);
        productGrid.setVgap(10);
        productGrid.setPadding(new Insets(10));
        productGrid.setPrefColumns(4); // Adjust as needed

        for (Product product : DataStorage.getProducts()) {
            VBox productBox = createProductBox(product, primaryStage);
            productGrid.getChildren().add(productBox);
        }

        ScrollPane scrollPane = new ScrollPane(productGrid);

        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> {
            try {
                Scene loginScene = new Scene(new App().createLoginScene(primaryStage), 300, 200);
                primaryStage.setScene(loginScene);
            } catch (Exception ex) {
                ex.printStackTrace();//wdit mean?
            }
        });

        VBox root = new VBox(10, new Label("Customer View"), scrollPane, logoutButton); // Added logoutButton here
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        return root;
    }

    private VBox createProductBox(Product product, Stage primaryStage) {
        Label nameLabel = new Label(product.getName());
        Label priceLabel = new Label("$" + product.getPrice());

        TextField quantityField = new TextField();
        quantityField.setPromptText("Quantity");

        Button orderButton = new Button("Place Order");
        orderButton.setOnAction(e -> {
            int quantity;
            try {
                quantity = Integer.parseInt(quantityField.getText());
                placeOrder(product, quantity, primaryStage);
            } catch (NumberFormatException ex) {

                System.err.println("Invalid quantity entered: " + quantityField.getText());
            }
        });

        VBox productBox = new VBox(10, nameLabel, priceLabel, quantityField, orderButton);
        productBox.setAlignment(Pos.CENTER);
        productBox.setPadding(new Insets(10));
        productBox.setStyle("-fx-border-color: lightgray; -fx-border-width: 1;");

        return productBox;
    }

    private void placeOrder(Product product, int quantity, Stage primaryStage) {
        OrderItem orderItem = new OrderItem(product, quantity);

        Customer customer = new Customer("default_customer", "password");
        double totalAmount = product.getPrice() * quantity;
        Order order = new Order(customer, new ArrayList<>(Collections.singletonList(orderItem)), totalAmount);

        int newStock = product.getStockQuantity() - quantity;
        if (newStock >= 0) {
            product.setStockQuantity(newStock);

            DataStorage.getOrders().add(order);

            Label confirmationLabel = new Label("Order placed successfully!");
            Label productLabel = new Label("Product: " + product.getName() + " x " + quantity);
            Label totalLabel = new Label("Total: $" + (product.getPrice() * quantity));

            Button logoutButton = new Button("Logout");
            logoutButton.setOnAction(e -> {
                try {
                    Scene loginScene = new Scene(new App().createLoginScene(primaryStage), 300, 200);
                    primaryStage.setScene(loginScene);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });

            VBox confirmationBox = new VBox(10, confirmationLabel, productLabel, totalLabel, logoutButton);
            Scene confirmationScene = new Scene(confirmationBox, 300, 200);
            primaryStage.setScene(confirmationScene);
        }
        System.err.println("Insufficient stock for " + product.getName());
    }
}
