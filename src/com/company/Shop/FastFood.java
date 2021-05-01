package com.company.Shop;
import com.company.Menu.Box;
import com.company.Menu.Menu;
import com.company.Product.Burger;
import com.company.Product.Drink;
import com.company.Product.Product;
import com.company.Product.Sweet;
import com.company.User.DeliveryBoy;
import com.company.User.Owner;

import java.util.*;
public class FastFood extends Shop {
    private List<Box> boxes;
    private List<Drink> drinks;
    private List<Burger> burgers;

    public FastFood() {
        boxes=new ArrayList<Box>();
        drinks=new ArrayList<Drink>();
        burgers=new ArrayList<Burger>();
    }

    public FastFood(String name, Owner owner, List<DeliveryBoy> deliveryBoys, List<Box> boxes, List<Drink> drinks, List<Burger> burgers, HashMap<String, Integer>stock) {
        super(name, owner, deliveryBoys, stock);
        this.boxes = boxes;
        this.drinks = drinks;
        this.burgers = burgers;

    }

    @Override
    public void reader() {
        Scanner var = new Scanner(System.in);

        System.out.print("FastFood name:");
        String name = var.nextLine();
        this.name = name;


        System.out.println("->FastFood delivery boys:");
        System.out.print("How many delivery boys do you want:");
        int n = var.nextInt();
        for (int i = 0; i < n; i++) {
            DeliveryBoy deliveryboy = new DeliveryBoy();
            deliveryboy.reader();
            deliveryBoys.add(deliveryboy);
        }

        System.out.println("->FastFood list of burgers:");
        System.out.print("How many burgers:");
        n = var.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.println("Introduce burger number " + i + ": ");
            Burger burger = new Burger();
            burger.reader();
            this.burgers.add(burger);

            System.out.print("Introduce the stock of "+burger.getName()+" :");
            int quantity=var.nextInt();
            stock.put(burger.getName(), quantity);
        }

        System.out.println("FastFood list of drinks:");
        System.out.print("How many drinks:");
        n = var.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.println("Introduce drink number " + i + ": ");
            Drink drink = new Drink();
            drink.reader();
            this.drinks.add(drink);

            System.out.print("Introduce the stock of "+drink.getName()+" :");
            int quantity=var.nextInt();
            stock.put(drink.getName(), quantity);
        }
        System.out.println("FastFood list of boxes:");
        System.out.print("How many boxes:");
        n = var.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.println("Introduce box number " + i + ": ");
            Box box = new Box();
            box.reader();
            this.boxes.add(box);

            System.out.print("Introduce the stock of "+box.getName()+" :");
            int quantity=var.nextInt();
            stock.put(box.getName(), quantity);
        }
        Owner owner = new Owner();
        owner.reader(this);
        this.owner = owner;

    }

    @Override
    public String toString() {
        String output="------FastFood----------\n";
        output+="Name: "+ this.name+"\n";
        output+=this.owner+"\n";
        output+="Restaurant rating: "+ this.rating+"\n";
        output+="->List of Deliveryboys:\n ";
        for (DeliveryBoy db: this.deliveryBoys){
            output+= db +"\n";
        }
        output+="->List of Boxes: \n";
        for (Box box: this.boxes){
            output+= box + " \nStock:" + stock.get(box.getName()) +"\n";
        }
        output+="->List of Drinks: \n";
        for (Drink drink: this.drinks){
            output+= drink + " \nStock:" + stock.get(drink.getName()) +"\n";
        }
        output+="->List of Burgers: \n";
        for (Burger burger: this.burgers){
            output+= burger + " \nStock:" + stock.get(burger.getName()) +"\n";
        }

        return output;
    }

    @Override
    public List<Menu> getMenus() {
        List<Menu> l = new ArrayList<Menu>();
        for (Box it : boxes) {
            l.add(it);
        }
        return l;
    }

    @Override
    public List<Product> getProducts() {
        List<Product> l = new ArrayList<Product>();
        for (Drink it : drinks) {
            l.add(it);
        }
        for (Burger it : burgers) {
            l.add(it);
        }
        return l;
    }

    public void addBurger(Burger burger) {
        burgers.add(burger);
    }

    public void addDrink(Drink drink) {
        drinks.add(drink);
    }
    public void removeBurger(Burger burger){
        for(Burger it: burgers) {
            if (it.equals(burger)) {
                burgers.remove(burger);
                break;
            }
        }

    }
    public void removeDrink(Drink drink){
        for(Drink it: drinks) {
            if (it.equals(drink)) {
                drinks.remove(drink);
                break;
            }
        }
    }
    public void addBox(Box box){
        this.boxes.add(box);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FastFood)) return false;
        FastFood fastFood = (FastFood) o;
        return Objects.equals(boxes, fastFood.boxes) &&
                Objects.equals(drinks, fastFood.drinks) &&
                Objects.equals(burgers, fastFood.burgers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(boxes, drinks, burgers);
    }

    public Box orderBox(Box chooseFrom){
        Scanner var=new Scanner(System.in);
        List <Drink> D=new ArrayList<Drink>();
        int i=0;
        System.out.println("->List of drinks to choose:");
        for(Drink drink: chooseFrom.getDrinks()) {
            System.out.println("Drink number "+ i+ ":\n");
            i++;
            System.out.println(drink);
        }
        System.out.print("->Choose a drink number:");
        int choose=var.nextInt();
        D.add((chooseFrom.getDrinks()).get(choose));
        Box box=new Box (chooseFrom.getName(), D, chooseFrom.getBurger(), chooseFrom.getFries());
        return box;

    }
}
