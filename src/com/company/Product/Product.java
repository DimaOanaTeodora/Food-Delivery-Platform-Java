package com.company.Product;

public abstract class Product {

    protected String name;
    protected double price;
    protected String measurement;
    protected int quantity;
    public Product(){}

    public Product(String name, double price, String measurement, int quantity) {
        this.name = name;
        this.price = price;
        this.measurement = measurement;
        this.quantity = quantity;
    }
    public double getPrice(){return this.price;}

    public abstract void reader();

    @Override
    public abstract String toString();

    public String getName() {
        return name;
    }
}
