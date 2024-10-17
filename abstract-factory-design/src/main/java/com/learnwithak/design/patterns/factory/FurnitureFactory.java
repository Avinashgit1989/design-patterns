package com.learnwithak.design.patterns.factory;

import com.learnwithak.design.patterns.furniture.Furniture;

public interface FurnitureFactory {
    Furniture createfurniture(String furnitureName);
}
