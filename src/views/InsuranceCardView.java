package views;

import java.util.Scanner;

/**
 * RMIT University Vietnam - Assignment 1
 * @author <Min Chi Gia Khiem - S3878280>
 * @version 1.0
 * @since 04/05/2024
 */

public class InsuranceCardView {
    // Method to display the details of an insurance card
    public void displayInsuranceCardDetails(String cardNumber, String cardHolder, String policyOwner, String expiredDate) {
        System.out.println("\n***********************************");
        System.out.println("Insurance Card Details:");
        System.out.println("Card Number: " + cardNumber);
        System.out.println("Card Holder: " + cardHolder);
        System.out.println("Policy Owner: " + policyOwner);
        System.out.println("Expired Date (mm/dd/yyyy): " + expiredDate);
        System.out.println("***********************************\n");
    }

    // Method to display messages to the user...
    public void displayMessage(String message) {
        System.out.println(message);
    }

    // Method to prompt for input and return the input...
    public String promptForInput(String prompt) {
        System.out.print(prompt);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
