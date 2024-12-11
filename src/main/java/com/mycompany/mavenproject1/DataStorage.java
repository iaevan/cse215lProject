package com.mycompany.mavenproject1;

import java.util.ArrayList;

public class DataStorage {

    private static ArrayList<Order> orders;
    private static ArrayList<Product> products;
    private static ArrayList<User> users;

    private DataStorage() {
    }

    public static ArrayList<Product> getProducts() {
        if (products == null) {
            products = Product.getPredefinedProducts();
        }
        return products;
    }

    public static void addProduct(Product product) {
        products.add(product);
    }

    public static void removeProduct(Product product) {
        products.remove(product);
    }

    public static void updateStock(String productName, int newStock) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(productName)) {
                product.setStockQuantity(newStock);
                break;
            }
        }
    }

    public static ArrayList<User> getUsers() {
        if (users == null) {
            users = new ArrayList<>();

            users.add(new Admin("admin", "12345"));
            users.add(new Salesperson("sale", "12345"));
            users.add(new Customer("customer", "12345"));
        }
        return users;
    }

    public static User getUser(String username, String password) {
        getUsers();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public static ArrayList<Order> getOrders() {
        if (orders == null) {
            orders = new ArrayList<>();
        }
        return orders;
    }

}
