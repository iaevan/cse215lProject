/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.util.ArrayList;

public class Customer extends User {

    private ArrayList<OrderItem> cart;

    public Customer(String username, String password) {
        super(username, password, "customer");
        this.cart = new ArrayList<>();
    }

    public void addToCart(Product product, int quantity) {
        for (OrderItem item : cart) {
            if (item.getProduct().getName().equalsIgnoreCase(product.getName())) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        cart.add(new OrderItem(product, quantity));
    }

    public void removeFromCart(String productName) {
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getProduct().getName().equalsIgnoreCase(productName)) {
                cart.remove(i);
                break;
            }
        }
    }

    public void updateCartItemQuantity(String productName, int newQuantity) {
        for (OrderItem item : cart) {
            if (item.getProduct().getName().equalsIgnoreCase(productName)) {
                item.setQuantity(newQuantity);
                break; //all prod unik
            }
        }
    }

    public void clearCart() {
        cart.clear();
    }

    public ArrayList<OrderItem> getCart() {
        return cart;
    }

    public Order placeOrder() {

        double totalAmount = 0;
        for (OrderItem item : cart) {
            totalAmount += item.getProduct().getPrice() * item.getQuantity();
        }
        Order order = new Order(this, cart, totalAmount);

        clearCart();

        return order;
    }
}
