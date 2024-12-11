/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.util.ArrayList;

public class Admin extends User {

    public Admin(String username, String password) {
        super(username, password, "admin");
    }

    public void addProduct(Product product) {
        DataStorage.addProduct(product);
    }

    public void removeProduct(String productName) {
        ArrayList<Product> products = DataStorage.getProducts();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getName().equalsIgnoreCase(productName)) {
                products.remove(i);
                break;
            }
        }
    }

    public void updateProductName(String oldName, String newName) {
        for (Product product : DataStorage.getProducts()) {
            if (product.getName().equalsIgnoreCase(oldName)) {
                product.setName(newName);
                break;
            }
        }
    }

    public void updateProductDescription(String productName, String newDescription) {
        for (Product product : DataStorage.getProducts()) {
            if (product.getName().equalsIgnoreCase(productName)) {
                product.setDescription(newDescription);
                break;
            }
        }
    }

    public void updateProductPrice(String productName, double newPrice) {
        for (Product product : DataStorage.getProducts()) {
            if (product.getName().equalsIgnoreCase(productName)) {
                product.setPrice(newPrice);
                break;
            }
        }
    }

    public void updateProductStock(String productName, int newStock) {
        DataStorage.updateStock(productName, newStock);
    }

}
