package com.techelevator.view;

import java.io.*;
import java.util.Scanner;

public class Inventory {


    private PrintWriter out;
    private Scanner in;
    private final int INITIAL_QUANTITY = 5;

    public Inventory(InputStream input, OutputStream output) {
        this.out = new PrintWriter(output);
        this.in = new Scanner(input);
    }

    public void getInventory() throws FileNotFoundException {

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

