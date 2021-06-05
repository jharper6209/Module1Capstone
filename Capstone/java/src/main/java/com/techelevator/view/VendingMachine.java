package com.techelevator.view;




import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
            if (vendingMachineInventory.mapOfItems().get(eachKey).returnCurrentNumberOfItems() != 1) {
                System.out.println(eachKey + " " + vendingMachineInventory.mapOfItems().get(eachKey).getTheProducts().peek().getProductName() + " " + vendingMachineInventory.mapOfItems().get(eachKey).getTheProducts().peek().getProductType() + " $" + vendingMachineInventory.mapOfItems().get(eachKey).getItemPrice(eachKey) + " Current Quantity: " + (vendingMachineInventory.mapOfItems().get(eachKey).returnCurrentNumberOfItems() - 1));
            } else {
                System.out.println("SOLD OUT");
            }
        }
    }

    public void purchaseVendingMachineProducts() {
    }

    public void feedMoney() throws IOException {
        BigDecimal startingBalance = balance;
        System.out.println("How much money ($)? (1, 2, 5, 10, 20, 50, or 100)");
        Scanner theKeyboard = new Scanner(System.in);
        String feedMoneyLine = theKeyboard.nextLine();
        if (feedMoneyLine.equals("1") || feedMoneyLine.equals("2") || feedMoneyLine.equals("5") || feedMoneyLine.equals("10") || feedMoneyLine.equals("20") || feedMoneyLine.equals("50") || feedMoneyLine.equals("100")) {
            balance = balance.add(BigDecimal.valueOf(Double.parseDouble(feedMoneyLine)));
        } else {
            System.out.println("Invalid amount, please specify again");
        }


        File theLogFile = new File("Log.txt");
        theLogFile.createNewFile();
        FileWriter aFileWriter = new FileWriter(theLogFile, true);
        BufferedWriter aBufferedWriter = new BufferedWriter(aFileWriter);
        PrintWriter diskFileWriter = new PrintWriter(aBufferedWriter);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");
        String now = LocalDateTime.now().format(formatter);

        diskFileWriter.println(now + " FEED MONEY: " + " $" + startingBalance + " $" + balance);

        diskFileWriter.close();
    }

    public void purchaseItem(String slotNumber) throws IOException {
        BigDecimal beginningBalance = balance;
        if (!vendingMachineInventory.mapOfItems().containsKey(slotNumber)) {
            System.out.println("Invalid selection, please try again!");
        } else if (balance.compareTo(vendingMachineInventory.mapOfItems().get(slotNumber).getItemPrice(slotNumber)) == -1) {
            System.out.println("Insufficient funds, please deposit more money!");
        } else if (vendingMachineInventory.mapOfItems().get(slotNumber).returnCurrentNumberOfItems() == 1) {
            System.out.println("SOLD OUT!");
        } else {

            BigDecimal updatedBalance = balance.subtract(vendingMachineInventory.mapOfItems().get(slotNumber).getItemPrice(slotNumber));

            System.out.println("\nThank you for your purchase!");
            System.out.println(vendingMachineInventory.mapOfItems().get(slotNumber).getTheProducts().peek().getProductName() + " $" + vendingMachineInventory.mapOfItems().get(slotNumber).getItemPrice(slotNumber) + " $" + updatedBalance);
            System.out.println(vendingMachineInventory.mapOfItems().get(slotNumber).dispenseProduct().getItemNoise());

            balance = updatedBalance;

            File theLogFile = new File("Log.txt");
            theLogFile.createNewFile();
            FileWriter aFileWriter = new FileWriter(theLogFile, true);
            BufferedWriter aBufferedWriter = new BufferedWriter(aFileWriter);
            PrintWriter diskFileWriter = new PrintWriter(aBufferedWriter);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");
            String now = LocalDateTime.now().format(formatter);

            diskFileWriter.println(now + " " + vendingMachineInventory.mapOfItems().get(slotNumber).getTheProducts().peek().getProductName() + " " + slotNumber + " $" + beginningBalance + " $" + updatedBalance);


            File theSalesFile = new File("Sales_Report.txt");
            FileWriter aSalesWriter = new FileWriter(theSalesFile, true);
            BufferedWriter aSalesBufferedWriter = new BufferedWriter(aSalesWriter);
            PrintWriter salesFileWriter = new PrintWriter(aSalesBufferedWriter);

            salesFileWriter.println(vendingMachineInventory.mapOfItems().get(slotNumber).getTheProducts().peek().getProductName() + "|" + (5 - (vendingMachineInventory.mapOfItems().get(slotNumber).returnCurrentNumberOfItems() - 1)) + "|" + vendingMachineInventory.mapOfItems().get(slotNumber).getItemPrice(slotNumber));


            salesFileWriter.close();
            diskFileWriter.close();

        }
    }

    public void completeTransaction() throws IOException {
        System.out.println("\nThank you for your purchase!");
        double remainderQuarter, remainderDime;
        double dbBalance = balance.doubleValue();

        int quartersToReturn = (int) (dbBalance / (0.25));
        remainderQuarter = dbBalance % 0.25;
        remainderQuarter = remainderQuarter * 100;
        remainderQuarter = Math.round(remainderQuarter);
        remainderQuarter = remainderQuarter / 100;

        int dimeToReturn = (int) (remainderQuarter / 0.10);
        remainderDime = remainderQuarter % 0.10;
        remainderDime = remainderDime * 100;
        remainderDime = Math.round(remainderDime);
        remainderDime = remainderDime / 100;

        int nickelToReturn = (int) (remainderDime / 0.05);

        balance = BigDecimal.valueOf(0.00);
        System.out.println("Current Balance is : $0.00");
        System.out.println("Your change is :" + quartersToReturn + " Quarter(s), " + dimeToReturn + " Dime(s), " + nickelToReturn + " Nickel(s) .");


        File theLogFile = new File("Log.txt");
        theLogFile.createNewFile();
        FileWriter aFileWriter = new FileWriter(theLogFile, true);
        BufferedWriter aBufferedWriter = new BufferedWriter(aFileWriter);
        PrintWriter diskFileWriter = new PrintWriter(aBufferedWriter);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");
        String now = LocalDateTime.now().format(formatter);

        diskFileWriter.println(now + " GIVE CHANGE: " + " " + dbBalance + " $0.00");

        diskFileWriter.close();
    }


    public void viewSalesReport() throws IOException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");
        String now = LocalDateTime.now().format(formatter);

        File theFile = new File("Sales_Report.txt");
        Scanner scannerForFile = new Scanner(theFile);

        String aLine = "";
        double totalSales = 0;
        if (theFile.exists()) {
            System.out.println("\nSales Report for " + now + "\n");
            while (scannerForFile.hasNextLine()) {
                aLine = scannerForFile.nextLine();
                String[] eachLine = aLine.split("\\|");

                System.out.println(eachLine[0] + "|" + eachLine[1]);
                double sales = Double.parseDouble(eachLine[1]) * Double.parseDouble(eachLine[2]);
                totalSales = totalSales + sales;
            }
            totalSales = totalSales * 100;
            totalSales = Math.round(totalSales);
            totalSales = totalSales / 100;
            System.out.println("Total Sales: " + " $" + totalSales);
        } else {
            System.out.println("There Is No Sales File.");
        }
    }

    public void clearSalesFile() throws IOException {
        File theFile = new File("Sales_Report.txt");
        if (theFile.exists() && theFile.isFile()) {
           theFile.delete();
        }
        theFile.createNewFile();
    }

}










