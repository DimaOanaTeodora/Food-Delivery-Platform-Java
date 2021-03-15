package com.company.Shop;

import com.company.Menu.Menu;
import com.company.Product.Product;
import com.company.Product.Sweet;
import com.company.User.DeliveryBoy;
import com.company.User.Owner;

import java.util.*;

public class CakeShop extends Shop{
    private List<Sweet> sweets;

    public CakeShop(){this.sweets=new ArrayList<Sweet>();}
    public CakeShop(String name, Owner owner, List<DeliveryBoy> deliveryBoys,  List<Sweet> sweets) {
        super(name, owner, deliveryBoys);
        this.sweets = sweets;
        this.sweets=new ArrayList<Sweet>();
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
        }

        Owner owner=new Owner();
        owner.reader(this);
        this.owner=owner;

    }

    @Override
    public String toString() {
        return "CakeShop{" +
                "sweets=" + sweets +
                ", name='" + name + '\'' +
                ", owner=" + owner +
                ", deliveryBoys=" + deliveryBoys +
                ", rating=" + rating +
                '}';
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
        if (this == o) return true;
        if (!(o instanceof CakeShop)) return false;
        CakeShop cakeShop = (CakeShop) o;
        return Objects.equals(sweets, cakeShop.sweets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sweets);
    }
}
