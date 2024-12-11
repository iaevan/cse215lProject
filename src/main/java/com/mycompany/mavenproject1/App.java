package com.mycompany.mavenproject1;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        Scene loginScene = new Scene(createLoginScene(primaryStage), 300, 200);
        primaryStage.setTitle("Plumbum Stationery BD - Login");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    public Pane createLoginScene(Stage primaryStage) {
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(loginButton, 1, 2);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 3);

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String username = usernameField.getText();
                String password = passwordField.getText();

                User user = DataStorage.getUser(username, password);

                if (user != null) {
                    if (user instanceof Admin) {
                        actiontarget.setFill(Color.GREEN);
                        actiontarget.setText("Login successful! Redirecting to Admin view...");
                        try {
                            AdminViewController adminView = new AdminViewController();
                            Scene adminScene = new Scene(adminView.createView(primaryStage), 600, 400);
                            primaryStage.setScene(adminScene);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    } else if (user instanceof Salesperson) {
                        actiontarget.setFill(Color.GREEN);
                        actiontarget.setText("Login successful! Redirecting to Salesperson view...");
                        try {
                            SalespersonViewController salespersonView = new SalespersonViewController();
                            Scene salespersonScene = new Scene(salespersonView.createView(primaryStage), 600, 400);
                            primaryStage.setScene(salespersonScene);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    } else if (user instanceof Customer) {
                        actiontarget.setFill(Color.GREEN);
                        actiontarget.setText("Login successful! Redirecting to Customer view...");
                        try {
                            CustomerViewController customerView = new CustomerViewController();
                            Scene customerScene = new Scene(customerView.createView(primaryStage), 600, 400);
                            primaryStage.setScene(customerScene);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                } else {
                    actiontarget.setFill(Color.FIREBRICK);
                    actiontarget.setText("Invalid username or password.");
                }
            }
        });

        return grid;
    }

    public static void main(String[] args) {
        launch(args);
    }
}