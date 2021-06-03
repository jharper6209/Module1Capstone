package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Inventory {

    private final int INITIAL_QUANTITY = 5;

    public Inventory() throws FileNotFoundException {

        File vendingItems = new File("./Capstone/vendingmachine.csv");
        Scanner invItems = new Scanner(vendingItems);
        String aLine = "";

        int remainingQuantity = INITIAL_QUANTITY;

        while (invItems.hasNextLine()) {

            aLine = invItems.nextLine();

            System.out.println("Item: " + aLine + " \nRemaining Quantity: " + remainingQuantity);
        }
    }
}
