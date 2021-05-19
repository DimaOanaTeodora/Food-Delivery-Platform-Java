package com.company;

import com.company.service.Aplication;

public class Main {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {

        //TODO: Cand fac un menu trebuie sa scad numarul din stock-uri
        //TODO: de adaugat posibilitatea de a adauga un rating la un shop
        //TODO: de regandit lista de bauturi de la Restaurant
        //TODO: de scazut stock-ul la plasarea unei comenzi
        //TODO: de regandit clasele: User, Owner, DelieveryBoy
        //TODO: eventual de adaugat si o metoda pentru afisarea comenzilor
        Aplication aplication = Aplication.getInstance();
        aplication.start();

    }


}
