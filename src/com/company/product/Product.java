package com.company.product;

public abstract class Product {

    protected String name;
    protected double price;
    public Product(){}

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public double getPrice(){return this.price;}

    public abstract void reader();

    @Override
    public abstract String toString();

    public String getName() {
        return name;
    }
}
