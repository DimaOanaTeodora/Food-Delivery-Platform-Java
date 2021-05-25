package com.company.service;

import com.company.config.DataSetup;
import com.company.product.Burger;
import com.company.product.Drink;
import com.company.product.RFood;
import com.company.product.Sweet;
import com.company.repository.BurgerRepository;
import com.company.repository.DrinkRepository;
import com.company.repository.RFoodRepository;
import com.company.repository.SweetRepository;

import java.util.Scanner;

public class DatabaseMenu {//singleton

    private DataSetup setUpData;
    private AuditService writing;
    Scanner var;
    private static DatabaseMenu single_instance = null;

    private DatabaseMenu(){
        this.writing = AuditService.getInstance();
        this.setUpData = new DataSetup();
        this.var=new Scanner(System.in);
    }

    public static synchronized DatabaseMenu getInstance() {
        if (single_instance == null)
            single_instance = new DatabaseMenu();
        return single_instance;
    }

    private void Options(){
        System.out.println("---------Database Manipulation Menu-----------");
        System.out.println("1) List the rows of a table");
        System.out.println("2) Insert a product");
        System.out.println("3) Get product by name from the database");
        System.out.println("4) Update a product");
        System.out.println("5) Delete a product");
        System.out.println("6) Delete information from all the tables");
        System.out.println("7) Drop all the tables");
    }

    private void LoadDatabase(){
        this.setUpData.dropAllTables();//sterg tabelele create inainte (daca exista)
        this.setUpData.createTables();
        this.setUpData.addRows();
    }

    private void insertProduct(){
        writing.WriteTimestamp("Insert a product in database");

        System.out.print("What type of product do you want to add \n" +
                "-> burger\n-> drink\n-> restaurant food\n-> sweet\n-> your choice is: ");

        String choice = this.var.nextLine();

        if(choice.equalsIgnoreCase("burger")){

            BurgerRepository burgerRepository = new BurgerRepository();
            Burger burger= new Burger();
            burger.reader();
            burgerRepository.insertBurger(burger);

        }else if(choice.equalsIgnoreCase("drink")){

            DrinkRepository drinkRepository = new DrinkRepository();
            Drink drink= new Drink();
            drink.reader();
            drinkRepository.insertDrink(drink);

        }else if(choice.equalsIgnoreCase("restaurant food")){

            RFoodRepository rFoodRepository= new RFoodRepository();
            RFood rfood= new RFood();
            rfood.reader();
            rFoodRepository.insertRFood(rfood);

        }else if(choice.equalsIgnoreCase("sweet")){

            SweetRepository sweetRepository= new SweetRepository();
            Sweet sweet= new Sweet();
            sweet.reader();
            sweetRepository.insertSweet(sweet);
        }
    }

    private void getProduct(){
        writing.WriteTimestamp("List a product from database");

        System.out.print("What type of product do you want to show \n" +
                "-> burger\n-> drink\n-> restaurant food\n-> sweet\n-> your choice is: ");

        String choice = this.var.nextLine();
        String name="";

        if(choice.equalsIgnoreCase("burger")){

            BurgerRepository burgerRepository = new BurgerRepository();
            System.out.print("Give the name of the burger: ");
            name=var.nextLine();
            Burger burger = burgerRepository.getBurgerByName(name);
            System.out.println(burger);

        }else if(choice.equalsIgnoreCase("drink")){

            DrinkRepository drinkRepository = new DrinkRepository();
            System.out.print("Give the name of the drink: ");
            name=var.nextLine();
            Drink drink= drinkRepository.getDrinkByName(name);
            System.out.println(drink);

        }else if(choice.equalsIgnoreCase("restaurant food")){

            RFoodRepository rFoodRepository= new RFoodRepository();
            System.out.print("Give the name of the meal: ");
            name=var.nextLine();
            RFood rfood= rFoodRepository.getRFoodByName(name);
            System.out.println(rfood);

        }else if(choice.equalsIgnoreCase("sweet")){

            SweetRepository sweetRepository= new SweetRepository();
            System.out.print("Give the name of the cake: ");
            name=var.nextLine();
            Sweet sweet= sweetRepository.getSweetByName(name);
            System.out.println(sweet);
        }
    }

