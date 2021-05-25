package com.company.service;

import com.company.shop.*;

import java.io.*;

public class WriterCSV {//singleton

    private static WriterCSV single_instance = null;
    private BufferedWriter buffer;

    private WriterCSV() {}

    public static synchronized WriterCSV getInstance() {
        if (single_instance == null)
            single_instance = new WriterCSV();
        return single_instance;
    }

    public <T> void writeShop(Object shop, Class<T> classOf, String path) {

        try {
            buffer = new BufferedWriter(new FileWriter(path, true));

            //sterge continut dinaintea pornirii programului
            new FileWriter(path, false).close();
            if (classOf.toString().equals("class com.company.shop.CakeShop")) {
                System.out.println("You choose to write a CakeShop");
                buffer.write(((CakeShop)shop).toString());
            }
            else if (classOf.toString().equals("class com.company.shop.FastFood")) {
                System.out.println("You choose to write a FastFood");
                buffer.write(((FastFood)shop).toString());
            }
            else if (classOf.toString().equals("class com.company.shop.Restaurant")){
                System.out.println("You choose to write a Restaurant");
                buffer.write(((Restaurant)shop).toString());
            }

            buffer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}