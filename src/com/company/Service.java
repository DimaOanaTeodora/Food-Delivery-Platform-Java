package com.company;

import com.company.Menu.Box;
import com.company.Menu.RMenu;
import com.company.Product.*;
import com.company.Shop.CakeShop;
import com.company.Shop.FastFood;
import com.company.Shop.Restaurant;
import com.company.Shop.Shop;
import com.company.User.DeliveryBoy;
import com.company.User.Login;
import com.company.User.Owner;
import com.company.User.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Service { //singleton

    private static Service single_instance = null;
    private Login login;
    private HashMap<Integer, Shop> shops;
    private HashMap<Integer, Order> orders;
    private User currentUser;
    private int shopId;
    private int orderId;

    private Service() {
        shops=new HashMap<Integer, Shop>();
        orders=new HashMap<Integer, Order>();
        //citire lista de magazine din csv

        //citire CakeShops

        try (BufferedReader buffer = new BufferedReader(new
                FileReader("C:\\Users\\Lenovo\\Desktop\\Food-Delivery-Platform-Java-First-Phase\\Proiect PAO\\src\\com\\company\\CakeShop.csv"))) {

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
        try (BufferedReader buffer = new BufferedReader(new
                FileReader("C:\\Users\\Lenovo\\Desktop\\Food-Delivery-Platform-Java-First-Phase\\Proiect PAO\\src\\com\\company\\FastFood.csv"))) {

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
        try (BufferedReader buffer = new BufferedReader(new
                FileReader("C:\\Users\\Lenovo\\Desktop\\Food-Delivery-Platform-Java-First-Phase\\Proiect PAO\\src\\com\\company\\Restaurant.csv"))) {

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
    public static Service getInstance()
    {
        if (single_instance == null)
            single_instance = new Service();
        return single_instance;
    }

    public void addShop(){
        //adaugare restaurant de catre Admin
        Shop shop;
        Scanner var=new Scanner(System.in);
        //validare tip restaurant
        while(true) {
            System.out.print("What kind of shop do you want to add(CakeShop/FastFood/Restaurant):");
            String type=var.nextLine();
            if (type.equalsIgnoreCase("cakeshop")) {
                shop = new CakeShop();
                break;
            }
            else if (type.equalsIgnoreCase("fastfood")) {
                shop = new FastFood();
                break;
            }
            else if (type.equalsIgnoreCase("restaurant")) {
                shop = new Restaurant();
                break;
            }
            System.out.println("Not a valid type..try again :(");

        }
        shopId+=1;
        shop.reader();
        shops.put(shopId,shop);
        System.out.println("Shop added succsefully");

    }

    public void deleteShop(){
        //stergere restaurant de catre Admin
        Scanner var=new Scanner(System.in);
        System.out.print("What is the name of the Shop do you want to remove:");
        String name=var.nextLine();

        Set set=shops.entrySet();//Convertire la set ca sa pot itera
        Iterator itr=set.iterator();
        while(itr.hasNext()){
            //Convertire la Map.Entry ca sa pot lua fiecare cheie separata
            Map.Entry entry=(Map.Entry)itr.next();
            if(((Shop) entry.getValue()).getName().equalsIgnoreCase(name)) {
                shops.remove(entry.getKey());
                System.out.println("The shop has been succesfully removed");
                break;
            }
        }
        System.out.println("List of the names of the remaining shops:");
        while(itr.hasNext()){
            //Convertire la Map.Entry ca sa pot lua fiecare cheie separata
            Map.Entry entry=(Map.Entry)itr.next();
            System.out.println(((Shop) entry.getValue()).getName());
        }
    }

    public void listShops(){
        System.out.println("------->Shops<---------");
        Set set=shops.entrySet();//Convertire la set ca sa pot itera
        Iterator itr=set.iterator();
        while(itr.hasNext()){
            //Convertire la Map.Entry ca sa pot lua fiecare cheie separata
            Map.Entry entry=(Map.Entry)itr.next();
            System.out.println(((Shop) entry.getValue()));
            System.out.println("---------------------");
        }

    }

    public void logOff(){
        //delogare
        login.setCurentUser(null);
        System.out.println("See you next time!");
    }

    public void addOrder(){
        //plasare comanda
        Scanner var =new Scanner (System.in);
        System.out.println("->Place an order<-");
        Order order=new Order();
        order.setCustomer(currentUser);
        order.reader(shops);
        orderId+=1;
        order.setPrice();
        if(order.getPrice()>0.0) {
            System.out.println("The order's price is: " + order.getPrice());
            System.out.println("--->Your order is: \n" + "Oder ID:"+ orderId+ order);
            System.out.print("Do you want to place the order?(yes/no):");
            String answer = var.nextLine();
            if (answer.equalsIgnoreCase("yes")) {
                orders.put(orderId, order);
                System.out.println("Your order was succesfully procesed");
            }
        }


    }

    public void cancelOrder(){
        //anulare plasare comanda
        Scanner var=new Scanner(System.in);
        System.out.println("Give the order ID you want to cancel:");
        int ID=var.nextInt();

        Set set=shops.entrySet();//Convertire la set ca sa pot itera
        Iterator itr=set.iterator();
        while(itr.hasNext()){
            //Convertire la Map.Entry ca sa pot lua fiecare cheie separata
            Map.Entry entry=(Map.Entry)itr.next();
            if((int)entry.getKey()==ID) {
                orders.remove(entry.getValue());
                System.out.print("The order has been succesfully canceld");
                break;
            }

        }

    }

    public void addProduct(){
        //adaugare produs de catre admin in restaurantul de care apartine
        Scanner var=new Scanner (System.in);
        System.out.println("------Add a product------");
        System.out.print("Introduce the name of the shop:");
        String name=var.nextLine();
        Set set=shops.entrySet();//Convertire la set ca sa pot itera
        Iterator itr=set.iterator();
        while(itr.hasNext()){
            //Convertire la Map.Entry ca sa pot lua fiecare cheie separata
            Map.Entry entry=(Map.Entry)itr.next();
            if(((Shop) entry.getValue()).getName().equalsIgnoreCase(name)) {
                System.out.print("Shop found: ");
                if(entry.getValue() instanceof CakeShop){
                    System.out.println("Cake Shop");
                    Sweet sweet=new Sweet();
                    sweet.reader();
                    ((CakeShop)entry.getValue()).addSweet(sweet);
                    System.out.println("The product was added succesfully");
                }
                else if(entry.getValue() instanceof FastFood){
                    System.out.println("Fast Food");
                    System.out.print("Do you want to add a burger or a drink?:");
                    String answer=var.nextLine();
                    Product product=null;
                    if(answer.equalsIgnoreCase("burger")) {
                        product = new Burger();
                        product.reader();
                        ((FastFood) entry.getValue()).addBurger((Burger) product);
                    }
                    else {
                        product = new Drink();
                        product.reader();
                        ((FastFood) entry.getValue()).addDrink((Drink) product);
                    }
                    System.out.println("The product was added succesfully");
                }
                else
                {
                    System.out.println("No products found");
                }
                break;
            }

        }

    }

    public void deleteProduct(){
        //stergere produs de catre admin/restaurant de care apartine
        Scanner var=new Scanner (System.in);
        System.out.println("------Remove a product------");
        System.out.print("Introduce the name of the shop:");
        String name=var.nextLine();
        Set set=shops.entrySet();//Convertire la set ca sa pot itera
        Iterator itr=set.iterator();
        while(itr.hasNext()){
            //Convertire la Map.Entry ca sa pot lua fiecare cheie separata
            Map.Entry entry=(Map.Entry)itr.next();
            if(((Shop) entry.getValue()).getName().equalsIgnoreCase(name)) {
                System.out.print("Shop found: ");
                if(entry.getValue() instanceof CakeShop){
                    System.out.println("Cake Shop");
                    System.out.print("Introduce the name of the cake you want to remove:");
                    String Name=var.nextLine();
                    List<Product> products=((CakeShop)entry.getValue()).getProducts();
                    for(Product it: products) {
                        if(it.getName().equalsIgnoreCase(Name))
                        {
                            ((CakeShop)entry.getValue()).removeSweet((Sweet) it);
                            System.out.println("The product was removed succesfully");
                            break;
                        }
                    }

                }
                else if(entry.getValue() instanceof FastFood){
                    System.out.println("Fast Food");
                    System.out.print("Introduce the name of the cake you want to remove:");
                    String Name=var.nextLine();
                    List<Product> products=((FastFood)entry.getValue()).getProducts();
                    for(Product it: products) {
                        if(it.getName().equalsIgnoreCase(Name))
                        {
                            if(it instanceof Burger)
                                ((FastFood)entry.getValue()).removeBurger((Burger) it);
                            else
                                ((FastFood)entry.getValue()).removeDrink((Drink) it);
                            System.out.println("The product was removed succesfully");
                            break;
                        }
                    }
                }
                else
                {
                    System.out.println("No products found");
                }
                break;
            }

        }
    }

    public void getPopularShops(){
        //afisare restaurante populare in urma ratingului
        TreeMap<Integer, Shop> sorted = new TreeMap<>(shops);
        sorted.putAll(shops);
        //afisare
        for (Map.Entry<Integer, Shop>
                entry : sorted.entrySet())
            System.out.println(
                    "[" + entry.getKey()
                            + ", " + entry.getValue() + "]");
    }

    public void addMenu(){
        Scanner var=new Scanner (System.in);
        System.out.println("------Add a menu------");
        System.out.print("Introduce the name of the shop:");
        String name=var.nextLine();
        Set set=shops.entrySet();//Convertire la set ca sa pot itera
        Iterator itr=set.iterator();
        while(itr.hasNext()){
            //Convertire la Map.Entry ca sa pot lua fiecare cheie separata
            Map.Entry entry=(Map.Entry)itr.next();
            if(((Shop) entry.getValue()).getName().equalsIgnoreCase(name)) {
                System.out.print("Shop found: ");
                if(entry.getValue() instanceof Restaurant){
                    System.out.println("Restaurant");
                    RMenu rMenu=new RMenu();
                    rMenu.reader();
                    ((Restaurant)entry.getValue()).addrMenu(rMenu);
                    System.out.println("The menu was added succesfully");
                }
                else if(entry.getValue() instanceof FastFood){
                    System.out.println("Fast Food");
                    Box box=new Box();
                    box.reader();
                    ((FastFood)entry.getValue()).addBox(box);
                    System.out.println("The box was added succesfully");
                }
                else
                {
                    System.out.println("No menus found");
                }
                break;
            }

        }
    }

    public int logIn(){
        //logare
        login = Login.getInstance();
        int type=0;
        while (true) {
            Scanner var = new Scanner(System.in);
            System.out.print("Do you want to Sign In/ Sign Up? (in/up):");
            String option = var.nextLine();
            if (option.equalsIgnoreCase("in")) {
                //conectare
                System.out.print("Email: ");
                String email = var.nextLine();
                System.out.print("Password: ");
                String password = var.nextLine();

                if (login.signIn(email, password)) {
                    System.out.println("Login successfully");
                    currentUser=login.getCurentUser();
                    if (email.equals("admin@gmail.com"))
                        type=1;
                    break;
                }
                else {
                    System.out.println("Login unsuccessfully, "+email+" doesn't exists");

                }

            } else {
                //inscriere

                User customer = new User();
                customer.reader();
                if(login.signUp(customer))
                {
                    System.out.println("Registration succssefully");
                    currentUser=customer;
                }
                else
                    System.out.println("You are already signed");

            }
        }
        return type;
    }

    public String getCurentUserEmail(){
        return currentUser.getEmail();
    }

}
