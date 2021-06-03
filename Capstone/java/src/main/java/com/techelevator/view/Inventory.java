package com.techelevator.view;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Inventory {


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




