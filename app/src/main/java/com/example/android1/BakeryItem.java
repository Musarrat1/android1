package com.example.android1;

public class BakeryItem {
    private String name;
    private double price;
    private int imageResId;

    // Constructor
    public BakeryItem(String name, double price, int imageResId) {
        this.name = name;
        this.price = price;
        this.imageResId = imageResId;
    }

    // Getters
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getImageResId() {
        return imageResId;
    }
}
