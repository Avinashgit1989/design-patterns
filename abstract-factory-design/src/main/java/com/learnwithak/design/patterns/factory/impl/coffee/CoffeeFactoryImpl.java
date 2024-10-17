package com.learnwithak.design.patterns.factory.impl.coffee;

import com.learnwithak.design.patterns.constant.FurnitureNameConstant;
import com.learnwithak.design.patterns.factory.FurnitureFactory;
import com.learnwithak.design.patterns.furniture.Furniture;
import com.learnwithak.design.patterns.furniture.impl.coffee.ArtDecoCoffeeTable;
import com.learnwithak.design.patterns.furniture.impl.coffee.ModernCoffeeTable;
import com.learnwithak.design.patterns.furniture.impl.coffee.VictorianCoffeeTable;

public class CoffeeFactoryImpl implements FurnitureFactory {
    @Override
    public Furniture createfurniture(String furnitureName) {
        Furniture furniture= null;
        if (furnitureName.equalsIgnoreCase(FurnitureNameConstant.ART_DECO)){
            furniture = new ArtDecoCoffeeTable();
        } else if (furnitureName.equalsIgnoreCase(FurnitureNameConstant.VICTORIAN)) {
            furniture = new VictorianCoffeeTable();
        } else if (furnitureName.equalsIgnoreCase(FurnitureNameConstant.MODERN)) {
            furniture = new ModernCoffeeTable();
        }
        return furniture;
    }
}
