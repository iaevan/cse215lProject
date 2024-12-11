/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class SalesReportViewController {

    public Pane createView(Stage primaryStage) {

        TableView<Order> salesTable = new TableView<>();
        ObservableList<Order> salesData = FXCollections.observableArrayList(DataStorage.getOrders()); // You'll need to implement getOrders() in DataStorage
        salesTable.setItems(salesData);

        TableColumn<Order, String> customerColumn = new TableColumn<>("Customer");
        customerColumn.setCellValueFactory(new PropertyValueFactory<>("customer")); // Assuming you have a getCustomer() method in Order

        TableColumn<Order, Double> totalAmountColumn = new TableColumn<>("Total Amount");
        totalAmountColumn.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));

        TableColumn<Order, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("orderDate"));

        salesTable.getColumns().addAll(customerColumn, totalAmountColumn, dateColumn);

        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> {
            try {
                Scene loginScene = new Scene(new App().createLoginScene(primaryStage), 300, 200);
                primaryStage.setScene(loginScene);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        VBox root = new VBox(10, new Label("Sales Report"), salesTable, logoutButton); // Add logoutButton to the VBox
        root.setPadding(new Insets(20));

        return root;
    }
}
