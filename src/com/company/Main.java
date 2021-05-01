package com.company;

import java.util.*;


public class Main {

    public static void listActionsForAdmin(){
        System.out.println("->Signed as admin");
        System.out.println("--->Your actions<---");
        System.out.println("1)Add Shop");//done
        System.out.println("2)Remove Shop");//done
        System.out.println("3)Add a product");//done
        System.out.println("4)Delete a product");//done
        System.out.println("5)Add a menu");//done
        System.out.println("6)Log Off");//done
        System.out.println("7)List shops");//done
        System.out.println("8)List one shop");//done
        System.out.print("->What operation you choose?(1/2/3/4/5/6/7/8):");
    }
    public static void listActionsForUser(Service service){
        System.out.println("->Signed as "+ service.getCurentUserEmail());
        System.out.println("--->Your actions<---");
        System.out.println("1)List shops");//done
        System.out.println("2)List one shop");//done
        System.out.println("3)Sorting shops by rating ");
        System.out.println("4)Place an order");
        System.out.println("5)Cancel an order");
        System.out.println("6)Log Off");//done
        System.out.println("7)Rate one shop");//done
        System.out.print("->What operation you choose?(1/2/3/4/5/6/7):");
    }

    public static void main(String[] args) {

        //TODO: Cand fac un menu trebuie sa scad numarul din stock-uri
        //TODO: de adaugat posibilitatea de a adauga un rating la un shop
        //TODO: de regandit lista de bauturi de la Restaurant
        //TODO: de scazut stock-ul la comandarea unui produs
        //TODO: de regandit clasele: User, Owner, DelieveryBoy


        Service service = Service.getInstance();

        if(service.logIn()==1){
            //Actiuni Admin
            Scanner var=new Scanner(System.in);

            while(true) {
                listActionsForAdmin();
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
                else if (choose == 8) {
                    //afisare magazine
                    service.listOneShop();
                }
                System.out.print("Do you want another action?(yes/no):");
                String answer=var.nextLine();
                if(answer.equalsIgnoreCase("no"))
                    break;

            }
        }
        else {

            Scanner var=new Scanner(System.in);

            while(true) {
                listActionsForUser(service);
                int choose =var.nextInt();
                var.nextLine();
                if (choose == 1) {
                    //afisare magazine
                    service.listShops();
                } else if (choose == 2) {
                    //afisare un singur magazin dupa nume
                    service.listOneShop();
                } else if (choose == 3) {
                    //sortare magazine descrescator dupa rating
                    service.sortShops();

                } else if (choose == 4) {
                    //plasare comanda
                    service.addOrder();

                } else if (choose == 5) {
                    //anulare comanda
                    service.cancelOrder();
                }
                else if (choose == 6) {
                    //delogare
                    service.logOff();
                    break;
                }
                else if (choose == 7) {
                    //adaugare rating magazin
                    service.rateAShop();
                }

                System.out.print("Do you want another action?(yes/no):");
                String answer=var.nextLine();
                if(answer.equalsIgnoreCase("no"))
                    break;

            }

        }


    }


}
