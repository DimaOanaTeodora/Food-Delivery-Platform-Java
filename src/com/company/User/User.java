package com.company.User;

import com.company.Shop.Shop;

import java.util.Scanner;

public class User {
    //customer sau admin
    protected String name;
    protected String email; //adresa admin: admin@gmail.com
    protected String phoneNumber;
    private String password;
    public User(){}

    public User(String name, String email, String phoneNumber, String password) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password=password;

    }
    public void reader(){
        Scanner var = new Scanner(System.in);
        System.out.print("Name: ");
        String name = var.nextLine();
        System.out.print("Email: ");
        String email = var.nextLine();
        String phoneNumber;

        //validare nr de telefon
        while(true) {
            System.out.print("Phone number: ");
            phoneNumber = var.nextLine();
            boolean ok = phoneNumber.matches("0[0-9]{9}");
            if (ok)
                break;
            else
                System.out.println("Please give a valid phone number: ");
        }
        System.out.print("Password: ");
        String password = var.nextLine();

        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password=password;
    }

    @Override
    public String toString() {
        String output="----User Information----\n";
        output+="Name: "+this.name+"\n";
        output+="Email: "+this.email+"\n";
        output+="Phone: "+this.phoneNumber+"\n";

        return output;

    }

    public String getName() {
        return name;
    }
    public String getPassword() { return password; }
    public String getEmail(){return email;}

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}