/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.util.ArrayList;

public class Salesperson extends User {

    public Salesperson(String username, String password) { // Constructor with arguments
        super(username, password, "salesperson");
    }

    public Order createSale(Customer customer, ArrayList<OrderItem> orderItems) {
        double totalAmount = 0;
        for (OrderItem item : orderItems) {
            totalAmount += item.getProduct().getPrice() * item.getQuantity();
        }

        Order order = new Order(customer, orderItems, totalAmount);

        for (OrderItem item : orderItems) {
            Product product = item.getProduct();
            int newStock = product.getStockQuantity() - item.getQuantity();
            product.setStockQuantity(newStock); // Assuming sufficient stock
        }

        return order;
    }

    public String getOrderSummary(Order order) {
        StringBuilder summary = new StringBuilder();
        summary.append("Order Summary\n");
        summary.append("Customer: ").append(order.getCustomer().getUsername()).append("\n");
        summary.append("Items:\n");
        for (OrderItem item : order.getOrderItems()) {
            summary.append("  - ").append(item.getProduct().getName())
                    .append(" x ").append(item.getQuantity())
                    .append(" = ").append(item.getProduct().getPrice() * item.getQuantity()).append("\n");
        }
        summary.append("Total Amount: ").append(order.getTotalAmount()).append("\n");
        return summary.toString();
    }

    public int checkStock(String productName) {
        for (Product product : DataStorage.getProducts()) {
            if (product.getName().equalsIgnoreCase(productName)) {
                return product.getStockQuantity();
            }
        }
        return 0;
    }
}
