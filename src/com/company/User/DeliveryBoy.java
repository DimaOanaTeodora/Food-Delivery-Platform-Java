package com.company.User;

import com.company.Shop.CakeShop;
import com.company.Shop.FastFood;
import com.company.Shop.Restaurant;
import com.company.Shop.Shop;

import java.util.Objects;
import java.util.Scanner;

public class DeliveryBoy extends User {
    private String carNumber;

    public DeliveryBoy(){}
    public DeliveryBoy(String name, String email, String phoneNumber,  String carNumber) {
        super(name, email, phoneNumber, null);
        this.carNumber = carNumber;
    }


    public void reader(){
        Scanner var=new Scanner(System.in);

        System.out.print("Delivery Boy name: ");
        String name = var.nextLine();
        this.name=name;
        System.out.print("Delivery Boy e-mail: ");
        String email = var.nextLine();
        this.email=email;
        String phoneNumber;

        //validare nr de telefon
        while(true) {
            System.out.print("Delivery Boy phone number: ");
            phoneNumber = var.nextLine();
            boolean ok = phoneNumber.matches("0[0-9]{9}");
            if (ok)
                break;
            else
                System.out.println("Please give a valid phone number: ");
        }
        this.phoneNumber=phoneNumber;

        System.out.print("Delivery Boy car number: ");
        String carNumber = var.nextLine();
        this.carNumber=carNumber;



    }
    @Override
    public String toString() {
        return "DeliveryBoy{" +
                "carNumber='" + carNumber + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeliveryBoy)) return false;
        DeliveryBoy that = (DeliveryBoy) o;
        return Objects.equals(carNumber, that.carNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carNumber);
    }
}
