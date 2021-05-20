package com.company;

import com.company.service.Aplication;

public class Main {

    public static void main(String[] args) {
        Aplication aplication = Aplication.getInstance();
        aplication.start();
    }

}
