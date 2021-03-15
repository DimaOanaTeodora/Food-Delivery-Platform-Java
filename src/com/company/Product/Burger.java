package com.company.Product;

import java.util.Scanner;

public class Burger extends Product {
    private boolean isVegan;
    private String ingredients;

    public Burger(){}
    public Burger(String name, double price, String measurement, int quantity, boolean isVegan, String ingredients) {
        super(name, price, measurement, quantity);
        this.isVegan = isVegan;
        this.ingredients = ingredients;
    }
    @Override
    public void reader(){
        Scanner var=new Scanner (System.in);

        System.out.print("Burger's name:");
        String name=var.nextLine();

        System.out.print("Burger's measurement:");
        String measurement=var.nextLine();

        System.out.print("Burger's ingredients:");
        String ingredients=var.nextLine();

        System.out.print("The burger is vegan(true/false):");
        boolean isVegan=var.nextBoolean();

        System.out.print("Burger's quantity:");
        int quantity=var.nextInt();

        System.out.print("Burger's price:");
        Double price=var.nextDouble();

        this.name=name;
        this.price=price;
        this.measurement=measurement;
        this.quantity=quantity;
        this.isVegan=isVegan;
        this.ingredients=ingredients;

    }

    @Override
    public String toString() {
        return "Burger{" +
                "isVegan=" + isVegan +
                ", ingredients='" + ingredients + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", measurement='" + measurement + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
