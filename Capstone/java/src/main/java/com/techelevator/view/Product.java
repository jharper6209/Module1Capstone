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


/*    public void setProductName(String itemCode) throws FileNotFoundException {
        File vendingMachine = new File("./Capstone/vendingmachine.csv");
        Scanner invItems = new Scanner(vendingMachine);
        String aLine = "";
        while (invItems.hasNextLine()) {
            aLine = invItems.nextLine();
            String[] itemProperties = aLine.split("\\|");
            if (itemProperties[0].equals(itemCode)) {
                productName = itemProperties[1];
            }
        }
    }

    public void setProductType(String itemCode) throws FileNotFoundException {
        File vendingMachine = new File("./Capstone/vendingmachine.csv");
        Scanner invItems = new Scanner(vendingMachine);
        String aLine = "";
        while (invItems.hasNextLine()) {
            aLine = invItems.nextLine();
            String[] itemProperties = aLine.split("\\|");
            if (itemProperties[0].equals(itemCode)) {
                productType = itemProperties[3];
            }
        }
    }

        public void setProductNameAndType(String itemCode) throws FileNotFoundException {
            File vendingMachine = new File("./Capstone/vendingmachine.csv");
            Scanner invItems = new Scanner(vendingMachine);
            String aLine = "";
            while (invItems.hasNextLine()) {
                aLine = invItems.nextLine();
                String[] itemProperties = aLine.split("\\|");
                if (itemCode.equals(itemProperties[0])) {
                    this.productNameAndType.put(itemProperties[1], itemProperties[3]);
                }
            }
        }

         public Map displayProductNameAndType() throws FileNotFoundException {
             File vendingMachine = new File("./Capstone/vendingmachine.csv");
             Scanner invItems = new Scanner(vendingMachine);
             String aLine = "";
             while (invItems.hasNextLine()) {
                 aLine = invItems.nextLine();
                 String[] itemProperties = aLine.split("\\|");
                 productNameAndType.put(itemProperties[1], itemProperties[3]);
             }
             return productNameAndType;
         }

    public String setItemNoise() {
        if (productType.contains("Chip")) {
            return "Crunch Crunch, Yum!";
        } else if (productType.contains("Candy")) {
            return "Munch Munch, Yum!";
        } else if (productType.contains("Drink")) {
            return "Glug Glug, Yum!";
        } else if (productType.contains("Gum")) {
            return "Chew Chew, Yum!";
        }
            return "No Noise!";
    }
*/

