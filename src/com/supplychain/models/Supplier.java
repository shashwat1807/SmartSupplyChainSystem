package com.supplychain.models;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;


public class Supplier {
    private final String name;
    Random r= new Random();

    private List<Product> products;
    private final Map<String, Double> priceMap;           // productCode -> price
    private int  maxQuantity;
    private final int acceptanceTime=5000;
    private final int deliveryTime=7000;

    public Supplier(String name, int maxQuantity) {
        this.name = name;
        this.priceMap = new HashMap<>();
        this.maxQuantity = maxQuantity;
        this.products = new ArrayList<>();

    }

    public Supplier(String name, int maxQuantity, List<Product> products) {
        this.name = name;
        this.priceMap = new HashMap<>();
        this.maxQuantity = maxQuantity;
        this.products=products;
        for(Product p: this.products){
            setProductPricing(p.getCode(), r.nextInt(40), this.maxQuantity);
        }
    }


    public String getName() {
        return name;
    }

    public void setProductPricing(String productCode, double price, int maxQuantity) {
        priceMap.put(productCode, price);

    }

    public double getPrice(String productCode) {
        return priceMap.getOrDefault(productCode, Double.MAX_VALUE);
    }

    public int getMaxOrderQuantity(String productCode) {
        return maxQuantity;
    }

    public int getAcceptanceTime(){
        return this.acceptanceTime;
    }

    public int getDeliveryTime(){
        return this.deliveryTime;
    }
}
