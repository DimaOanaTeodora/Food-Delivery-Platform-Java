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
        return "Box{" +
                "burger=" + burger +
                ", fries='" + fries + '\'' +
                ", name='" + name + '\'' +
                ", drinks=" + drinks +
                ", price=" + price +
                '}';
    }
}
