package com.example.foodloop;

public class RequestFoodItem {
    private String itemName;
    private String expiry;
    private String quantity;
    private String offerType;

    // Constructor, Getters, and Setters
    public RequestFoodItem(String itemName, String expiry, String quantity, String offerType) {
        this.itemName = itemName;
        this.expiry = expiry;
        this.quantity = quantity;
        this.offerType = offerType;
    }

    public String getItemName() {
        return itemName;
    }

    public String getExpiry() {
        return expiry;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getOfferType() {
        return offerType;
    }
}
