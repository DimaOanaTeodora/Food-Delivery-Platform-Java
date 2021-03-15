package com.company.Shop;

import com.company.Menu.Menu;
import com.company.Product.Product;
import com.company.User.DeliveryBoy;
import com.company.User.Owner;

import java.util.*;

public abstract class Shop implements Comparable<Shop> {

    protected String name;
    protected Owner owner;
    protected List<DeliveryBoy> deliveryBoys;
    protected double rating;

    public Shop(){this.deliveryBoys=new ArrayList<DeliveryBoy>();}

    public Shop(String name, Owner owner, List<DeliveryBoy> deliveryBoys) {
        this.name = name;
        this.owner = owner;
        this.deliveryBoys = deliveryBoys;
        this.rating =5;
        this.deliveryBoys=new ArrayList<DeliveryBoy>();
    }
    public void addRating(){
        Scanner var=new Scanner(System.in);

        System.out.println("Do you want to rate "+this.name+"?(yes/no)");
        String response=var.nextLine();
        if(response.equalsIgnoreCase("yes"))
        {
            System.out.print("Give us x points out of 5:");
            int points=var.nextInt();
            this.rating=(this.rating+points)/2;
            System.out.println("Thank you :)");
        }

    }

    public String getName() {
        return this.name;
    }

    public List<DeliveryBoy> getDeliveryBoys() {
        return deliveryBoys;
    }

    public abstract void reader();

    @Override
    public abstract String toString();

    public abstract List<Menu>getMenus();
    public abstract List<Product> getProducts();

    public double getRating() {
        return rating;
    }
    @Override
    public int compareTo(Shop shop){
        return (int)(this.getRating()- shop.getRating());
    }
    public void updateDeliveryBoy(DeliveryBoy d){
        int poz=0;
        for(int i=0;i<deliveryBoys.size();i++)
            if(deliveryBoys.get(i).equals(d))
            {
                poz=i;
                break;
            }
        Scanner var=new Scanner(System.in);
        System.out.print("Do you want to change his car number?(yes/no):");
        String answer=var.nextLine();
        if(answer.equalsIgnoreCase("yes")){
            System.out.print("Give the new car number:");
            String carNumber=var.nextLine();
            d.setCarNumber(carNumber);
        }
        System.out.print("Do you want to change his phone number?(yes/no):");
        answer=var.nextLine();
        if(answer.equalsIgnoreCase("yes")){
            System.out.print("Give the new phone number:");
            String phoneNumber=var.nextLine();
            d.setPhoneNumber(phoneNumber);
        }
        deliveryBoys.set(poz, d);

    }

}
