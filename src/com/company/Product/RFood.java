package com.company.Product;

import java.util.Scanner;

public class RFood extends Product{

    private String ingredients;

    public RFood(){}
    public RFood(String name, double price, String measurement, int quantity,  String ingredients) {
        super(name, price, measurement, quantity);
        this.ingredients = ingredients;
    }
    @Override
    public void reader(){
        Scanner var=new Scanner (System.in);

        System.out.print("Meal's name:");
        String name=var.nextLine();

        System.out.print("Meal's measurement:");
        String measurement=var.nextLine();

        System.out.print("Meal's ingredients:");
        String ingredients=var.nextLine();

        System.out.print("Meal's quantity:");
        int quantity=var.nextInt();

        System.out.print("Meal's price:");
        Double price=var.nextDouble();

        this.name=name;
        this.price=price;
        this.measurement=measurement;
        this.quantity=quantity;
        this.ingredients=ingredients;


    }

    @Override
    public String toString() {
        return "RFood{" +
                "ingredients='" + ingredients + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", measurement='" + measurement + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
