package com.company.Shop;
 import com.company.Menu.Box;
 import com.company.Menu.Menu;
 import com.company.Menu.RMenu;
 import com.company.Product.*;
 import com.company.User.DeliveryBoy;
 import com.company.User.Owner;

 import java.util.*;
public class Restaurant extends Shop {
    private List<RMenu> rMenus;
    private List<Drink> drinks;

    public Restaurant(){
        this.drinks=new ArrayList<Drink>();
        this.rMenus=new ArrayList<RMenu>();
    }
    public Restaurant(String name, Owner owner, List<DeliveryBoy> deliveryBoys,List<Drink> drinks, List<RMenu> rMenus, HashMap<String, Integer>stock) {
        super(name, owner, deliveryBoys, stock);
        this.rMenus = rMenus;
        this.drinks = drinks;
    }
    @Override
    public void reader(){
        Scanner var=new Scanner(System.in);

        System.out.print("Restaurant name:");
        String name=var.nextLine();
        this.name=name;


        System.out.println("->Restaurant delivery boys:");
        System.out.print("How many delivery boys do you want:");
        int n = var.nextInt();
        for (int i = 0; i < n; i++) {
            DeliveryBoy deliveryboy = new DeliveryBoy();
            deliveryboy.reader();
            deliveryBoys.add(deliveryboy);
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

        System.out.println("->Restaurant list of meals:");
        System.out.print("How many meals:");
        n=var.nextInt();
        for(int i=0;i<n;i++)
        {
            System.out.println("->Introduce meal number "+i+": ");
            RMenu rMenu=new RMenu();
            rMenu.reader();
            rMenus.add(rMenu);

            System.out.print("Introduce the stock of "+rMenu.getName()+" :");
            int quantity=var.nextInt();
            stock.put(rMenu.getName(), quantity);
        }
        Owner owner=new Owner();
        owner.reader(this);
        this.owner=owner;

    }

    @Override
    public String toString() {
        String output="------Restaurant----------";
        output+="Name: "+ this.name+"\n";
        output+= this.owner+"\n";
        output+="Restaurant rating: "+ this.rating+"\n";
        output+="->List of Deliveryboys:\n ";
        for (DeliveryBoy db: this.deliveryBoys){
            output+= db +"\n";
        }
        output+="->List of Drinks: \n";
        for (Drink drink: this.drinks){
            output+= drink + " \nStock:" + stock.get(drink.getName()) +"\n";
        }
        output+="->List of rMenus: \n";
        for (RMenu rMenu: rMenus){
            output+= rMenu + " \nStock:" + stock.get(rMenu.getName()) +"\n";
        }

        return output;
    }

    @Override
    public List<Menu> getMenus() {
        List<Menu> l = null;
        for(RMenu it: rMenus) {
            l.add(it);
        }
        return l;
    }

    @Override
    public List<Product> getProducts() {
        return null;
    }
    public void addrMenu(RMenu menu){
        rMenus.add(menu);
    }
}
