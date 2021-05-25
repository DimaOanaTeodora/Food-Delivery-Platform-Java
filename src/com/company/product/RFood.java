package com.company.product;

import java.util.Scanner;

public class RFood extends Product{

    private String ingredients;

    public RFood(){}

    public RFood(String name, double price,  String ingredients) {
        super(name, price);

        this.ingredients = ingredients;
    }

    @Override
    public void reader(){
        Scanner var=new Scanner (System.in);

        System.out.print("Meal's name:");
        String name=var.nextLine();

        System.out.print("Meal's ingredients:");
        String ingredients=var.nextLine();

        System.out.print("Meal's price:");
        Double price=var.nextDouble();

        this.name=name;
        this.price=price;
        this.ingredients=ingredients;
    }

    @Override
    public String toString() {
        String output="----Product:Restaurant Food-----\n";
        output+="Name: "+this.name+"\n";
        output+="Ingredients: "+this.ingredients+"\n";
        output+="Price: "+this.price+"lei\n";

        return output;

    }

    public String getIngredients() {
        return ingredients;
    }
}
