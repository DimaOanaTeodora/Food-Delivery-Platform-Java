package com.company.service;

import com.company.menu.Box;
import com.company.menu.RMenu;

import com.company.Order;

import com.company.product.*;

import com.company.shop.*;

import com.company.user.Login;
import com.company.user.User;

import java.util.*;

class Sort implements Comparator<Map.Entry<Integer, Shop>> {

    //ajuta la sortarea in TreeSet
    public int compare(Map.Entry<Integer, Shop> e1, Map.Entry<Integer, Shop> e2) {
        Shop v1 = e1.getValue();
        Shop v2 = e2.getValue();

        if (v1.getRating() > v2.getRating())
            return -1;
        return 1;
    }
}
public class Service { //singleton

    private static Service single_instance = null;
    private Login login;
    private HashMap<Integer, Shop> shops;
    private HashMap<Integer, Order> orders;
    private User currentUser;
    private int shopId;
    private int orderId;
    private ReaderCSV readingCSV;
    private AuditService writing;

    private Service(){
        this.readingCSV = ReaderCSV.getInstance();
        this.writing = AuditService.getInstance();
        this.shops=new HashMap<Integer, Shop>();
        this.orders=new HashMap<Integer, Order>();

        //citire lista de magazine din csv
        this.shops=this.readingCSV.getShops();
    }

    public static synchronized Service getInstance() {
        if (single_instance == null)
            single_instance = new Service();
        return single_instance;
    }

    public void addShop(){
        //adaugare restaurant de catre Admin
        writing.WriteTimestamp("Add Shop");

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
        writing.WriteTimestamp("Delete Shop");

        Scanner var=new Scanner(System.in);
        System.out.println("List of the names of the  shops:");

        Set set=shops.entrySet();//Convertire la set ca sa pot itera
        Iterator itr=set.iterator();

        while(itr.hasNext()){
            //Convertire la Map.Entry ca sa pot lua fiecare cheie separata
            Map.Entry entry=(Map.Entry)itr.next();
            System.out.println(((Shop) entry.getValue()).getName());
        }

        System.out.print("What is the name of the Shop do you want to remove:");
        String name=var.nextLine();

        itr=set.iterator();
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

        itr=set.iterator();
        while(itr.hasNext()){
            //Convertire la Map.Entry ca sa pot lua fiecare cheie separata
            Map.Entry entry=(Map.Entry)itr.next();
            System.out.println(((Shop) entry.getValue()).getName());
        }
    }

    public void listShops(){
        writing.WriteTimestamp("List Shops");

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
        writing.WriteTimestamp("Log Off");

        login.setCurentUser(null);
        System.out.println("See you next time!");
    }

