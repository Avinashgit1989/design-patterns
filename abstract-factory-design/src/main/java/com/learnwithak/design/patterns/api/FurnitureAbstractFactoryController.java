package com.learnwithak.design.patterns.api;

import com.learnwithak.design.patterns.factory.FurnitureFactory;
import com.learnwithak.design.patterns.furniture.Furniture;
import com.learnwithak.design.patterns.producer.FurnitureFactoryProducer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class FurnitureAbstractFactoryController {
    @GetMapping("/furniture")
    public String getFurniture(@RequestParam String type, @RequestParam String furnitureName) {
        FurnitureFactory furnitureFactory = FurnitureFactoryProducer.getFactory(type);

        if (furnitureFactory == null) {
            return "Invalid furniture type.";
        }
        Furniture furniture = furnitureFactory.createfurniture(furnitureName);
        if (furniture != null){
            return furniture.getType();
        }else {
            return "Invalid furniture.";

        }
    }
}

