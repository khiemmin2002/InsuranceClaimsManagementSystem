package models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.IntPredicate;

public class InsuranceCard {
    private final String cardNum;
    private String cardHolder;
    private String policyOwner;
    private Date expiredDate;

    public InsuranceCard() {
        cardNum = generateCardNum();
        cardHolder = "Default";
        policyOwner = "Default";
        expiredDate = null;
    }

    public InsuranceCard(String cardHolder, String policyOwner, Date expiredDate) {
        this.cardNum = generateCardNum();
        this.cardHolder = cardHolder;
        this.policyOwner = policyOwner;
        this.expiredDate = expiredDate;
    }

    // Constructor for loading card from file (no new card number generation)
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

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public String getPolicyOwner() {
        return policyOwner;
    }

    public void setPolicyOwner(String policyOwner) {
        this.policyOwner = policyOwner;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
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

    @Override
    public String toString() {
        // Format the expired date to mm/dd/yyyy
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String expiredDateString = expiredDate != null ? dateFormat.format(expiredDate) : "N/A";

        // Each attribute is separated by a comma
        return cardNum + "," + cardHolder + "," + policyOwner + "," + expiredDateString;
    }

}
