package com.example.parreplenishment.service;

import com.example.parreplenishment.model.Item;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReplenishmentService {
    private final String parLocator = "STA.B10.AISLE-1";
    private final Map<String, Item> items = new HashMap<>();

    public ReplenishmentService() {
        items.put("Suture Kit - Ethicon", new Item("Suture Kit - Ethicon", 50));
        items.put("Disposable Surgical Gloves - Size 7.5", new Item("Disposable Surgical Gloves - Size 7.5", 100));
        items.put("Sterile gloves", new Item("Sterile gloves", 120));
        items.put("IV Cannulas", new Item("IV Cannulas", 200));
        items.put("Surgical clamps", new Item("Surgical clamps", 70));
        items.put("Disposable syringe with needle 50cc", new Item("Disposable syringe with needle 50cc", 150));
    }

    public String identifyLocator(String content) {
        // Mock parsing logic
        return parLocator;
    }

    public List<Item> getItemsForLocator(String locator) {
        return locator.equals(parLocator) ? new ArrayList<>(items.values()) : Collections.emptyList();
    }

    public List<Map<String, Object>> getOnHand(List<String> itemNames) {
        return itemNames.stream()
                .map(name -> {
                    Item item = items.get(name);
                    Map<String, Object> map = new HashMap<>();
                    map.put("item", item.getName());
                    map.put("onHand", item.getOnHand());
                    map.put("parLevel", item.getParLevel());
                    map.put("replenishmentQty", item.getReplenishmentQty());
                    return map;
                })
                .collect(Collectors.toList());
    }

    public Map<String, Object> createReplenishment(String item, int qty) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Replenishment created");
        response.put("item", item);
        response.put("qty", qty);
        response.put("requisitionNumber", String.format("%05d", new Random().nextInt(100000)));
        return response;
    }
}
