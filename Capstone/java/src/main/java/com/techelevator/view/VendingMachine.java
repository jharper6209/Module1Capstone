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

        diskFileWriter.println(now + " FEED MONEY:" + " $" + startingBalance + " $" + balance);

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


            File theSalesFile = new File("Tracker.txt");
            FileWriter aSalesWriter = new FileWriter(theSalesFile, true);
            BufferedWriter aSalesBufferedWriter = new BufferedWriter(aSalesWriter);
            PrintWriter salesFileWriter = new PrintWriter(aSalesBufferedWriter);

            salesFileWriter.println(vendingMachineInventory.mapOfItems().get(slotNumber).getTheProducts().peek().getProductName());


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

        File theLogFile = new File("Log.txt");
        theLogFile.createNewFile();
        FileWriter aFileWriter = new FileWriter(theLogFile, true);
        BufferedWriter aBufferedWriter = new BufferedWriter(aFileWriter);
        PrintWriter diskFileWriter = new PrintWriter(aBufferedWriter);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");
        String now = LocalDateTime.now().format(formatter);

        diskFileWriter.println(now + " GIVE CHANGE: " + "$" + balance.setScale(2, RoundingMode.CEILING) + " $0.00");


        balance = BigDecimal.valueOf(0.00);
        System.out.println("Current Balance is : $0.00");
        System.out.println("Your change is :" + quartersToReturn + " Quarter(s), " + dimeToReturn + " Dime(s), " + nickelToReturn + " Nickel(s) .");

        diskFileWriter.close();
    }


    public void viewSalesReport() throws IOException {

        System.out.println("\nPlease open Sales_Report.txt to view Sales Report");

        File theSalesFile = new File("Sales_Report.txt");
        FileWriter aSalesWriter = new FileWriter(theSalesFile, true);
        BufferedWriter aSalesBufferedWriter = new BufferedWriter(aSalesWriter);
        PrintWriter salesFileWriter = new PrintWriter(aSalesBufferedWriter);

        File theTrackerFile = new File("Tracker.txt");
        Scanner trackerForFile = new Scanner(theTrackerFile);
        String anItem = "";
        int counter1 = 0;
        int counter2 = 0;
        int counter3 = 0;
        int counter4 = 0;
        int counter5 = 0;
        int counter6 = 0;
        int counter7 = 0;
        int counter8 = 0;
        int counter9 = 0;
        int counter10 = 0;
        int counter11 = 0;
        int counter12 = 0;
        int counter13 = 0;
        int counter14 = 0;
        int counter15 = 0;
        int counter16 = 0;

        while (trackerForFile.hasNextLine()) {
            anItem = trackerForFile.nextLine();
            if (anItem.equals("Potato Crisps")) {
                counter1++;
            } else if (anItem.equals("Stackers")) {
                counter2++;
            } else if (anItem.equals("Grain Waves")) {
                counter3++;
            } else if (anItem.equals("Cloud Popcorn")) {
                counter4++;
            } else if (anItem.equals("Moonpie")) {
                counter5++;
            } else if (anItem.equals("Cowtales")) {
                counter6++;
            } else if (anItem.equals("Wonka Bar")) {
                counter7++;
            } else if (anItem.equals("Crunchie")) {
                counter8++;
            } else if (anItem.equals("Cola")) {
                counter9++;
            } else if (anItem.equals("Dr. Salt")) {
                counter10++;
            } else if (anItem.equals("Mountain Melter")) {
                counter11++;
            } else if (anItem.equals("Heavy")) {
                counter12++;
            } else if (anItem.equals("U-Chews")) {
                counter13++;
            } else if (anItem.equals("Little League")) {
                counter14++;
            } else if (anItem.equals("Chiclets")) {
                counter15++;
            } else if (anItem.equals("Triplemint")) {
                counter16++;
            }
        }
        salesFileWriter.println("Potato Crisps|" + counter1);
        salesFileWriter.println("Stackers|" + counter2);
        salesFileWriter.println("Grain Waves|" + counter3);
        salesFileWriter.println("Cloud Popcorn|" + counter4);
        salesFileWriter.println("Moonpie|" + counter5);
        salesFileWriter.println("Cowtales|" + counter6);
        salesFileWriter.println("Wonka Bar|" + counter7);
        salesFileWriter.println("Crunchie|" + counter8);
        salesFileWriter.println("Cola|" + counter9);
        salesFileWriter.println("Dr. Salt|" + counter10);
        salesFileWriter.println("Mountain Melter|" + counter11);
        salesFileWriter.println("Heavy|" + counter12);
        salesFileWriter.println("U-Chews|" + counter13);
        salesFileWriter.println("Little League|" + counter14);
        salesFileWriter.println("Chiclets|" + counter15);
        salesFileWriter.println("Triplemint|" + counter16);

        double itemPrice1 = 3.05 * counter1;
        double itemPrice2 = 1.45 * counter2;
        double itemPrice3 = 2.75 * counter3;
        double itemPrice4 = 3.65 * counter4;
        double itemPrice5 = 1.80 * counter5;
        double itemPrice6 = 1.50 * counter6;
        double itemPrice7 = 1.50 * counter7;
        double itemPrice8 = 1.75 * counter8;
        double itemPrice9 = 1.25 * counter9;
        double itemPrice10 = 1.50 * counter10;
        double itemPrice11 = 1.50 * counter11;
        double itemPrice12 = 1.50 * counter12;
        double itemPrice13 = 0.85 * counter13;
        double itemPrice14 = 0.95 * counter14;
        double itemPrice15 = 0.75 * counter15;
        double itemPrice16 = 0.75 * counter16;

        double totalPrice = itemPrice1 + itemPrice2 + itemPrice3 + itemPrice4 + itemPrice5 + itemPrice6 + itemPrice7 + itemPrice8 + itemPrice9 + itemPrice10 + itemPrice11 + itemPrice12 + itemPrice13 + itemPrice14 + itemPrice15 + itemPrice16;


        BigDecimal totalPriceBD = BigDecimal.valueOf(totalPrice);
        totalPriceBD = totalPriceBD.setScale(2, RoundingMode.CEILING);


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");
        String now = LocalDateTime.now().format(formatter);

        salesFileWriter.println("\nSales On: " + now);
        salesFileWriter.println("Total Sales: $" + totalPriceBD);

        salesFileWriter.close();
    }

    public void clearSalesFile() throws IOException {

        File theFile = new File("Sales_Report.txt");
        if (theFile.exists() && theFile.isFile()) {
            theFile.delete();
        }
        theFile.createNewFile();

        File theTrackerFile = new File("Tracker.txt");
        if (theTrackerFile.exists() && theTrackerFile.isFile()) {
            theTrackerFile.delete();
        }
        theTrackerFile.createNewFile();

}

}










