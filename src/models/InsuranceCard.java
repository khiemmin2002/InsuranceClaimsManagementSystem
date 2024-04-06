package models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.IntPredicate;

/**
 * RMIT University Vietnam - Assignment 1
 * @author <Min Chi Gia Khiem - S3878280>
 * @version 1.0
 * @since 04/05/2024
 */

public class InsuranceCard {
    private final String cardNum;
    private String cardHolder;
    private String policyOwner;
    private Date expiredDate;

    // Default constructor with default values
    public InsuranceCard() {
        cardNum = generateCardNum();
        cardHolder = "Default";
        policyOwner = "Default";
        expiredDate = null;
    }

    // Constructor used in InsuranceCardController when adding a new card
    public InsuranceCard(String cardHolder, String policyOwner, Date expiredDate) {
        this.cardNum = generateCardNum();
        this.cardHolder = cardHolder;
        this.policyOwner = policyOwner;
        this.expiredDate = expiredDate;
    }

    // Constructor for loading card from file (No new card number generation)
    public InsuranceCard(String cardNum, String cardHolder, String policyOwner, Date expiredDate) {
        this.cardNum = cardNum; // Use the existing card number
        this.cardHolder = cardHolder;
        this.policyOwner = policyOwner;
        this.expiredDate = expiredDate;
    }

    public String getCardNum() {
        return cardNum;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public String getPolicyOwner() {
        return policyOwner;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    // Set the card number whenever the card is created
    // The card number is 10 digits long, generate randomly, and unique (The num can start with 0)
    // This method now returns a String that represents the card number
    private String generateCardNum() {
        StringBuilder cardNumBuilder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            cardNumBuilder.append((int) (Math.random() * 10));
        }
        return cardNumBuilder.toString();
    }

    // Format the expired date to mm/dd/yyyy
    public String getFormattedExpiredDate() {
        if (expiredDate == null) {
            return "Date is not set";
        }
        return new SimpleDateFormat("MM/dd/yyyy").format(expiredDate);
    }

    // toString method to format the insurance card object to a string
    @Override
    public String toString() {
        // Format the expired date to mm/dd/yyyy
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String expiredDateString = expiredDate != null ? dateFormat.format(expiredDate) : "N/A";

        // Each attribute is separated by a comma
        return cardNum + "," + cardHolder + "," + policyOwner + "," + expiredDateString;
    }

}
