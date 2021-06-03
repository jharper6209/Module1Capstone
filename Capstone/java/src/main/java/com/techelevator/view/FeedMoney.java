package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class FeedMoney {

    private PrintWriter out;
    private Scanner in;

    public FeedMoney(InputStream input, OutputStream output) {
        this.out = new PrintWriter(output);
        this.in = new Scanner(input);
    }

    public int feedMoney() {
        System.out.println("How much money? (1, 2, 5, 10, or 20)");
        Scanner theKeyboard = new Scanner(System.in);
        int fedMoney = 0;
        String feedMoneyLine = theKeyboard.nextLine();
        return fedMoney = Integer.parseInt(feedMoneyLine);
    }

}
