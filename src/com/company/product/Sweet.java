package com.company.product;

import java.util.Scanner;

public class Sweet extends Product {
    private int calories;

    public Sweet(){}
    public Sweet(String name, double price,  int calories) {
        super(name, price);
        this.calories = calories;
    }
    @Override
    public void reader(){
        Scanner var=new Scanner (System.in);

        System.out.print("Cake's name:");
        String name=var.nextLine();

        System.out.print("Cake's calories:");
        int calories=var.nextInt();

        System.out.print("Cake's price:");
        Double price=var.nextDouble();

        this.name=name;
        this.price=price;
        this.calories=calories;

    }

    @Override
    public String toString() {
        String output="----Product:Sweet-----\n";
        output+="Name: "+this.name+"\n";
        output+="Calories: "+this.calories+"kcal\n";
        output+="Price: "+this.price+"lei\n";

        return output;
    }
}
