package com.company;

import com.company.Menu.Box;
import com.company.Menu.RMenu;
import com.company.Product.Burger;
import com.company.Product.Drink;
import com.company.Product.RFood;
import com.company.Product.Sweet;
import com.company.Shop.CakeShop;
import com.company.Shop.FastFood;
import com.company.Shop.Restaurant;
import com.company.Shop.Shop;
import com.company.User.DeliveryBoy;
import com.company.User.Owner;
import com.company.User.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Reading {//singleton

    private static Reading single_instance = null;
    private HashMap<Integer, Shop> shops;
    private int shopId;
    private Set<User> usersReg;


    private Reading() {
        this.usersReg=new HashSet<User>();
        this.shops=new HashMap<Integer, Shop>();


        //apelare metoda de citire a userilor
        ReadUsers();
        //citire CakeShops

        try {
             String path="./Proiect PAO/src/com/company/CakeShop.csv";
            BufferedReader buffer = new BufferedReader
                    (new FileReader(path ));

            String line = buffer.readLine();
            while (line != null) {
                String [] array=line.split(",");

                int k=0;
                Owner owner= new Owner(array[k++],array[k++],array[k++]);
                int n=Integer.parseInt(array[k++]);
                List<DeliveryBoy> deliveryBoys=new ArrayList<DeliveryBoy>();
                for(int i=0;i<n;i++) {
                    DeliveryBoy d = new DeliveryBoy(array[k++],array[k++],array[k++],array[k++]);
                    deliveryBoys.add(d);
                }
                String name=array[k++];
                n=Integer.parseInt(array[k++]);
                List<Sweet> sweets=new ArrayList<Sweet>();
                HashMap<String, Integer>stock=new HashMap<String, Integer>();
                for(int i=0;i<n;i++) {
                    Sweet s = new Sweet(array[k++], Double.parseDouble(array[k++]), Integer.parseInt(array[k++]));
                    sweets.add(s);
                    stock.put(s.getName(), Integer.parseInt(array[k++]));

                }
                CakeShop shop=new CakeShop(name,owner,deliveryBoys,sweets,stock);
                shopId+=1;
                shops.put(shopId, shop);

                line = buffer.readLine();
            }

        }
        catch (IOException e) {

            e.printStackTrace();
        }
        //citire FastFood
        try {
             String path="./Proiect PAO/src/com/company/FastFood.csv";
            BufferedReader buffer = new BufferedReader
                    (new FileReader(path ));

            String line = buffer.readLine();
            while (line != null) {
                String [] array=line.split(",");

                int k=0;
                Owner owner= new Owner(array[k++],array[k++],array[k++]);
                int n=Integer.parseInt(array[k++]);
                List<DeliveryBoy> deliveryBoys=new ArrayList<DeliveryBoy>();
                for(int i=0;i<n;i++) {
                    DeliveryBoy d = new DeliveryBoy(array[k++],array[k++],array[k++],array[k++]);
                    deliveryBoys.add(d);
                }

                String name=array[k++];
                n=Integer.parseInt(array[k++]);

                List<Burger> burgers=new ArrayList<Burger>();
                HashMap<String, Integer>stock=new HashMap<String, Integer>();
                for(int i=0;i<n;i++) {
                    Burger b = new Burger(array[k++], Double.parseDouble(array[k++]), Boolean.parseBoolean(array[k++]), array[k++]);
                    burgers.add(b);
                    stock.put(b.getName(), Integer.parseInt(array[k++]));

                }
                n=Integer.parseInt(array[k++]);

                List<Drink> drinks=new ArrayList<Drink>();
                for(int i=0;i<n;i++) {
                    Drink d = new Drink(array[k++], Double.parseDouble(array[k++]), array[k++]);
                    drinks.add(d);
                    stock.put(d.getName(), Integer.parseInt(array[k++]));

                }
                n=Integer.parseInt(array[k++]);

                List<Box> boxes=new ArrayList<Box>();
                for(int i=0;i<n;i++) {
                    Burger burger=new Burger(array[k++], Double.parseDouble(array[k++]), Boolean.parseBoolean(array[k++]), array[k++]);
                    Box b = new Box(array[k++],drinks,burger,array[k++]);
                    boxes.add(b);
                    stock.put(b.getName(), Integer.parseInt(array[k++]));

                }
                FastFood shop=new FastFood(name,owner,deliveryBoys,boxes,drinks, burgers,stock);
                shopId+=1;
                shops.put(shopId, shop);

                line = buffer.readLine();
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        //citire Restaurant
        try {
             String path="./Proiect PAO/src/com/company/Restaurant.csv";
            BufferedReader buffer = new BufferedReader
                    (new FileReader(path ));

            String line = buffer.readLine();
            while (line != null) {
                String [] array=line.split(",");

                int k=0;
                Owner owner= new Owner(array[k++],array[k++],array[k++]);
                int n=Integer.parseInt(array[k++]);
                List<DeliveryBoy> deliveryBoys=new ArrayList<DeliveryBoy>();
                for(int i=0;i<n;i++) {
                    DeliveryBoy d = new DeliveryBoy(array[k++],array[k++],array[k++],array[k++]);
                    deliveryBoys.add(d);
                }

                String name=array[k++];
                n=Integer.parseInt(array[k++]);

                HashMap<String, Integer>stock=new HashMap<String, Integer>();
                List<Drink> drinks=new ArrayList<Drink>();
                for(int i=0;i<n;i++) {
                    Drink d = new Drink(array[k++], Double.parseDouble(array[k++]), array[k++]);
                    drinks.add(d);
                    stock.put(d.getName(), Integer.parseInt(array[k++]));

                }
                n=Integer.parseInt(array[k++]);

                List<RMenu> rMenus=new ArrayList<RMenu>();
                for(int i=0;i<n;i++) {
                    int x=Integer.parseInt(array[k++]);
                    List<Sweet> sweets=new ArrayList<Sweet>();
                    for(int j=0;j<x;j++){
                        Sweet sweet=new Sweet(array[k++], Double.parseDouble(array[k++]), Integer.parseInt(array[k++]));
                        sweets.add(sweet);
                        //aici nu am stock ci am pe intreg meniul
                    }
                    int y=Integer.parseInt(array[k++]);
                    List<RFood> rFoods=new ArrayList<RFood>();
                    for(int j=0;j<y;j++){
                        RFood rFood=new RFood(array[k++], Double.parseDouble(array[k++]), array[k++]);
                        rFoods.add(rFood);
                        //aici nu am stock ci am pe intreg meniul
                    }

                    RMenu rMenu=new RMenu(array[k++], drinks,sweets, rFoods);
                    rMenus.add(rMenu);
                    stock.put(rMenu.getName(), Integer.parseInt(array[k++]));
                }
                Restaurant shop=new Restaurant(name,owner,deliveryBoys,drinks, rMenus,stock);
                shopId+=1;
                shops.put(shopId, shop);

                line = buffer.readLine();
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void ReadUsers(){
        //setare date admin
        String name="Admin";
        String email="admin@gmail.com";
        String phone="9999999999";
        String password="Admin01";
        User admin=new User(name, email, phone, password);
        this.usersReg.add(admin);

        //citire useri din csv
        try {
             String path="./Proiect PAO/src/com/company/Users.csv";
             BufferedReader buffer = new BufferedReader
                    (new FileReader(path ));

            String line = buffer.readLine();
            while (line != null) {
                String [] array=line.split(",");
                User user=new User(array[0], array[1], array[2], array[3]);
                this.usersReg.add(user);
                line = buffer.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Reading getInstance()
    {
        if (single_instance == null)
            single_instance = new Reading();
        return single_instance;
    }
    //getteri pentru shops, usersReg
    public HashMap<Integer, Shop> getShops() {
        return shops;
    }
    public Set<User> getUsersReg() {
        return usersReg;
    }
}
