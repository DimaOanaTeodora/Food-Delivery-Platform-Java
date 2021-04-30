package com.company;

import java.util.*;


public class Main {

    public static void main(String[] args) {

    // TODO: Cand fac un menu trebuie sa scad numarul din stock-uri
     //TODO:am verificat add shop, list shops, sign in/up, log off, delete shop,
        // TODO: de adaugat posibilitatea de a adauga un rating la un shop
        //TODO: La add a product de adaugat stock-ul
        //TODO: de inlocuit cu o metoda de listare a unui shop dupa nume


        Service service = Service.getInstance();

        if(service.logIn()==1){
            //Actiuni Admin
            Scanner var=new Scanner(System.in);
            System.out.println("->Signed as admin");
            System.out.println("--->Your actions<---");
            System.out.println("1)Add Shop");
            System.out.println("2)Remove Shop");
            System.out.println("3)Add a product");
            System.out.println("4)Delete a product");
            System.out.println("5)Add a menu");
            System.out.println("6)Log Off");
            System.out.println("7)List shops");
            while(true) {
                System.out.print("->What operation you choose?(1/2/3/4/5/6/7):");
                int choose =var.nextInt();
                var.nextLine();
                if (choose == 1) {
                    //adaugare magazin
                    service.addShop();
                } else if (choose == 2) {
                    //stergere magazin
                    service.deleteShop();
                } else if (choose == 3) {
                    //adaugare produs
                    service.addProduct();
                } else if (choose == 4) {
                    //stergere produs
                    service.deleteProduct();
                } else if (choose == 5) {
                    //adaugare menu
                    service.addMenu();
                } else if (choose == 6) {
                    //delogare
                    service.logOff();
                    break;
                }
                else if (choose == 7) {
                    //afisare magazine
                    service.listShops();
                }
                System.out.print("Do you want another action?(yes/no):");
                String answer=var.nextLine();
                if(answer.equalsIgnoreCase("no"))
                    break;

            }
        }
        else {

            Scanner var=new Scanner(System.in);
            System.out.println("->Signed as "+ service.getCurentUserEmail());
            System.out.println("--->Your actions<---");
            System.out.println("1)List shops");
            System.out.println("2)List popular shops");
            System.out.println("3)Place an order");
            System.out.println("4)Cancel an order");
            System.out.println("5)Log Off");
            while(true) {
                System.out.print("->What operation you choose?(1/2/3/4/5):");
                int choose =var.nextInt();
                var.nextLine();
                if (choose == 1) {
                    //afisare magazine
                    service.listShops();
                } else if (choose == 2) {
                    //afisare magazine sortate dupa rating
                    service.getPopularShops();
                } else if (choose == 3) {
                    //plasare comanda
                    service.addOrder();
                } else if (choose == 4) {
                    //anulare comanda
                    service.cancelOrder();
                } else if (choose == 5) {
                    //delogare
                    service.logOff();
                    break;
                }
                System.out.print("Do you want another action?(yes/no):");
                String answer=var.nextLine();
                if(answer.equalsIgnoreCase("no"))
                    break;

            }

        }


    }


}
