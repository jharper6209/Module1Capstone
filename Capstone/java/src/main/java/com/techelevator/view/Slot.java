package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class Slot {

    private Stack<Product> theProducts;
    private String slotNumber;
    private BigDecimal itemPrice;


    public Slot(String slotNumber, double itemPrice) {
        this.slotNumber = slotNumber;
        this.itemPrice = new BigDecimal(itemPrice);
        this.theProducts = new Stack();
    }

    public Stack<Product> getTheProducts() {
        return theProducts;
    }

    public String getSlotNumber() {
        return slotNumber;
    }

    public int returnCurrentNumberOfItems() {
        return theProducts.size();
    }

    public void addProduct(Product vendingMachineItem) {
        theProducts.push(vendingMachineItem);
}

    public Product dispenseProduct() {
        if (theProducts.empty()) {
            return null;
        }
        return theProducts.pop();      // returns a product from a Stack
}


    public BigDecimal getItemPrice(String slotNumber) throws FileNotFoundException {
        File vendingMachine = new File("./Capstone/vendingmachine.csv");
        Scanner invItems = new Scanner(vendingMachine);
        String aLine = "";
        while (invItems.hasNextLine()) {
            aLine = invItems.nextLine();
            String[] itemProperties = aLine.split("\\|");
            if (slotNumber.equals(itemProperties[0])) {
                itemPrice = BigDecimal.valueOf(Double.parseDouble(itemProperties[2]));
            }
        }
        return itemPrice;
    }

    public BigDecimal getTheItemPrice() {
        return itemPrice;
    }
}

