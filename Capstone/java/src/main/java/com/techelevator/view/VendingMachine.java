package com.techelevator.view;




import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class VendingMachine {

    private Inventory vendingMachineInventory;
    private BigDecimal balance;


    public VendingMachine() throws FileNotFoundException {
        this.vendingMachineInventory = new Inventory();
        this.balance = new BigDecimal(0);
    }

    public BigDecimal getBalance() {
        return balance = balance.setScale(2, RoundingMode.CEILING);
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

    public BigDecimal feedMoney() {
        System.out.println("How much money? (1, 2, 5, 10, 20, 50, or 100)");
        Scanner theKeyboard = new Scanner(System.in);
        String feedMoneyLine = theKeyboard.nextLine();
        return balance = balance.add(BigDecimal.valueOf(Double.parseDouble(feedMoneyLine)));
    }

    public void purchaseItem(String slotNumber) throws FileNotFoundException {
        if (!vendingMachineInventory.mapOfItems().containsKey(slotNumber)) {
            System.out.println("Invalid selection, please try again!");
        } else if (balance.compareTo(vendingMachineInventory.mapOfItems().get(slotNumber).getItemPrice(slotNumber)) == -1) {
            System.out.println("Insufficient funds, please deposit more money!");
        } else if (vendingMachineInventory.mapOfItems().get(slotNumber).returnCurrentNumberOfItems() == 0) {
            System.out.println("SOLD OUT!");
        } else {

            BigDecimal updatedBalance = balance.subtract(vendingMachineInventory.mapOfItems().get(slotNumber).getItemPrice(slotNumber));

            System.out.println("\nThank you for your purchase!\n");
            System.out.println(vendingMachineInventory.mapOfItems().get(slotNumber).getTheProducts().peek().getProductName() + " " + vendingMachineInventory.mapOfItems().get(slotNumber).getItemPrice(slotNumber) + " " + updatedBalance);
            System.out.println(vendingMachineInventory.mapOfItems().get(slotNumber).dispenseProduct().getItemNoise());

            balance = updatedBalance;
        }
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
