package views;

import models.*;
import controllers.*;

import java.util.Scanner;

public class ClaimView {

        public void displayClaimDetails(String claimNumber, String claimantName, String claimType, String claimAmount, String claimDate) {
            System.out.println("\n-----------------------------------");
            System.out.println("Claim Details:");
            System.out.println("Claim Number: " + claimNumber);
            System.out.println("Claimant Name: " + claimantName);
            System.out.println("Claim Type: " + claimType);
            System.out.println("Claim Amount: " + claimAmount);
            System.out.println("Claim Date (mm/dd/yyyy): " + claimDate);
            System.out.println("-----------------------------------\n");
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
