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
            Product newProduct = new Product(itemProperties[1],itemProperties[3]);

            // now we can add the products to the slot
            for( int i =0; i < 5; i++) {
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




















   /* public void stockVendingMachineAtStart() throws FileNotFoundException {
        File vendingItems = new File("./Capstone/vendingmachine.csv");
        Scanner invItems = new Scanner(vendingItems);
        String aLine = "";
        while (invItems.hasNextLine()) {
            aLine = invItems.nextLine();
            String[] itemProperties = aLine.split("\\|");
            inventoryList.put(itemProperties[0], 5);
        }
    }

    public void subtractFromMachineStock(String itemCode) {
        inventoryList.put(itemCode, inventoryList.get(itemCode) - 1);
    }

    public int returnCurrentInventory(String itemCode) {
        return inventoryList.get(itemCode);
    }

    public Map<String, String[]> itemInMachine() throws FileNotFoundException {
        String[] itemNameAndPrice;
        Map<String, String[]> itemNamePrice = new HashMap<>();
        File vendingItems = new File("./Capstone/vendingmachine.csv");
        Scanner invItems = new Scanner(vendingItems);
        String aLine = "";
        while (invItems.hasNextLine()) {
            aLine = invItems.nextLine();
            String[] itemProperties = aLine.split("\\|");

            itemNameAndPrice = new String[]{itemProperties[1], itemProperties[2]};
            itemNamePrice.put(itemProperties[0], itemNameAndPrice);
        }
            return itemNamePrice;
    }

}
/*
    private PrintWriter out;
    private Scanner in;
    //private Map<String, Integer> quantityItem = new LinkedHashMap<>();
    private static int INITIAL_QUANTITY = 5;
    private int quantity;

    public Inventory(InputStream input, OutputStream output) {
        this.out = new PrintWriter(output);
        this.in = new Scanner(input);
    }

    //public Map<String, Integer> getQuantityItem() {
    //    return quantityItem;
  //  }

  //  public void setQuantityItem(Map<String, Integer> quantityItem) {
 //       this.quantityItem = quantityItem;
//      }

    public void getInventory() throws FileNotFoundException {

        SelectProduct myProduct = new SelectProduct(System.in, System.out);

        quantity = INITIAL_QUANTITY;
        File vendingItems = new File("./Capstone/vendingmachine.csv");
        Scanner invItems = new Scanner(vendingItems);
        String aLine = "";

        while (invItems.hasNextLine()) {
            aLine = invItems.nextLine();
            String[] itemProperties = aLine.split("\\|");
            //quantityItem.put(itemProperties[0], INITIAL_QUANTITY);
            //System.out.println(quantityItem);


            // When the item has been purchased, then Map.put(itemProperties[0], map.get(itemProperties[0] - 1)
            //quantityItem.putAll(myProduct.getNewQuantity());
            if (quantity > 0) {
//                quantityItem.putAll(myProduct.getNewQuantity());
                System.out.println(aLine + " \nRemaining Quantity: " + quantity);
            } else {
                System.out.println("SOLD OUT");
            }
        }
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
*/



