package com.supplychain.models;

import com.supplychain.services.InventoryService;
import java.util.ArrayList;
import com.supplychain.services.ShipmentTrackerThread;
import com.supplychain.services.Trackable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class WarehouseManager extends User{
    private InventoryService inventoryService;
    private List<Supplier> suppliers;
    private int minOrderQty;
    private int MAX_QUANTITY;
    private List<Product> products;
    public HashMap<String, Integer> quantityMap; //code, quantity
    public WarehouseManager(List<Supplier> suppliers, int minOrderQty, List<Product> products){
        inventoryService=new InventoryService();
        this.suppliers=suppliers;
        this.products=products;
        this.minOrderQty=minOrderQty;
    }
    public List<Product> getProducts() {
        return products;
    }

    public void buyStock(Product product, int desiredQty) {
        String code = product.getCode();
        Integer current = quantityMap.get(code);
        if (current > minOrderQty) {
            System.out.println("Stock for " + code + " (" + current +
                    ") above threshold (" + minOrderQty + "). No reorder.");
            return;
        }

        Supplier bestSupplier = null;
        double bestCost = Double.MAX_VALUE;
        int bestQty = 0;

        for (Supplier s : suppliers) {
            int supplierMax = s.getMaxOrderQuantity(code);
            int orderQty = Math.min(desiredQty, supplierMax);
            double price = s.getPrice(code);
            double cost = price * orderQty;
            if (cost < bestCost) {
                bestCost = cost;
                bestSupplier = s;
                bestQty = orderQty;
            }
        }

        if (bestSupplier == null) {
            System.err.println("No supplier available for " + code);
            return;
        }
    }
    public void sellToRetailer(Product product, int qty, Retailer retailer) throws InsufficientStockException {
        String code = product.getCode();
        int currentQty = quantityMap.getOrDefault(code, 0);

        if (currentQty < qty) {
            throw new InsufficientStockException("Not enough stock for product: " + code);
        }

        quantityMap.put(code, currentQty - qty);
    }
    public static class InsufficientStockException extends Exception {
        public InsufficientStockException(String message) {
            super(message);
        }
    }
}
