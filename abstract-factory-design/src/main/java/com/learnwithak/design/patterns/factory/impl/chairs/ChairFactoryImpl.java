package com.learnwithak.design.patterns.factory.impl.chairs;

import com.learnwithak.design.patterns.constant.FurnitureNameConstant;
import com.learnwithak.design.patterns.factory.FurnitureFactory;
import com.learnwithak.design.patterns.furniture.Furniture;
import com.learnwithak.design.patterns.furniture.impl.chair.ArtDecoChair;
import com.learnwithak.design.patterns.furniture.impl.chair.ModernChair;
import com.learnwithak.design.patterns.furniture.impl.chair.Victorian;

public class ChairFactoryImpl implements FurnitureFactory {
    @Override
    public Furniture createfurniture(String furnitureName) {
        Furniture furniture= null;
        if (furnitureName.equalsIgnoreCase(FurnitureNameConstant.ART_DECO)){
            furniture = new ArtDecoChair();
        } else if (furnitureName.equalsIgnoreCase(FurnitureNameConstant.VICTORIAN)) {
            furniture = new Victorian();
        } else if (furnitureName.equalsIgnoreCase(FurnitureNameConstant.MODERN)) {
            furniture = new ModernChair();
        }
        return furniture;
    }
}
