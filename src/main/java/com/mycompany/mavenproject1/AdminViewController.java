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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminViewController {

    private TableView<Product> productTable;

    public Pane createView(Stage primaryStage) {
        //Product Table -

        productTable = new TableView<>();
        ObservableList<Product> productData = FXCollections.observableArrayList(DataStorage.getProducts());
        productTable.setItems(productData);

        TableColumn<Product, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Product, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Product, Integer> stockColumn = new TableColumn<>("Stock");
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stockQuantity"));

        productTable.getColumns().addAll(nameColumn, priceColumn, stockColumn);

        Button deleteButton = new Button("Delete Product");
        deleteButton.setOnAction(e -> {
            Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
            if (selectedProduct != null) {
                DataStorage.removeProduct(selectedProduct);
                productData.remove(selectedProduct);
            }
        });

        Button salesReportButton = new Button("View Sales Report");
        salesReportButton.setOnAction(e -> {
            try {
                SalesReportViewController salesReportView = new SalesReportViewController();
                Scene salesReportScene = new Scene(salesReportView.createView(primaryStage), 600, 400);
                primaryStage.setScene(salesReportScene);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
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

        HBox buttonBox = new HBox(10, deleteButton, salesReportButton, logoutButton); // Added logoutButton here
        buttonBox.setAlignment(Pos.CENTER);

        VBox root = new VBox(10, new Label("Admin View"), productTable, buttonBox);
        root.setPadding(new Insets(20));

        return root;
    }
}
