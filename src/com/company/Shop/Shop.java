package com.company.Shop;

import com.company.Menu.Menu;
import com.company.Product.Product;
import com.company.User.DeliveryBoy;
import com.company.User.Owner;

import java.util.*;

public abstract class Shop {

    protected String name;
    protected Owner owner;
    protected List<DeliveryBoy> deliveryBoys;
    protected double rating;
    HashMap<String, Integer>stock;//String = nume produs/meniu

    public Shop(){
        this.deliveryBoys=new ArrayList<DeliveryBoy>();
        this.stock =new HashMap<String, Integer>();
    }

    public Shop(String name, Owner owner, List<DeliveryBoy> deliveryBoys, HashMap<String, Integer>stock) {
        this.name = name;
        this.owner = owner;
        this.deliveryBoys = deliveryBoys;
        this.rating =0;
        this.stock = stock;
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

    public void updateStock(String productName, int stock){
        this.stock.put(productName, stock);
    }

    public void removeProductFromStock(String productName) {
        Iterator it = this.stock.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            if(((String)pair.getKey()).equalsIgnoreCase(productName)){
                this.stock.remove(productName);
                break;
            }
        }

    }
    public void StockLower(String name){
        stock.replace(name, stock.get(name)-1);
    }

    public void setRating(int points) {
        this.rating = (this.rating+points)/2;
    }

}
