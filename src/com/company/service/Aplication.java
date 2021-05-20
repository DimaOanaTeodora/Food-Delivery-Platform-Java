package com.company.service;

import java.util.Scanner;

public class Aplication {//singleton

    private static Aplication single_instance = null;
    private Aplication(){}
    public static synchronized Aplication getInstance()
    {
        if (single_instance == null)
            single_instance = new Aplication();
        return single_instance;
    }

    private void listActionsForAdmin(){
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
        System.out.println("9)Manage database");//done
        System.out.print("->What operation you choose?(1/2/3/4/5/6/7/8/9):");
    }

    private void listActionsForUser(Service service){
        System.out.println("->Signed as "+ service.getCurentUserEmail());
        System.out.println("--->Your actions<---");
        System.out.println("1)List shops");//done
        System.out.println("2)List one shop");//done
        System.out.println("3)Sorting shops by rating ");//done
        System.out.println("4)Place an order");//done
        System.out.println("5)Cancel an order");
        System.out.println("6)Log Off");//done
        System.out.println("7)Rate one shop");//done
        System.out.println("8)Write one shop in CSV file");//done
        System.out.print("->What operation you choose?(1/2/3/4/5/6/7/8):");
    }

    private void adminActions(Service service){
        //Actiuni Admin
        Scanner var=new Scanner(System.in);

        while(true) {
            this.listActionsForAdmin();
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
            else if (choose == 9) {
                //management baza de date de catre admin
                DatabaseMenu databaseMenu=DatabaseMenu.getInstance();
                databaseMenu.DatabaseService();
            }
            System.out.print("Do you want another action in shop?(yes/no):");
            String answer=var.nextLine();
            if(!answer.equalsIgnoreCase("yes"))
                break;

        }
    }

    private void userAction(Service service){
        Scanner var=new Scanner(System.in);

        while(true) {
            this.listActionsForUser(service);
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
            else if (choose == 8) {
                //afisare magazin in CSV
                service.writeOneShop();
            }

            System.out.print("Do you want another action in shop?(yes/no):");
            String answer=var.nextLine();
            if(!answer.equalsIgnoreCase("yes"))
                break;

        }

    }

    public void start() {
      Service service = Service.getInstance();

        if(service.logIn()==1){
            this.adminActions(service);
        }
        else {
            this.userAction(service);
        }

    }
}
