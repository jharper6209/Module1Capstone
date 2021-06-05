package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Product {


    private String productName;
    private String productType;
    private String itemNoise;

    public Product(String name, String type) {
        this.productName = name;
        this.productType = type;
        getItemNoise();
    }

    @Override
    public String toString() {
        return productName + " " + productType + " " + itemNoise;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductType() {
        return productType;
    }

    public String getItemNoise() {
        itemNoise = "ERROR";
        if (productType.equals("Chip")) {
            itemNoise = "Crunch Crunch, Yum!";
        } else if (productType.equals("Candy")) {
            itemNoise = "Munch Munch, Yum!";
        } else if (productType.equals("Drink")) {
            itemNoise = "Glug Glug, Yum!";
        } else if (productType.equals("Gum")) {
            itemNoise = "Chew Chew, Yum!";
        }
        return itemNoise;
    }
}

