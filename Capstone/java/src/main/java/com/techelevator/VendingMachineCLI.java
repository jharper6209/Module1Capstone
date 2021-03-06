package com.techelevator;
/**************************************************************************************************************************
*  This is your Vending Machine Command Line Interface (CLI) class
*
*  It is the main process for the Vending Machine
*
*  THIS is where most, if not all, of your Vending Machine interactions should be coded
*  
*  It is instantiated and invoked from the VendingMachineApp (main() application)
*  
*  Your code vending machine related code should be placed in here
***************************************************************************************************************************/
import com.techelevator.view.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class VendingMachineCLI {


	VendingMachine myVendingMachine;




    // Main menu options defined as constants

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE      = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT          = "Exit";
	private static final String MAIN_MENU_SALES_REPORT		   = "Sales Report";

	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT      = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION          = "Finish Transaction";

	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY,
			PURCHASE_MENU_OPTION_SELECT_PRODUCT,
			PURCHASE_MENU_OPTION_FINISH_TRANSACTION};
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS,
													    MAIN_MENU_OPTION_PURCHASE,
													    MAIN_MENU_OPTION_EXIT, MAIN_MENU_SALES_REPORT
													    };


	private Menu vendingMenu;              // Menu object to be used by an instance of this class
	
	public VendingMachineCLI(Menu menu) throws IOException {  // Constructor - user will pass a menu for this class to use
		this.vendingMenu = menu;           // Make the Menu the user object passed, our Menu
		this.myVendingMachine = new VendingMachine();
		myVendingMachine.clearSalesFile();
	}
	/**************************************************************************************************************************
	*  VendingMachineCLI main processing loop
	*  
	*  Display the main menu and process option chosen
	*
	*  It is invoked from the VendingMachineApp program
	*
	*  THIS is where most, if not all, of your Vending Machine objects and interactions 
	*  should be coded
	*
	*  Methods should be defined following run() method and invoked from it
	*
	***************************************************************************************************************************/

	public void run() throws IOException {
		
		
		boolean shouldProcess = true;         // Loop control variable
		
		while(shouldProcess) {                // Loop until user indicates they want to exit
			
			String choice = (String)vendingMenu.getChoiceFromOptions(MAIN_MENU_OPTIONS);  // Display menu and get choice
			
			switch(choice) {                  // Process based on user menu choice
			
				case MAIN_MENU_OPTION_DISPLAY_ITEMS:

					displayItems();           // invoke method to display items in Vending Machine
					break;                    // Exit switch statement
			
				case MAIN_MENU_OPTION_PURCHASE:
					purchaseItems();          // invoke method to purchase items from Vending Machine

					boolean purchaseProcess = true;
					while (purchaseProcess) {

						System.out.println("Current Money Provided: $" + myVendingMachine.getBalance());
						String purchaseMenuChoiceFromOption = (String) vendingMenu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

						switch(purchaseMenuChoiceFromOption) {
							case PURCHASE_MENU_OPTION_FEED_MONEY:
								feedMoney();
								break;

							case PURCHASE_MENU_OPTION_SELECT_PRODUCT:
								selectProduct();
								break;

							case PURCHASE_MENU_OPTION_FINISH_TRANSACTION:
								finishTransaction();
								purchaseProcess = false;
								break;
						}
					}
					break;                    // Exit switch statement

				case MAIN_MENU_OPTION_EXIT:
					endMethodProcessing();    // Invoke method to perform end of method processing
					shouldProcess = false;    // Set variable to end loop
					break;                    // Exit switch statement

				case MAIN_MENU_SALES_REPORT:
					pullSalesReport();    // Invoke method to perform end of method processing
					break;
			}	
		}
		return;                               // End method and return to caller
	}
/********************************************************************************************************
 * Methods used to perform processing
 ********************************************************************************************************/
	public void displayItems() throws FileNotFoundException {      // static attribute used as method is not associated with specific object instance
		myVendingMachine.displayVendingMachineProducts();
	}
	
	public void purchaseItems() throws FileNotFoundException {	 // static attribute used as method is not associated with specific object instance
		// Code to purchase items from Vending Machine
		myVendingMachine.purchaseVendingMachineProducts();
	}
	
	public void endMethodProcessing() throws IOException { // static attribute used as method is not associated with specific object instance
		// Any processing that needs to be done before method ends
		myVendingMachine.viewSalesReport();
		System.out.println("\n$$$$$$$$$$$$$$$$");
		System.out.println("HAVE A NICE DAY!");
		System.out.println("$$$$$$$$$$$$$$$$");
	}

	public void feedMoney() throws IOException {      // static attribute used as method is not associated with specific object instance
		// Code to feed money
		myVendingMachine.feedMoney();
	}

	public void selectProduct() throws IOException {	 // static attribute used as method is not associated with specific object instance
		// Code to select product from Vending Machine inventory
		myVendingMachine.displayVendingMachineProducts();
		Scanner theKeyboard = new Scanner(System.in);
		String userInput = theKeyboard.nextLine();
		myVendingMachine.purchaseItem(userInput);
	}

	public void finishTransaction() throws IOException { // static attribute used as method is not associated with specific object instance
		myVendingMachine.completeTransaction();
	}

	public void pullSalesReport() throws IOException {
		myVendingMachine.viewSalesReport();
	}
}
