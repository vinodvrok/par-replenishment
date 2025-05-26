package com.example.parreplenishment.model;

public class Item {
    private String name;
    private int parLevel;
    private int onHand;
    private String subinventory;

    public Item(String name, int parLevel, String subinventory) {
        this.name = name;
        this.parLevel = parLevel;
        this.onHand = (int)(Math.random() * parLevel); // mock on-hand
        this.subinventory = subinventory;
    }

    public String getName() { return name; }
    public int getParLevel() { return parLevel; }
    public String getSubinventory() { return subinventory; }
    public int getOnHand() { return onHand; }
    public int getReplenishmentQty() { return parLevel - onHand; }
}