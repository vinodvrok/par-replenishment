package com.example.parreplenishment.service;

import com.example.parreplenishment.model.Item;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReplenishmentService {
    private final String parLocator = "STA.B10.AISLE-1";
    private final String subinventory = "Storage Room A";
    private final Map<String, Item> items = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    public ReplenishmentService() {
        items.put("Suture Kit - Ethicon", new Item("Suture Kit - Ethicon", 50, subinventory));
        items.put("Disposable Surgical Gloves - Size 7.5", new Item("Disposable Surgical Gloves - Size 7.5", 100,subinventory));
        items.put("Sterile gloves", new Item("Sterile gloves", 120,subinventory));
        items.put("IV Cannulas", new Item("IV Cannulas", 200,subinventory));
        items.put("Surgical clamps", new Item("Surgical clamps", 70,subinventory));
        items.put("Disposable syringe with needle 50cc", new Item("Disposable syringe with needle 50cc", 150,subinventory));
    }

    public String identifyLocator(String content) {
        // Mock parsing logic
        return parLocator;
    }

    public List<Item> getItemsForLocator(String locator) {
        return locator.equals(parLocator) ? new ArrayList<>(items.values()) : Collections.emptyList();
    }
    
    public List<Item> getItemsForLocator(String locator, String itemName) {
        if (!locator.equals(parLocator)) return Collections.emptyList();

        if (itemName == null || itemName.trim().isEmpty()) {
            return new ArrayList<>(items.values());
        }

        Item item = items.get(itemName); // TreeMap handles case-insensitivity
        return item != null ? List.of(item) : Collections.emptyList();
    }

    public List<Map<String, Object>> getOnHand(List<String> itemNames) {
        return itemNames.stream()
                .map(name -> {
                    Item item = items.get(name);
                    Map<String, Object> map = new HashMap<>();
                    map.put("item", item.getName());
                    map.put("onHand", item.getOnHand());
                    map.put("Subinventory", item.getSubinventory());
                    map.put("Locator",parLocator);
                    //map.put("replenishmentQty", item.getReplenishmentQty());
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
