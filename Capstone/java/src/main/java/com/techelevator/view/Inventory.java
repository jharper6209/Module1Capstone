package com.techelevator.view;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Inventory {


    private PrintWriter out;
    private Scanner in;
    private final int INITIAL_QUANTITY = 5;
    private Map<String, Integer> quantityItem = new HashMap<>();

    public Inventory(InputStream input, OutputStream output) {
        this.out = new PrintWriter(output);
        this.in = new Scanner(input);
    }

    public void getInventory() throws FileNotFoundException {

        ;

        File vendingItems = new File("./Capstone/vendingmachine.csv");
            Scanner invItems = new Scanner(vendingItems);
            String aLine = "";

            while (invItems.hasNextLine()) {
                aLine = invItems.nextLine();
                String[] itemProperties = aLine.split("\\|");
                quantityItem.put(itemProperties[0], INITIAL_QUANTITY);

                System.out.println(aLine + " \nRemaining Quantity: " + quantityItem.get(itemProperties[0]));
            }
        }

    public Map<String, Integer> getQuantityItem() {
        return quantityItem;
    }
}



