/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.util.ArrayList;

public class Product {

    private String name;
    private String description;
    private double price;
    private int stockQuantity;

    public Product(String name, String description, double price, int stockQuantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public static ArrayList<Product> getPredefinedProducts() {
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("Sticky Cartoon Tabs (SCT)", "Colorful and fun sticky tabs", 120, 29));
        products.add(new Product("Space Notebooks", "Notebooks with space-themed designs", 799, 16));
        products.add(new Product("Vintage Notebook (VNB)", "Classic notebook with a vintage look", 299, 21));
        products.add(new Product("3D Sticker Set with Tweezers", "Set of 3D stickers with tweezers for easy application", 640, 9));
        products.add(new Product("Erasable Gel Pens", "Smooth-writing gel pens with erasable ink", 79, 35));
        products.add(new Product("Transparent Sticky Notes (TSN)", "See-through sticky notes for highlighting", 100, 195));
        products.add(new Product("Washi Tape (WAT)", "Decorative tape with various patterns", 499, 16));
        products.add(new Product("Scrapbooking Set (SBS)", "Complete set for scrapbooking", 499, 28));
        products.add(new Product("Sticky Highlighter Strips (SHS)", "Thin sticky strips for highlighting text", 149, 37));
        products.add(new Product("Metal Bookmarks (MBM)", "Durable and stylish metal bookmarks", 199, 3));
        products.add(new Product("Mini Poem Books (MPB)", "Collection of poems in a small book format", 599, 1));
        products.add(new Product("Mini Note Books (MNB)", "Pocket-sized notebooks for quick notes", 15, 30));
        return products;
    }
}
