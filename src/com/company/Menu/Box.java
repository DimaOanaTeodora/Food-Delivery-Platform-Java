package com.company.Menu;

import com.company.Product.Burger;
import com.company.Product.Drink;


import java.util.*;

public class Box extends Menu{

    private Burger burger;
    private String fries; //small, medium, big and they are cost free

    public Box(){}
    public Box(String name, List<Drink> drinks, Burger burger, String fries) {
        super(name, drinks);
        this.burger = burger;
        this.fries = fries;

        //calcul pret
        double totalPrice=0;
        for(Drink it: drinks)
            totalPrice+=it.getPrice();

        totalPrice+=burger.getPrice();
        this.price=totalPrice;

    }

    @Override
    public void reader() {
        Scanner var=new Scanner (System.in);

        System.out.print("Box's name:");
        String name=var.nextLine();
        this.name=name;

        System.out.print("Box's fries(small/medium/big):");
        String fries=var.nextLine();
        this.fries=fries;

        System.out.println("->Box's list of drinks:");
        System.out.print("How many drinks:");
        int n=var.nextInt();
        for(int i=0;i<n;i++)
        {
            System.out.println("->Introduce drink number "+i+": ");
            Drink drink=new Drink();
            drink.reader();
            this.drinks.add(drink);
        }

        System.out.println("Burger:");
        Burger burger=new Burger();
        burger.reader();
        this.burger=burger;


        double totalPrice=0;
        for(Drink it: drinks)
            totalPrice+=it.getPrice();
        totalPrice+=burger.getPrice();
        this.price=totalPrice;


    }

    @Override
    public String toString() {
        String output="----Menu: Fast Food Box----\n";
        output+="Name: "+this.name+"\n";
        output+="Menu Price: "+this.price+"lei\n";
        output+=this.burger;
        output+="Fries: "+this.fries+"\n";
        output+="->Drinks Options:\n";
        for(Drink drink : this.drinks)
            output+=drink;

        return output;

    }

}
