package com.company.product;

import java.util.Scanner;

public class Burger extends Product {
    private boolean isVegan;
    private String ingredients;

    public Burger(){}
    public Burger(String name, double price, boolean isVegan, String ingredients) {
        super(name, price);
        this.isVegan = isVegan;
        this.ingredients = ingredients;
    }
    @Override
    public void reader(){
        Scanner var=new Scanner (System.in);

        System.out.print("Burger's name:");
        String name=var.nextLine();

        System.out.print("Burger's ingredients:");
        String ingredients=var.nextLine();

        System.out.print("The burger is vegan(true/false):");
        boolean isVegan=var.nextBoolean();

        System.out.print("Burger's price:");
        Double price=var.nextDouble();

        this.name=name;
        this.price=price;
        this.isVegan=isVegan;
        this.ingredients=ingredients;

    }

    @Override
    public String toString() {
        String output="----Product:Burger-----\n";
        output+="Name: "+this.name+"\n";
        output+="Ingredients: "+this.ingredients+"\n";
        output+="Vegan: "+this.isVegan+"\n";
        output+="Price: "+this.price+"lei\n";

        return output;
    }

    public boolean getisVegan() {
        return isVegan;
    }

    public String getIngredients() {
        return ingredients;
    }
}
