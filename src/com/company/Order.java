package com.company;

import com.company.menu.*;

import com.company.product.Product;

import com.company.shop.*;

import com.company.user.DeliveryBoy;
import com.company.user.User;

import java.util.*;

public class Order {
    private User customer;
    private Shop shop;
    private DeliveryBoy deliveryBoy;
    private double price;
    private List<Menu> menus;
    private List<Product> products;
    private String address;

    public Order(){
        this.menus=new ArrayList<Menu>();
        this.products=new ArrayList<Product>();
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
    private int getRandomNumber(int min, int max) {
        // [min, max)
        return (int) ((Math.random() * (max - min)) + min);
    }
    public void setDeliveryBoy() {
        //aleg random un baiat de livrat
        List<DeliveryBoy> D=this.shop.getDeliveryBoys();
        int random=getRandomNumber(0,D.size());
        this.deliveryBoy=D.get(random);
    }


    public void setPrice() {

        double totalPrice=0;
        for(Menu it: menus){
            totalPrice+=it.getPrice();
        }
        for(Product it: products){
            totalPrice+=it.getPrice();
        }
        this.price=totalPrice;


    }
    public double getPrice(){
        return this.price;
    }

    public void setMenus(Menu menu) {
        this.menus.add(menu);
    }

    public void setProducts(Product product) {
        this.products.add(product);
    }

    @Override
    public String toString() {
        String output=""+this.customer+"\n";
        output+="*Value: "+this.price+" lei\n";
        output+="*Delivery address: "+this.address+"\n";
        output+=this.deliveryBoy;
        output+="*Shop: "+this.shop.getName()+"\n";
        output+="**The menus you chooes:**\n";
        for(Menu it: this.menus)
            output+=it;
        output+="**The products you chooes:**\n";
        for(Product it: this.products)
            output+=it;

        return output;
    }
    public void reader(HashMap<Integer, Shop> shops){
        Scanner var=new Scanner(System.in);

        System.out.print("Delivery address: ");
        String address=var.nextLine();
        this.address=address;

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


            itr = set.iterator();//reinitializare iterator
            while (itr.hasNext()) {
                //Convertire la Map.Entry ca sa pot lua fiecare cheie separata
                Map.Entry entry = (Map.Entry) itr.next();

                if (((Shop) entry.getValue()).getName().equalsIgnoreCase(name)) {
                    //Downcasting
                    if (entry.getValue() instanceof CakeShop)
                        this.shop  = (CakeShop) entry.getValue();
                    else if (entry.getValue() instanceof FastFood)
                        this.shop = (FastFood) entry.getValue();
                    else
                        this.shop  = (Restaurant) entry.getValue();
                    break;
                }
            }

            //aleg random un baiat de livrat
            this.setDeliveryBoy();

            List<Product> products = this.shop.getProducts();
            int choose = 0;
            System.out.println("->The products are:");
            for (int i = 0; i < products.size(); i++) {
                System.out.println("->Product number " + i + " is:");
                System.out.println(products.get(i));
            }

            List<Menu> menus = this.shop.getMenus();
            System.out.println("->The menus are:");
            for (int i = 0; i < menus.size(); i++) {
                System.out.println("Menu number " + i + " is:");
                System.out.println(menus.get(i));
            }

            System.out.print("->Do you want to add a menu?(yes/no):");
            String answer = var.nextLine();
            if (answer.equalsIgnoreCase("yes")) {

                while (true) {
                    System.out.print("Choose menu number:");
                    //trebuie sa afisez lista de bauturi si/sau mancaruri si
                    // sa fac un nou meniu format cu cate unul din fiecare
                    //=> fac o functie care sa-mi returneze un astfel de meniu pe care il adaug la comanda
                    //orderBox(Box)
                    //orderRMenu(RMenu)
                    choose = var.nextInt();
                    Menu menu;
                    if (this.shop instanceof FastFood)
                       menu= ((FastFood) this.shop).orderBox((Box) menus.get(choose));
                    else
                        menu=((Restaurant) this.shop).orderRMenu((RMenu) menus.get(choose));
                    this.setMenus(menu);
                    //scad stock-ul
                    this.shop.StockLower(menu.getName());

                    System.out.print("Do you want to add another?(yes/no):");
                    var.nextLine();
                    answer = var.nextLine();
                    if (answer.equalsIgnoreCase("no"))
                        break;

                }
            }

            System.out.print("->Do you want to add a product?(yes/no):");
            answer = var.nextLine();
            if (answer.equalsIgnoreCase("yes")) {
                System.out.println("Choose products:");
                while (true) {
                    System.out.print("Choose product number:");
                    choose = var.nextInt();
                    this.setProducts(products.get(choose));

                    //scad stock-ul
                    this.shop.StockLower((products.get(choose)).getName());

                    System.out.print("Do you want to add another?(yes/no):");
                    var.nextLine();
                    answer = var.nextLine();
                    if (answer.equalsIgnoreCase("no"))
                        break;
                }
            }
        }

    }
}
