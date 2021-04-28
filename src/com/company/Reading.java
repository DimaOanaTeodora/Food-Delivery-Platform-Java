package com.company;

import com.company.Shop.Shop;

import java.util.HashMap;

public class Reading {//singleton
    private static Reading single_instance = null;
    private Reading() { }
    public static Reading getInstance()
    {
        if (single_instance == null)
            single_instance = new Reading();
        return single_instance;
    }
    private HashMap<Integer, Shop> shops=new HashMap<Integer, Shop>();
    private HashMap<Integer, Order> orders=new HashMap<Integer, Order>();
    private int shopId;
    private int orderId;
    public static void read_Menus(){

    }
}
