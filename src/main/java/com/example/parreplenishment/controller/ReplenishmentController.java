package com.example.parreplenishment.controller;

import com.example.parreplenishment.model.Item;
import com.example.parreplenishment.service.ReplenishmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

@RestController
@RequestMapping("/api/replenishment")
public class ReplenishmentController {

    @Autowired
    private ReplenishmentService service;

    @PostMapping(value = "/getReplenishmentsforPARLocator", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Item> getItems(@RequestBody Map<String, Object> request) throws Exception {
        String locatorText = (String) request.get("locatorText");
        String itemName = (String) request.get("item"); // Can be null
        String locator = service.identifyLocator(locatorText);  // Optional if already clean
        return service.getItemsForLocator(locator,itemName);
    }

    @PostMapping("/getOnHandForItems")
    public List<Map<String, Object>> getOnHand(@RequestBody Map<String, Object> request) {
        String locator = (String) request.get("locator");
        List<String> items = (List<String>) request.get("items");
        return service.getOnHand(items);
    }

  /*  @PostMapping("/createReplenishment")
    public Map<String, Object> createReplenishment(@RequestBody Map<String, Object> request) {
        String item = (String) request.get("item");
        int qty = (Integer) request.get("replenishmentQty");
        return service.createReplenishment(item, qty);
    }*/
    
    @PostMapping("/createOrder")
    public Map<String, Object> createOrder(@RequestBody Map<String, Object> request) {
        String item = (String) request.get("item");
        int qty = (Integer) request.get("replenishmentQty");
        return service.createOrder(item, qty);
    }
    
    @GetMapping("/getScheduledSurgeries")
    public Map<String, Object> getScheduledSurgeries() {
        return service.getScheduledSurgeries();
    }
}

