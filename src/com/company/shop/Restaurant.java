package com.company.shop;

import com.company.menu.*;

import com.company.product.*;

import com.company.user.DeliveryBoy;
import com.company.user.Owner;

import java.util.*;

public class Restaurant extends Shop {
    private List<RMenu> rMenus;
    private List<Drink> drinks;

    public Restaurant(){
        this.drinks=new ArrayList<Drink>();
        this.rMenus=new ArrayList<RMenu>();
    }
    public Restaurant(String name, Owner owner, List<DeliveryBoy> deliveryBoys,List<Drink> drinks, List<RMenu> rMenus, HashMap<String, Integer>stock) {
        super(name, owner, deliveryBoys, stock);
        this.rMenus = rMenus;
        this.drinks = drinks;
    }
    @Override
    public void reader(){
        Scanner var=new Scanner(System.in);

        System.out.print("Restaurant name:");
        String name=var.nextLine();
        this.name=name;


        System.out.println("->Restaurant delivery boys:");
        System.out.print("How many delivery boys do you want:");
        int n = var.nextInt();
        for (int i = 0; i < n; i++) {
            DeliveryBoy deliveryboy = new DeliveryBoy();
            deliveryboy.reader();
            deliveryBoys.add(deliveryboy);
        }

        System.out.println("FastFood list of drinks:");
        System.out.print("How many drinks:");
        n = var.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.println("Introduce drink number " + i + ": ");
            Drink drink = new Drink();
            drink.reader();
            this.drinks.add(drink);

            System.out.print("Introduce the stock of "+drink.getName()+" :");
            int quantity=var.nextInt();
            stock.put(drink.getName(), quantity);
        }

        System.out.println("->Restaurant list of meals:");
        System.out.print("How many meals:");
        n=var.nextInt();
        for(int i=0;i<n;i++)
        {
            System.out.println("->Introduce meal number "+i+": ");
            RMenu rMenu=new RMenu();
            rMenu.reader();
            rMenus.add(rMenu);

            System.out.print("Introduce the stock of "+rMenu.getName()+" :");
            int quantity=var.nextInt();
            stock.put(rMenu.getName(), quantity);
        }
        Owner owner=new Owner();
        owner.reader(this);
        this.owner=owner;

    }

    @Override
    public String toString() {
        String output="------Restaurant----------\n";
        output+="Name: "+ this.name+"\n";
        output+= this.owner+"\n";
        output+="Restaurant rating: "+ this.rating+"\n";
        output+="->List of Deliveryboys:\n ";
        for (DeliveryBoy db: this.deliveryBoys){
            output+= db +"\n";
        }
        output+="->List of Drinks: \n";
        for (Drink drink: this.drinks){
            output+= drink + " \nStock:" + stock.get(drink.getName()) +"\n";
        }
        output+="->List of rMenus: \n";
        for (RMenu rMenu: rMenus){
            output+= rMenu + " \nStock:" + stock.get(rMenu.getName()) +"\n";
        }

        return output;
    }

    @Override
    public List<Menu> getMenus() {
        List<Menu> l = null;
        for(RMenu it: rMenus) {
            l.add(it);
        }
        return l;
    }

    @Override
    public List<Product> getProducts() {
        List<Product> l = new ArrayList<Product>();
        for (Drink it : drinks) {
            l.add(it);
        }

        return l;
    }
    public void addrMenu(RMenu menu){
        rMenus.add(menu);
    }
    public void addDrink(Drink drink) {
        drinks.add(drink);
    }
    public void removeDrink(Drink drink){
        for(Drink it: drinks) {
            if (it.equals(drink)) {
                drinks.remove(drink);
                break;
            }
        }
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public RMenu orderRMenu(RMenu chooseFrom){
        Scanner var=new Scanner(System.in);
        List <Sweet> S=new ArrayList<Sweet>();
        int i=0;
        System.out.println("->List of sweets to choose:");
        for(Sweet sweet: chooseFrom.getSweets() ) {
            System.out.println("Sweet number "+ i+ ":\n");
            i++;
            System.out.println(sweet);
        }
        System.out.print("->Choose a sweet number:");
        int choose=var.nextInt();
        S.add(chooseFrom.getSweets().get(choose));

        List <RFood> R=new ArrayList<RFood>();
        i=0;
        System.out.println("->List of meals to choose:");
        for(RFood rFood: chooseFrom.getRfoods() ) {
            System.out.println("Meal number "+ i+ ":\n");
            i++;
            System.out.println(rFood);
        }
        System.out.print("->Choose a meal number:");
        choose=var.nextInt();
        R.add(chooseFrom.getRfoods().get(choose));

        List <Drink> D=new ArrayList<Drink>();
        i=0;
        System.out.println("->List of drinks to choose:");
        for(Drink drink: chooseFrom.getDrinks()) {
            System.out.println("Drink number "+ i+ ":\n");
            i++;
            System.out.println(drink);
        }
        System.out.print("->Choose a drink number:");
        choose=var.nextInt();
        D.add((chooseFrom.getDrinks()).get(choose));

        RMenu rMenu=new RMenu(chooseFrom.getName(),D,S,R);
        return rMenu;

    }
}
