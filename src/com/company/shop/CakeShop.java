package com.company.shop;

import com.company.menu.Menu;

import com.company.product.*;

import com.company.user.DeliveryBoy;
import com.company.user.Owner;

import java.util.*;

public class CakeShop extends Shop{
    private List<Sweet> sweets;

    public CakeShop(){
        this.sweets=new ArrayList<Sweet>();
    }

    public CakeShop(String name, Owner owner, List<DeliveryBoy> deliveryBoys,  List<Sweet> sweets, HashMap<String, Integer>stock) {
        super(name, owner, deliveryBoys, stock);
        this.sweets = sweets;
    }

    @Override
    public void reader(){
        Scanner var=new Scanner(System.in);

        System.out.print("CakeShop's name:");
        String name=var.nextLine();
        this.name=name;

        System.out.println("->CakeShop's delivery boys:");
        System.out.print("How many delivery boys do you want:");
        int n=var.nextInt();

        for(int i=0;i<n;i++){
            DeliveryBoy deliveryboy=new DeliveryBoy();
            deliveryboy.reader();
            deliveryBoys.add(deliveryboy);
        }

        System.out.println("->CakeShop's Menu:");
        System.out.print("How many cakes do you want:");
        n=var.nextInt();

        for(int i=0;i<n;i++){
            Sweet sweet=new Sweet();
            sweet.reader();
            sweets.add(sweet);

            System.out.print("Introduce the stock of "+sweet.getName()+" :");
            int quantity=var.nextInt();
            stock.put(sweet.getName(), quantity);
        }

        Owner owner=new Owner();
        owner.reader(this);
        this.owner=owner;
    }

    @Override
    public String toString() {
        String output="------CakeShop----------\n";
        output+="Name: "+ this.name+"\n";
        output+=this.owner+"\n";
        output+="->List of Deliveryboys:\n ";

        for (DeliveryBoy db: this.deliveryBoys){
            output+= db +"\n";
        }

        output+="Restaurant rating: "+ this.rating+"\n";
        output+="->List of Sweets: \n";

        for (Sweet sweet: this.sweets){
            output+= sweet + "Stock:" + stock.get(sweet.getName()) +"\n";
        }
        return output;
    }

    @Override
    public List<Menu> getMenus() {
        return null;
    }

    @Override
    public List<Product> getProducts() {
        List<Product> l = null;

        for(Sweet it: sweets) {
            l.add(it);
        }
        return l;
    }

    public void addSweet(Sweet sweet){
        sweets.add(sweet);
    }

    public void removeSweet(Sweet sweet){
        for(Sweet it: sweets) {
            if (it.equals(sweet)) {
                sweets.remove(sweet);
                break;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof CakeShop))
            return false;
        CakeShop cakeShop = (CakeShop) o;
        return Objects.equals(sweets, cakeShop.sweets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sweets);
    }

    public List<Sweet> getSweets() {
        return sweets;
    }

}
