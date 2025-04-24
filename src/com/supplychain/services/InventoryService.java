package com.supplychain.services;

import java.util.Map;
import java.util.HashMap;

public class InventoryService implements Trackable{
  private Map<String, Integer> stockLevels;
	 
	// Overloaded constructor 1: Default
	 public InventoryService() {
		 this.stockLevels = new HashMap<>();
	 }
	 
	 //Overloaded constructor 2 : provided with the map
	 public InventoryService(Map<String, Integer> initialStock) {
	        this.stockLevels = new HashMap<>(initialStock);
	 }
	 
	 public void addStock(String productCode, int qty) {
		stockLevels.put(productCode, stockLevels.getOrDefault(productCode, 0) + qty);
		System.out.println("Added " + qty + " units of " + productCode);
	}
	
	public void addStock(int[] qty, String... productCodes) {
		if (qty.length != productCodes.length) {
			throw new IllegalArgumentException("Quantities and product codes must be of the same length.");
		}
		
		for (int i = 0; i < qty.length; i++) {
			addStock(productCodes[i],qty[i]);
		}
	}
	
	public void removeStock(String productCode, Integer qty) {
		int currentQty = stockLevels.getOrDefault(productCode, 0);
		
		if (qty > currentQty) {
			throw new InsufficientStockException("Insufficient stock for " + productCode);
		}
		else {
			stockLevels.put(productCode, currentQty - qty);
			System.out.println("Removed " + qty + " units of " + productCode);
		}
	}
	
	//getter method for stockLevels
	public Map<String, Integer> getStockLevels() {
	    return this.stockLevels; 
	}
	
	
	
	public static class InsufficientStockException extends Exception {
        public InsufficientStockException(String message) {
            super(message);
        }
    }
}
