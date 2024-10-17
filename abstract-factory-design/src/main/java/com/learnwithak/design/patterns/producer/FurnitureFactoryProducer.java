package com.learnwithak.design.patterns.producer;

import com.learnwithak.design.patterns.FurnitureType;
import com.learnwithak.design.patterns.factory.FurnitureFactory;
import com.learnwithak.design.patterns.factory.impl.chairs.ChairFactoryImpl;
import com.learnwithak.design.patterns.factory.impl.coffee.CoffeeFactoryImpl;
import com.learnwithak.design.patterns.factory.impl.sofa.SofaFactoryImpl;

public class FurnitureFactoryProducer {
    public static FurnitureFactory getFactory(String furnitureType) {
        if (furnitureType.equalsIgnoreCase(FurnitureType.CHAIR.name())) {
            return new ChairFactoryImpl();
        } else if (FurnitureType.SOFA.name().equalsIgnoreCase(furnitureType)) {
            return new SofaFactoryImpl();
        }else if (FurnitureType.COFFEE.name().equalsIgnoreCase(furnitureType)) {
            return new CoffeeFactoryImpl();
        }
        return null;
    }
}
