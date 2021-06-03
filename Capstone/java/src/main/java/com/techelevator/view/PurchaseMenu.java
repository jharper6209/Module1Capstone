package com.techelevator.view;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class PurchaseMenu {

    private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
    private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT      = "Select Product";
    private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION          = "Finish Transaction";
    private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY,
            PURCHASE_MENU_OPTION_SELECT_PRODUCT,
            PURCHASE_MENU_OPTION_FINISH_TRANSACTION};

    private static int currentMoney;

    private Menu purchaseMenu;

    public PurchaseMenu(Menu menu) {  // Constructor - user will pass a menu for this class to use
        this.purchaseMenu = menu;           // Make the Menu the user object passed, our Menu
    }
    public void run() throws FileNotFoundException {

        System.out.println("Current Money Provided: $" + currentMoney);

        boolean shouldProcess = true;         // Loop control variable

        while(shouldProcess) {                // Loop until user indicates they want to exit

            String choice = (String)purchaseMenu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);  // Display menu and get choice

            switch(choice) {                  // Process based on user menu choice

                case PURCHASE_MENU_OPTION_FEED_MONEY:
                    feedMoney();           // invoke method to display items in Vending Machine

                    break;                    // Exit switch statement

                case PURCHASE_MENU_OPTION_SELECT_PRODUCT:
                    selectProduct();          // invoke method to purchase items from Vending Machine
                    break;                    // Exit switch statement

                case PURCHASE_MENU_OPTION_FINISH_TRANSACTION:
                    finishTransaction();    // Invoke method to perform end of method processing
                    shouldProcess = false;    // Set variable to end loop
                    break;                    // Exit switch statement
            }
        }
        return;                               // End method and return to caller
    }

    public void feedMoney() {      // static attribute used as method is not associated with specific object instance
       // Code to feed money
        FeedMoney fedMoney = new FeedMoney();

    }

    public void selectProduct() {	 // static attribute used as method is not associated with specific object instance
        // Code to select product from Vending Machine inventory
    }

    public void finishTransaction() { // static attribute used as method is not associated with specific object instance
        // Any processing that needs to be done before method ends
    }


}