    private void updateProduct(){
        writing.WriteTimestamp("Update a product from database");

        System.out.print("What type of product do you want to update \n" +
                "-> burger\n-> drink\n-> restaurant food\n-> sweet\n-> your choice is: ");

        String choice = this.var.nextLine();
        String name="";

        if(choice.equalsIgnoreCase("burger")){

            BurgerRepository burgerRepository = new BurgerRepository();
            System.out.print("Give the name of the burger: ");
            name=var.nextLine();
            System.out.print("Give the new ingredients of the burger: ");
            String ingredients = var.nextLine();
            burgerRepository.updateBurgerIngredients(name, ingredients);
            Burger burger = burgerRepository.getBurgerByName(name);
            System.out.println(burger);

        }else if(choice.equalsIgnoreCase("drink")){

            DrinkRepository drinkRepository = new DrinkRepository();
            System.out.println("Give the name of the drink: ");
            name=var.nextLine();
            System.out.print("Give the new price of the drink: ");
            double price = var.nextDouble();
            drinkRepository.updateDrinkPrice(name, price);
            Drink drink= drinkRepository.getDrinkByName(name);
            System.out.println(drink);

        }else if(choice.equalsIgnoreCase("restaurant food")){

            RFoodRepository rFoodRepository= new RFoodRepository();
            System.out.println("Give the name of the meal: ");
            name=var.nextLine();
            System.out.print("Give the new name of the meal: ");
            String new_name= var.nextLine();
            System.out.print("Give the new ingredients of the meal: ");
            String ingredients = var.nextLine();
            rFoodRepository.updateRFoodNameAndIngredients(name,new_name, ingredients);
            RFood rfood= rFoodRepository.getRFoodByName(name);
            System.out.println(rfood);

        }else if(choice.equalsIgnoreCase("sweet")){

            SweetRepository sweetRepository= new SweetRepository();
            System.out.println("Give the name of the cake: ");
            name=var.nextLine();
            System.out.print("Give the new number of calories of the cake: ");
            int calories = var.nextInt();
            sweetRepository.updateSweetCalories(name, calories);
            Sweet sweet= sweetRepository.getSweetByName(name);
            System.out.println(sweet);
        }
    }

    private void deleteProduct(){
        writing.WriteTimestamp("Delete a product from database");

        System.out.print("What type of product do you want to remove \n" +
                "-> burger\n-> drink\n-> restaurant food\n-> sweet\n-> your choice is: ");

        String choice = this.var.nextLine();
        String name="";

        if(choice.equalsIgnoreCase("burger")){

            BurgerRepository burgerRepository = new BurgerRepository();
            System.out.print("Give the name of the burger: ");
            name=var.nextLine();
            burgerRepository.deleteBurgerByName(name);

        }else if(choice.equalsIgnoreCase("drink")){

            DrinkRepository drinkRepository = new DrinkRepository();
            System.out.println("Give the name of the drink: ");
            name=var.nextLine();
            drinkRepository.deleteDrinkByName(name);

        }else if(choice.equalsIgnoreCase("restaurant food")){

            RFoodRepository rFoodRepository= new RFoodRepository();
            System.out.println("Give the name of the meal: ");
            name=var.nextLine();
            rFoodRepository.deleteRFoodByName(name);

        }else if(choice.equalsIgnoreCase("sweet")){

            SweetRepository sweetRepository= new SweetRepository();
            System.out.println("Give the name of the cake: ");
            name=var.nextLine();
            sweetRepository.deleteSweetByName(name);
        }
    }

    public void DatabaseService(){
        this.LoadDatabase();

        Scanner Var=new Scanner(System.in);

        while(true) {
            this.Options();
            System.out.print("Choose one action(1/2/3/4/5/6/7):");
            int option = Var.nextInt();
            if (option == 1) {
                //afisare un singur tabel
                this.writing.WriteTimestamp("Display a table from database");
                this.setUpData.displayTable();
            } else if (option == 2) {
                //inserare produs
                this.writing.WriteTimestamp("Insert a product into database");
                this.insertProduct();
            } else if (option == 3) {
                //afisare produs dupa nume
                this.writing.WriteTimestamp("Display a product from database");
                this.getProduct();
            } else if (option == 4) {
                //actualizare produs dupa nume
                this.writing.WriteTimestamp("Update a product from database");
                this.updateProduct();
            } else if (option == 5) {
                //stergere produs
                this.writing.WriteTimestamp("Delete a product from database");
                this.deleteProduct();
            }else if (option == 6){
                //stergere informatii tabele
                this.writing.WriteTimestamp("Delete all informations from database");
                this.setUpData.deleteAllRows();
            }else if (option == 7){
                //stergere tabele din baza de date
                this.writing.WriteTimestamp("Drop all tables from database");
                this.setUpData.dropAllTables();
                System.out.println("Drop the tables successfully!");

            }
            else {
                System.out.println("You write a wrong answer! Try again!");
                continue;
            }
            System.out.print("Do you want another action in database?(yes/no): ");

            Var.nextLine();
            String answer = Var.nextLine();

            if(!answer.equalsIgnoreCase("yes")) {
                break;
            }

        }
    }
}
