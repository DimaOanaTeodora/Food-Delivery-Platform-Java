package com.company;

import com.company.config.DataSetup;
import com.company.product.Burger;
import com.company.repository.BurgerRepository;

public class Main {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {

        //TODO: Cand fac un menu trebuie sa scad numarul din stock-uri
        //TODO: de adaugat posibilitatea de a adauga un rating la un shop
        //TODO: de regandit lista de bauturi de la Restaurant
        //TODO: de scazut stock-ul la plasarea unei comenzi
        //TODO: de regandit clasele: User, Owner, DelieveryBoy
        //TODO: eventual de adaugat si o metoda pentru afisarea comenzilor
        //Aplication aplication = Aplication.getInstance();
        //aplication.start();
        ///////////////////////////////////////////
        DataSetup setUpData = new DataSetup();

        setUpData.createTables();
        setUpData.deleteAllRows();//evit duplicatele
        setUpData.addRows();
        setUpData.displayTable();
        BurgerRepository burgerRepository = new BurgerRepository();
        Burger burger = burgerRepository.getBurgerByName("Veggie");
        System.out.println(burger);
        burgerRepository.updateBurgerIngredients("Veggie", "doar paine");
        burger = burgerRepository.getBurgerByName("Veggie");
        System.out.println(burger);
        Burger burger1= new Burger();
        //burger.reader();
        //burgerRepository.insertBurger(burger);
        burgerRepository.deleteBurgerByName("Veggie");
        setUpData.displayTable();
        //setUpData.dropAllTables();



        /*setUpData.addUser(); //TODO de revenit
        setUpData.displayUser();

        UserRepository userRepository = new UserRepository();
        User user = userRepository.getUserByName("Dan Ionel");

        System.out.println("Name = " + user.getName());

        userRepository.updateUserEmail( "ionel_dan@gmail.com","Dan Ionel");
        User updatedUser = userRepository.getUserByName("Dan Ionel");

        System.out.println("Email = " + updatedUser.getEmail());

        userRepository.insertUser(new User("FLoricica Dansatoarea", "flori@gmail.com","0720903333", "floricica"));
    */
    }


}
