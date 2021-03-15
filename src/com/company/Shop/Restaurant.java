package com.company.Shop;
 import com.company.Menu.Box;
 import com.company.Menu.Menu;
 import com.company.Menu.RMenu;
 import com.company.Product.Product;
 import com.company.Product.RFood;
 import com.company.Product.Sweet;
 import com.company.User.DeliveryBoy;
 import com.company.User.Owner;

 import java.util.*;
public class Restaurant extends Shop {
    private List<RMenu> rMenus;

    public Restaurant(){
        this.rMenus=new ArrayList<RMenu>();
    }
    public Restaurant(String name, Owner owner, List<DeliveryBoy> deliveryBoys, List<RMenu> rMenus) {
        super(name, owner, deliveryBoys);
        this.rMenus = rMenus;
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

        System.out.println("->Restaurant list of meals:");
        System.out.print("How many meals:");
        n=var.nextInt();
        for(int i=0;i<n;i++)
        {
            System.out.println("->Introduce meal number "+i+": ");
            RMenu rMenu=new RMenu();
            rMenu.reader();
            rMenus.add(rMenu);
        }
        Owner owner=new Owner();
        owner.reader(this);
        this.owner=owner;

    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "rfoods=" + rMenus +
                ", name='" + name + '\'' +
                ", owner=" + owner +
                ", deliveryBoys=" + deliveryBoys +
                ", rating=" + rating +
                '}';
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
