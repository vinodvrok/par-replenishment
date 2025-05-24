package com.example.parreplenishment.model;

public class Item {
    private String name;
    private int parLevel;
    private int onHand;

    public Item(String name, int parLevel) {
        this.name = name;
        this.parLevel = parLevel;
        this.onHand = (int)(Math.random() * parLevel); // mock on-hand
    }

    public String getName() { return name; }
    public int getParLevel() { return parLevel; }
    public int getOnHand() { return onHand; }
    public int getReplenishmentQty() { return parLevel - onHand; }
}