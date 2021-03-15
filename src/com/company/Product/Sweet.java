package com.company.Product;

import java.util.Scanner;

public class Sweet extends Product {
    private int calories;

    public Sweet(){}
    public Sweet(String name, double price, String measurement, int quantity, int calories) {
        super(name, price, measurement, quantity);
        this.calories = calories;
    }
    @Override
    public void reader(){
        Scanner var=new Scanner (System.in);

        System.out.print("Cake's name:");
        String name=var.nextLine();
        System.out.print("Cake's measurement:");
        String measurement=var.nextLine();
        System.out.print("Cake's quantity:");
        int quantity=var.nextInt();
        System.out.print("Cake's calories:");
        int calories=var.nextInt();
        System.out.print("Cake's price:");
        Double price=var.nextDouble();

        this.name=name;
        this.price=price;
        this.measurement=measurement;
        this.quantity=quantity;
        this.calories=calories;

    }

    @Override
    public String toString() {
        return "Sweet{" +
                "calories=" + calories +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", measurement='" + measurement + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