    public void addOrder(){
        //plasare comanda
        writing.WriteTimestamp("Add Order");

        Scanner var =new Scanner (System.in);
        System.out.println("->Place an order<-");

        Order order=new Order();
        order.setCustomer(currentUser);
        order.reader(shops);
        orderId+=1;
        order.setPrice();

        if(order.getPrice()>0.0) {
            System.out.println("The order's price is: " + order.getPrice());
            System.out.println("\n***********Your order***********\n" + "\nOder ID:"+ orderId+"\n\n"+ order+"\n");
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
        writing.WriteTimestamp("Cancel Order");

        Scanner var=new Scanner(System.in);
        System.out.print("Give the order ID you want to cancel:");

        int ID=var.nextInt();
        if (this.orders.containsKey(ID)) {
            this.orders.remove(ID);
            System.out.println("Your order was removed succesfully");
        }else
            System.out.println("The order ID doesn't exists!");

    }

    public void addProduct(){
        //adaugare produs de catre admin in restaurantul de care apartine
        writing.WriteTimestamp("Add Product");

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

                    System.out.print("Stock of the product: ");

                    int stock= var.nextInt();
                    ((CakeShop) entry.getValue()).updateStock(sweet.getName(), stock);

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
                    System.out.print("Stock of the product: ");
                    int stock= var.nextInt();
                    ((FastFood) entry.getValue()).updateStock(product.getName(), stock);

                    System.out.println("The product was added succesfully");
                }
                else if(entry.getValue() instanceof Restaurant){
                    System.out.println("Restaurant");
                    System.out.print("Add a drink:");

                    Drink drink= new Drink();
                    drink.reader();
                    ((Restaurant) entry.getValue()).addDrink(drink);

                    System.out.print("Stock of the product: ");
                    int stock= var.nextInt();

                    ((FastFood) entry.getValue()).updateStock(drink.getName(), stock);

                    System.out.println("The product was added succesfully");
                }
                else {
                    System.out.println("No products found");
                }
                break;
            }
        }
    }

    public void deleteProduct(){
        //stergere produs de catre admin/restaurant de care apartine
        writing.WriteTimestamp("Delete Product");

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

                    List<Sweet> sweets=((CakeShop)entry.getValue()).getSweets();

                    for(Sweet it: sweets) {
                        if(it.getName().equalsIgnoreCase(Name)) {
                            ((CakeShop)entry.getValue()).removeSweet(it);
                            //trebuie sa-l sterg si din stock
                            ((CakeShop)entry.getValue()).removeProductFromStock(it.getName());

                            System.out.println("The product was removed succesfully");
                            break;
                        }
                    }
                }
                else if(entry.getValue() instanceof FastFood){
                    System.out.println("Fast Food");
                    System.out.print("Introduce the name of the product you want to remove:");
                    String Name=var.nextLine();

                    List<Product> products=((FastFood)entry.getValue()).getProducts();
                    //System.out.println(products);

                    for(Product it: products) {
                        if(it.getName().equalsIgnoreCase(Name)) {
                            if(it instanceof Burger)
                                ((FastFood)entry.getValue()).removeBurger((Burger) it);
                            else
                                ((FastFood)entry.getValue()).removeDrink((Drink) it);

                            //trebuie sa-l sterg si din stock
                            ((FastFood)entry.getValue()).removeProductFromStock(it.getName());

                            System.out.println("The product was removed succesfully");
                            break;
                        }
                    }
                }
                else if(entry.getValue() instanceof Restaurant){
                    System.out.println("Restaurant");
                    System.out.print("Introduce the name of the drink you want to remove:");

                    String Name=var.nextLine();
                    List<Drink> drinks=((Restaurant)entry.getValue()).getDrinks();

                    for(Drink it: drinks) {
                        if(it.getName().equalsIgnoreCase(Name)) {
                            ((Restaurant)entry.getValue()).removeDrink(it);
                            //trebuie sa-l sterg si din stock
                            ((Restaurant)entry.getValue()).removeProductFromStock(it.getName());
                            System.out.println("The product was removed succesfully");
                            break;
                        }
                    }
                }
                else {
                    System.out.println("No products found");
                }
                break;
            }
        }
    }

    public void listOneShop() {
        writing.WriteTimestamp("List One Shop");

        Scanner var = new Scanner(System.in);

        System.out.print("Introduce the name of the shop do you want to show:");
        String name = var.nextLine();
        System.out.println("------->" + name + "<---------");

        Set set = shops.entrySet();//Convertire la set ca sa pot itera
        Iterator itr = set.iterator();
        while (itr.hasNext()) {
            //Convertire la Map.Entry ca sa pot lua fiecare cheie separata
            Map.Entry entry = (Map.Entry) itr.next();
            if (((Shop) entry.getValue()).getName().equalsIgnoreCase(name)) {
                System.out.println((Shop) entry.getValue());
                break;
            }
        }
    }

    public void sortShops(){
        //sortare magazine descrecator dupa rating
        writing.WriteTimestamp("Sorting Shops by rating");
        //afisare restaurante populare in urma ratingului

        Set<Map.Entry<Integer, Shop>> set = new TreeSet<>(new Sort());
        set.addAll(this.shops.entrySet());
        //System.out.println(set);
        for (Map.Entry<Integer, Shop>
                entry : set)
            System.out.println(
                    entry.getValue().getName()+" --->Rating:"+entry.getValue().getRating());
    }

    public void addMenu(){
        writing.WriteTimestamp("Add Menu");

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

                    System.out.print("Stock of the restaurant menu: ");
                    int stock= var.nextInt();
                    ((Restaurant) entry.getValue()).updateStock(rMenu.getName(), stock);

                    System.out.println("The menu was added succesfully");
                }
                else if(entry.getValue() instanceof FastFood){
                    System.out.println("Fast Food");
                    Box box=new Box();
                    box.reader();
                    ((FastFood)entry.getValue()).addBox(box);

                    System.out.print("Stock of the box: ");
                    int stock= var.nextInt();
                    ((FastFood) entry.getValue()).updateStock(box.getName(), stock);

                    System.out.println("The box was added succesfully");
                }
                else {
                    System.out.println("No menus found");
                }
                break;
            }
        }
    }

    public int logIn(){
        //logare

        login = Login.getInstance();
        login.setUsersReg(this.readingCSV.getUsersReg());

        int type=0;
        while (true) {
            Scanner var = new Scanner(System.in);
            System.out.print("Do you want to Sign In/ Sign Up? (in/up):");
            String option = var.nextLine();

            if (option.equalsIgnoreCase("in")) {
                //conectare
                writing.WriteTimestamp("Sign In");

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
                    System.out.println("Login unsuccessfully, wrong password or email");
                }
            } else {
                //inscriere
                writing.WriteTimestamp("Sign Up");

                User customer = new User();
                customer.reader();

                if(login.signUp(customer)) {
                    System.out.println("Registration succssefully");
                    currentUser=customer;
                }
                else
                    System.out.println("You are already signed");
            }
        }
        return type;
    }

    public void rateAShop(){
        writing.WriteTimestamp("Rate a shop");

        Scanner var=new Scanner((System.in));
        System.out.println("->List of shops: ");

        if(shops.isEmpty())
            System.out.println("Shops not found");
        else {

            Set set = shops.entrySet();//Convertire la set ca sa pot itera
            Iterator itr = set.iterator();
            while (itr.hasNext()) {
                //Convertire la Map.Entry ca sa pot lua fiecare cheie separata
                Map.Entry entry = (Map.Entry) itr.next();
                System.out.println(((Shop) entry.getValue()).getName());
            }
            System.out.print("Choose one shop: ");
            String name = var.nextLine();
            //cautare magazin introdus
            itr = set.iterator();//reinitializare iterator
            Shop shop;
            while (itr.hasNext()) {
                //Convertire la Map.Entry ca sa pot lua fiecare cheie separata
                Map.Entry entry = (Map.Entry) itr.next();

                if (((Shop) entry.getValue()).getName().equalsIgnoreCase(name)) {
                    shop = (Shop) entry.getValue();
                    System.out.print("Give points between 0-5: ");
                    int points=var.nextInt();
                    shop.setRating(points);
                    System.out.println("Thank you for rating us!");
                    break;
                }
            }
        }
    }

    public void writeOneShop() {
        writing.WriteTimestamp("write a shop in CSV");

        Scanner var = new Scanner(System.in);
        WriterCSV writeCSV= WriterCSV.getInstance();

        System.out.print("Introduce the name of the shop do you want to write in CSV:");
        String name = var.nextLine();
        System.out.println("Give the path of the file do you want to write in");
        System.out.println("For example: Files/Output.csv");
        System.out.print("Introduce path: ");
        String path = var.nextLine();

        Set set = shops.entrySet();//Convertire la set ca sa pot itera
        Iterator itr = set.iterator();
        Boolean ok= false;
        while (itr.hasNext()) {
            //Convertire la Map.Entry ca sa pot lua fiecare cheie separata
            Map.Entry entry = (Map.Entry) itr.next();
            if (((Shop) entry.getValue()).getName().equalsIgnoreCase(name)) {
                System.out.println("Found the shop "+ name);
                ok = true;
                writeCSV.writeShop(entry.getValue(), entry.getValue().getClass(), path);
                break;
            }
        }
        if (ok == false)
            System.out.println("Sorry, the shop "+ name + "doesn't exits");
    }

    public String getCurentUserEmail(){
        return currentUser.getEmail();
    }

}
