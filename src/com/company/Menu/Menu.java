package com.company.Menu;
import com.company.Product.Drink;

import java.util.*;
public abstract class Menu {
    //cand adaug meniurile vreau sa se adauge pe stock in plus
    protected String name;
    protected List<Drink> drinks;
    protected double price;

    public Menu(){this.drinks=new ArrayList<Drink>();}
    public Menu(String name, List<Drink> drinks) {
        this.name=name;
        this.drinks = drinks;
       //pretul este calculat in functie de produse
    }
    public abstract void reader();

    @Override
    public abstract String toString();

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }
}
