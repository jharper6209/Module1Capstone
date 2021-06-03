package com.techelevator.view;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class SelectProduct {

    private PrintWriter out;
    private Scanner in;
    private String itemCodes = "A1A2A3A4B1B2B3B4C1C2C3C4D1D2D3D4";
    private double updatedMoney = 0;
    private int newQuantity;
//    private Map<String, Integer> newQuantity = new LinkedHashMap();

    public SelectProduct(InputStream input, OutputStream output) throws FileNotFoundException {
        this.out = new PrintWriter(output);
        this.in = new Scanner(input);
    }

    public void selectProduct(double currentMoney) throws FileNotFoundException {

        Inventory selectInventory = new Inventory(System.in, System.out);
        selectInventory.getInventory();

        System.out.println("\nPlease enter code to select an item.");
        Scanner theKeyboard = new Scanner(System.in);
        String inputLine = "";
        inputLine = theKeyboard.nextLine();

        File vendingItems = new File("./Capstone/vendingmachine.csv");
        Scanner invItems = new Scanner(vendingItems);
        String aLine = "";

//        newQuantity.putAll(selectInventory.getQuantityItem());


        if (!itemCodes.contains(inputLine)) {
            System.out.println("Invalid code. Please try again!");
            return ;
        } else {
            while (invItems.hasNextLine()) {
                aLine = invItems.nextLine();
                String[] itemProperties = aLine.split("\\|");


                    if (itemProperties[0].contains(inputLine) && itemProperties[3].equals("Chip")) {

                        System.out.println(itemProperties[1] + " Item Cost: $" + itemProperties[2] + " Current Money: $" + (currentMoney - Double.parseDouble(itemProperties[2])) + "\nCrunch Crunch, Yum!");
                        updatedMoney = currentMoney - Double.parseDouble(itemProperties[2]);
                    }
                    if (itemProperties[0].contains(inputLine) && itemProperties[3].equals("Candy")) {
                        System.out.println(itemProperties[1] + " Item Cost: $" + itemProperties[2] + " Current Money: $" + (currentMoney - Double.parseDouble(itemProperties[2])) + "\nMunch Munch, Yum!");
                        updatedMoney = currentMoney-Double.parseDouble(itemProperties[2]);
                    }

                    if (itemProperties[0].contains(inputLine) && itemProperties[3].equals("Drink")) {
                        System.out.println(itemProperties[1] + " Item Cost: $" + itemProperties[2] + " Current Money: $" + (currentMoney - Double.parseDouble(itemProperties[2])) + "\nGlug Glug, Yum!");
                        updatedMoney = currentMoney-Double.parseDouble(itemProperties[2]);
                    }

                    if (itemProperties[0].contains(inputLine) && itemProperties[3].equals("Gum")) {
                        System.out.println(itemProperties[1] + " Item Cost: $" + itemProperties[2] + " Current Money: $" + (currentMoney - Double.parseDouble(itemProperties[2])) + "\nChew Chew, Yum!");
                        updatedMoney = currentMoney - Double.parseDouble(itemProperties[2]);

                    }
                    newQuantity = selectInventory.getQuantity() - 1;
//                newQuantity.put(inputLine, newQuantity.get(inputLine)-1);
                }

            }

        }

//    public Map<String, Integer> getNewQuantity() {
//        return newQuantity;
//    }

    public double getAmount () {
        return updatedMoney;
    }

    public int getNewQuantity() {
        return newQuantity;
    }

}
