package com.company.service;

import com.company.menu.*;

import com.company.product.*;

import com.company.shop.*;

import com.company.user.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.*;

public class ReaderCSV {//singleton

    private static ReaderCSV single_instance = null;
    private HashMap<Integer, Shop> shops;
    private int shopId;
    private Set<User> usersReg;

    private void ReadCakeShop(String [] array, int k, Owner owner, List<DeliveryBoy> deliveryBoys){

        String name=array[k++];
        int n=Integer.parseInt(array[k++]);
        List<Sweet> sweets=new ArrayList<Sweet>();
        HashMap<String, Integer>stock=new HashMap<String, Integer>();

        for(int i=0;i<n;i++) {
            Sweet s = new Sweet(array[k++], Double.parseDouble(array[k++]), Integer.parseInt(array[k++]));
            sweets.add(s);
            stock.put(s.getName(), Integer.parseInt(array[k++]));
        }
        CakeShop shop=new CakeShop(name,owner,deliveryBoys,sweets,stock);

        this.shopId+=1;
        this.shops.put(shopId, shop);
    }

    private void ReadFastFood(String [] array, int k, Owner owner, List<DeliveryBoy> deliveryBoys){

        String name=array[k++];
        int n=Integer.parseInt(array[k++]);
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

        this.shopId+=1;
        this.shops.put(shopId, shop);
    }

    public void ReadRestaurant(String [] array, int k, Owner owner, List<DeliveryBoy> deliveryBoys){

        String name=array[k++];
        int n=Integer.parseInt(array[k++]);
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
            }

            int y=Integer.parseInt(array[k++]);
            List<RFood> rFoods=new ArrayList<RFood>();

            for(int j=0;j<y;j++){
                RFood rFood=new RFood(array[k++], Double.parseDouble(array[k++]), array[k++]);
                rFoods.add(rFood);
            }

            RMenu rMenu=new RMenu(array[k++], drinks,sweets, rFoods);

            rMenus.add(rMenu);
            stock.put(rMenu.getName(), Integer.parseInt(array[k++]));
        }
        Restaurant shop=new Restaurant(name,owner,deliveryBoys,drinks, rMenus,stock);

        this.shopId+=1;
        this.shops.put(shopId, shop);
    }

    //Metoda generica pentru citirea shopurilor
     private <T> void AuxiliaryMethod(String path, Class<T> classOf) {
        try {

            BufferedReader buffer = new BufferedReader(new FileReader(path));

            String line = buffer.readLine();

            while (line != null) {
                String[] array = line.split(",");
                int k = 0;
                Owner owner = new Owner(array[k++], array[k++], array[k++]);
                int n = Integer.parseInt(array[k++]);
                List<DeliveryBoy> deliveryBoys = new ArrayList<DeliveryBoy>();

                for (int i = 0; i < n; i++) {
                    DeliveryBoy d = new DeliveryBoy(array[k++], array[k++], array[k++], array[k++]);
                    deliveryBoys.add(d);
                }

                if (classOf.toString().equals("class com.company.shop.CakeShop")) {
                    this.ReadCakeShop(array, k, owner, deliveryBoys);
                }
                else if (classOf.toString().equals("class com.company.shop.FastFood")) {
                    this.ReadFastFood(array, k, owner, deliveryBoys);
                }
                else if (classOf.toString().equals("class com.company.shop.Restaurant")){
                   this.ReadRestaurant(array, k, owner, deliveryBoys);
                }
                else
                    System.out.println("Eroare la citirea din fisiere");

                line = buffer.readLine();
            }
        }
                catch (IOException e) {

                    e.printStackTrace();
                }
    }

    private ReaderCSV() {
        this.usersReg=new HashSet<User>();
        this.shops=new HashMap<Integer, Shop>();

        //apelare metoda de citire a userilor
        ReadUsers();

        //citire restaurante folosind metoda generica
        this.AuxiliaryMethod("Files/CakeShop.csv", CakeShop.class);
        this.AuxiliaryMethod("Files/FastFood.csv", FastFood.class);
        this.AuxiliaryMethod("Files/Restaurant.csv", Restaurant.class);
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
             String path="Files/Users.csv";
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

    public static synchronized ReaderCSV getInstance() {
        if (single_instance == null)
            single_instance = new ReaderCSV();
        return single_instance;
    }

    public HashMap<Integer, Shop> getShops() {
        return shops;
    }

    public Set<User> getUsersReg() {
        return usersReg;
    }
}
