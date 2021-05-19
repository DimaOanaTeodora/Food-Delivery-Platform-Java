package com.company.menu;

import com.company.product.Drink;
import com.company.product.RFood;
import com.company.product.Sweet;

import java.util.*;

public class RMenu extends Menu {
    private List<Sweet> sweets;
    private List<RFood> rfoods;

    public RMenu(){
        this.rfoods=new ArrayList<RFood>();
        this.sweets=new ArrayList<Sweet>();
    }
    public RMenu(String name, List<Drink> drinks, List<Sweet> sweets, List<RFood> rfoods) {
        super(name, drinks);
        this.sweets = sweets;
        this.rfoods = rfoods;

        //calcul pret
        double totalPrice=0;
        for(Drink it: drinks)
            totalPrice+=it.getPrice();
        for(Sweet it: sweets)
            totalPrice+=it.getPrice();
        for(RFood it: rfoods)
            totalPrice+=it.getPrice();
        this.price=totalPrice;
    }
    @Override
    public void reader(){
        Scanner var=new Scanner (System.in);

        System.out.print("Restaurant Menu name:");
        String name=var.nextLine();
        this.name=name;

        System.out.println("->Restaurant Menu list of drinks:");
        System.out.print("How many drinks:");
        int n=var.nextInt();
        for(int i=0;i<n;i++)
        {
            System.out.println("->Introduce drink number "+i+": ");
            Drink drink=new Drink();
            drink.reader();
            this.drinks.add(drink);
        }

        System.out.println("->Restaurant Menu list of cakes:");
        System.out.print("How many cakes:");
        n=var.nextInt();
        for(int i=0;i<n;i++)
        {
            System.out.println("->Introduce cake number "+i+": ");
            Sweet sweet=new Sweet();
            sweet.reader();
            sweets.add(sweet);
        }
        System.out.println("->Restaurant Menu list of meals:");
        System.out.print("How many meals:");
        n=var.nextInt();
        for(int i=0;i<n;i++)
        {
            System.out.println("->Introduce meal number "+i+": ");
            RFood rfood=new RFood();
            rfood.reader();
            rfoods.add(rfood);
        }

        double totalPrice=0;
        for(Drink it: drinks)
            totalPrice+=it.getPrice();
        for(Sweet it: sweets)
            totalPrice+=it.getPrice();
        for(RFood it: rfoods)
            totalPrice+=it.getPrice();
        this.price=totalPrice;
    }

    @Override
    public String toString() {
        String output="----Menu:Restaurant Menu-----\n";
        output+="Name: "+this.name+"\n";
        output+="Menu Price: "+this.price+"lei\n";
        output+="->Desert Options:\n";
        for(Sweet sweet : this.sweets)
            output+=sweet;
        output+="->Restaurant Food Options:\n";
        for(RFood rFood: this.rfoods)
            output+=rFood;
        output+="->Drinks Options:\n";
        for(Drink drink : this.drinks)
            output+=drink;

        return output;
    }

    public List<Sweet> getSweets() {
        return sweets;
    }

    public List<RFood> getRfoods() {
        return rfoods;
    }
}
