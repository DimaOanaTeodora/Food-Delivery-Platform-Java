package com.company;

import com.company.Menu.Menu;
import com.company.Product.Product;
import com.company.Shop.CakeShop;
import com.company.Shop.FastFood;
import com.company.Shop.Restaurant;
import com.company.Shop.Shop;
import com.company.User.DeliveryBoy;
import com.company.User.User;

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
    public void setDeliveryBoy(Shop shop) {
        //aleg random un baiat de livrat
        List<DeliveryBoy> D=shop.getDeliveryBoys();
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
        return "Order{" +
                "customer=" + customer +
                ", shop=" + shop +
                ", deliveryBoy=" + deliveryBoy +
                ", price=" + price +
                ", menus=" + menus +
                ", products=" + products +
                ", address='" + address + '\'' +
                '}';
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

            Shop shop = null;
            while (itr.hasNext()) {
                //Convertire la Map.Entry ca sa pot lua fiecare cheie separata
                Map.Entry entry = (Map.Entry) itr.next();
                if (((Shop) entry.getValue()).getName().equalsIgnoreCase(name)) {
                    //shop=(Shop) entry.getValue();
                    //Downcasting
                    if (entry.getValue() instanceof CakeShop)
                        shop = (CakeShop) entry.getValue();
                    else if (entry.getValue() instanceof FastFood)
                        shop = (FastFood) entry.getValue();
                    else
                        shop = (Restaurant) entry.getValue();
                    break;
                }
            }
            this.shop = shop;

            //aleg random un baiat de livrat
            this.setDeliveryBoy(shop);

            List<Product> products = shop.getProducts();
            List<Menu> menus = shop.getMenus();
            int choose = 0;
            System.out.println("->The products are:");
            for (int i = 0; i < products.size(); i++) {
                System.out.println("->Product number " + i + "is:");
                System.out.println(products.get(i));
            }

            String answer;
            System.out.print("->Do you want to add a menu?(yes/no):");
            answer = var.nextLine();
            if (answer.equalsIgnoreCase("yes")) {
                System.out.println("Choose products:");
                while (true) {
                    System.out.println("Choose the product wtih number:");
                    choose = var.nextInt();
                    this.setProducts(products.get(choose));
                    System.out.print("Do you want to add another?(yes/no):");
                    answer = var.nextLine();
                    if (answer.equalsIgnoreCase("no"))
                        break;
                }
            }
            System.out.println("->The menus are:");
            for (int i = 0; i < menus.size(); i++) {
                System.out.println("Menu number " + i + "is:");
                System.out.println(menus.get(i));
            }

            System.out.print("->Do you want to add a menu?(yes/no):");
            answer = var.nextLine();
            if (answer.equalsIgnoreCase("yes")) {

                while (true) {
                    System.out.println("Choose menu number:");
                    choose = var.nextInt();
                    this.setMenus(menus.get(choose));
                    System.out.print("Do you want to add another?(yes/no):");
                    answer = var.nextLine();
                    if (answer.equalsIgnoreCase("no"))
                        break;
                }
            }
        }

    }
}
