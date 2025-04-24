package com.supplychain.services;

public interface Trackable{
  //@param productCode is the unique identifier of the product
	void trackInventory(String productCode);
	
	//param shipmentId is the unique identifier of the shipment
	void trackShipment(String shipmentId);
}
