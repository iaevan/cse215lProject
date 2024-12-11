/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.mavenproject1;

import java.util.ArrayList;
import java.time.LocalDateTime; 

public class Order {
    private Customer customer;
    private ArrayList<OrderItem> orderItems;
    private double totalAmount;
    private LocalDateTime orderDate; 

    public Order(Customer customer, ArrayList<OrderItem> orderItems, double totalAmount) {
        this.customer = customer;
        this.orderItems = orderItems;
        this.totalAmount = totalAmount;
        this.orderDate = LocalDateTime.now(); 
    }

    // Getters
    public Customer getCustomer() {
        return customer;
    }

    public ArrayList<OrderItem> getOrderItems() {
        return orderItems;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }
}