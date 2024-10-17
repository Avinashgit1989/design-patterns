package com.learnwithak.design.patterns.factory.impl.sofa;

import com.learnwithak.design.patterns.constant.FurnitureNameConstant;
import com.learnwithak.design.patterns.factory.FurnitureFactory;
import com.learnwithak.design.patterns.furniture.Furniture;
import com.learnwithak.design.patterns.furniture.impl.sofa.ArtDecoSofa;
import com.learnwithak.design.patterns.furniture.impl.sofa.ModernSofa;
import com.learnwithak.design.patterns.furniture.impl.sofa.VictorianSofa;

public class SofaFactoryImpl implements FurnitureFactory {
    @Override
    public Furniture createfurniture(String sofaName) {
        Furniture furniture= null;
        if (sofaName.equalsIgnoreCase(FurnitureNameConstant.ART_DECO)){
            furniture = new ArtDecoSofa();
        } else if (sofaName.equalsIgnoreCase(FurnitureNameConstant.VICTORIAN)) {
            furniture = new VictorianSofa();
        } else if (sofaName.equalsIgnoreCase(FurnitureNameConstant.MODERN)) {
            furniture = new ModernSofa();
        }
        return furniture;
    }
}
