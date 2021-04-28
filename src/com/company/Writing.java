package com.company;

import com.company.Shop.Shop;

import java.util.HashMap;

public class Writing {//singleton
    private static Writing single_instance = null;
    private Writing() { }
    public static Writing getInstance()
    {
        if (single_instance == null)
            single_instance = new Writing();
        return single_instance;
    }
    private HashMap<Integer, Shop> shops=new HashMap<Integer, Shop>();
    private HashMap<Integer, Order> orders=new HashMap<Integer, Order>();
    private int shopId;
    private int orderId;

}
