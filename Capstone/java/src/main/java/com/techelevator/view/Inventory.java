package com.techelevator.view;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Inventory {
    //      Slot # , Slot
    private Map<String, Slot> inventoryList;

    public Inventory() throws FileNotFoundException {
        this.inventoryList = new TreeMap();
        stockVendingMachineAtStart(); // run the method to load the inventory from the file
    }


    public void stockVendingMachineAtStart() throws FileNotFoundException {
        File vendingItems = new File("./Capstone/vendingmachine.csv");
        Scanner invItems = new Scanner(vendingItems);
        String aLine = "";

        while (invItems.hasNextLine()) {
            aLine = invItems.nextLine();
            String[] itemProperties = aLine.split("\\|");

            // Now that we have the data, we have to add it to the Map
            // The key for the map is slot # - itemProperties[0] - 1st element in the array after split
            // The value for map is Slot - we have to create the slot
            // To create Slot we need slot # and price - itemProperties[0] and itemProperties[2]
            Slot itemSlot = new Slot(itemProperties[0], Double.parseDouble(itemProperties[2]));

            // We need a product to add to the Stack in the Slot
            // we need to know the product name- itemProperties[1]
            // we need to know the product type- itemProperties[3]
            Product newProduct = new Product(itemProperties[1], itemProperties[3]);

            // now we can add the products to the slot
            for (int i = 0; i < 6; i++) {
                itemSlot.addProduct(newProduct);
            }

            //now we have everything we need to add to our map
            inventoryList.put(itemProperties[0], itemSlot); // add entry to the map using slot # and slot
            // created
        }
    }

    public Map<String, Slot> mapOfItems() {
        return inventoryList;
    }


}

