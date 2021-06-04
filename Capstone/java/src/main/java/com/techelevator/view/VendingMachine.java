package com.techelevator.view;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {

    private Inventory vendingMachineInventory;
    private double balance;

    public VendingMachine() throws FileNotFoundException {
        this.vendingMachineInventory = new Inventory();
        this.balance = 0;
    }


    public double getBalance() {
        return balance;
    }
}








/*
    private boolean outOfStock(String[] product) {
        return (product.length == 0);
    }

    public Map<String, String[]> getItemsInMachine() {
        return itemsInMachine;
    }

    public void displayItems() throws FileNotFoundException {
        File vendingItems = new File("./Capstone/vendingmachine.csv");
        Scanner invItems = new Scanner(vendingItems);
        String aLine = "";
        while (invItems.hasNextLine()) {
            aLine = invItems.nextLine();
            String[] itemProperties = aLine.split("\\|");

            if (vendingMachineInventory.returnCurrentInventory(itemProperties[0]) == 0) {
                System.out.println("SOLD OUT!");
            } else {
                System.out.println(aLine + " \nRemaining Quantity" + vendingMachineInventory.returnCurrentInventory(itemProperties[0]));
            }
        }
    }
}


