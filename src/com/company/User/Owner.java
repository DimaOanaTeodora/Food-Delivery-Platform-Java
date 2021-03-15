package com.company.User;

import com.company.Shop.CakeShop;
import com.company.Shop.FastFood;
import com.company.Shop.Restaurant;
import com.company.Shop.Shop;

import java.util.Scanner;

public class Owner extends User {

    private Shop shop;

    public Owner(){}
    public Owner(String name, String email, String phoneNumber,  Shop shop) {
        super(name, email, phoneNumber, null);

        this.shop = shop;
    }

    @Override
    public String toString() {
        return "Owner{" +
                ", shop=" + shop +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    public void reader(Shop shop){
        Scanner var=new Scanner(System.in);

        System.out.print("Owner's name: ");
        String name = var.nextLine();
        this.name=name;
        System.out.print("Owner's e-mail: ");
        String email = var.nextLine();
        this.email=email;
        String phoneNumber;

        //validare nr de telefon
        while(true) {
            System.out.print("Owner's phone number: ");
            phoneNumber = var.nextLine();
            boolean ok = phoneNumber.matches("0[0-9]{9}");
            if (ok)
                break;
            else
                System.out.println("Please give a valid phone number: ");
        }
        this.phoneNumber=phoneNumber;

        this.shop=shop;

    }
}
