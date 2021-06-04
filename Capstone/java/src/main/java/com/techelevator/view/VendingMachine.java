package com.techelevator.view;




import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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


    public void displayVendingMachineProducts() throws FileNotFoundException {

        Set<String> theKeys = vendingMachineInventory.mapOfItems().keySet();

        for (String eachKey : theKeys) {
            if (vendingMachineInventory.mapOfItems().get(eachKey).returnCurrentNumberOfItems() != 0) {
                System.out.println(eachKey + " " + vendingMachineInventory.mapOfItems().get(eachKey).getTheProducts().peek().getProductName() + " " + vendingMachineInventory.mapOfItems().get(eachKey).getTheProducts().peek().getProductType() + " " + vendingMachineInventory.mapOfItems().get(eachKey).getItemPrice(eachKey) + " Current Quantity: " + vendingMachineInventory.mapOfItems().get(eachKey).returnCurrentNumberOfItems());
            } else {
                System.out.println("SOLD OUT");


            }
        }
    }

    public void purchaseVendingMachineProducts() {
    }


    public int feedMoney() {
        System.out.println("How much money? (1, 2, 5, 10, 20, 50, or 100)");
        Scanner theKeyboard = new Scanner(System.in);
        int fedMoney = 0;
        String feedMoneyLine = theKeyboard.nextLine();
        return fedMoney = Integer.parseInt(feedMoneyLine);
    }

    public int currentMoney() {
        int currentMoney = 0;
        return currentMoney;
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

*/
