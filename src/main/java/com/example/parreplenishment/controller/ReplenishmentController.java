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

    @PostMapping(value = "/getReplenishmentsforPARLocator", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<Item> getItems(@RequestParam("file") MultipartFile file) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String locator = service.identifyLocator(sb.toString());
        return service.getItemsForLocator(locator);
    }

    @PostMapping("/getOnHandForItems")
    public List<Map<String, Object>> getOnHand(@RequestBody Map<String, Object> request) {
        String locator = (String) request.get("locator");
        List<String> items = (List<String>) request.get("items");
        return service.getOnHand(items);
    }

    @PostMapping("/createReplenishment")
    public Map<String, Object> createReplenishment(@RequestBody Map<String, Object> request) {
        String item = (String) request.get("item");
        int qty = (Integer) request.get("replenishmentQty");
        return service.createReplenishment(item, qty);
    }
}

