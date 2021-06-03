package com.techelevator.view;

import java.util.Scanner;

public class FeedMoney {

    public FeedMoney() {
        System.out.println("How much money? (1, 2, 5, 10, or 20)");
        Scanner theKeyboard = new Scanner(System.in);
        int fedMoney = 0;
        String feedMoneyLine = theKeyboard.nextLine();
        fedMoney = Integer.parseInt(feedMoneyLine);
    }

    
}
