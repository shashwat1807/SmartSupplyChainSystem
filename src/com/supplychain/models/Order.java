package com.supplychain.models;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;
import java.util.Random;

public class Order {
    Supplier supplier;
    WarehouseManager warehouseManager;
    Random r=new Random();
    private final String orderID = String.valueOf(r.nextInt(51407 - 32104 + 1) + 32104);
    String orderStatus;
    public Order(Supplier supplier, WarehouseManager warehouseManager) throws InterruptedException, IOException {
        this.supplier = supplier;
        this.warehouseManager = warehouseManager;

    }
    public void process() throws InterruptedException {
        this.orderStatus = "In Progress";  // Set to "In Progress" first


        int acceptanceTime = supplier.getAcceptanceTime();
        System.out.println("[Order] Sleep for acceptanceTime: " + acceptanceTime);
        Thread.sleep(acceptanceTime);  // Sleep for acceptanceTime
        this.orderStatus = "In Transit";  // Update to "In Transit"


        int deliveryTime = supplier.getDeliveryTime();
        System.out.println("[Order] Sleep for deliveryTime: " + deliveryTime);
        Thread.sleep(deliveryTime);  // Sleep for deliveryTime
        this.orderStatus = "Delivered";  // Update to "Delivered"

    }
    public String getOrderID() {
        return this.orderID;
    }

    public String getOrderStatus() {
        return this.orderStatus;
    }
}
