package views;

import models.*;
import controllers.*;

import java.util.Scanner;

/**
 * RMIT University Vietnam - Assignment 1
 * @author <Min Chi Gia Khiem - S3878280>
 * @version 1.0
 * @since 04/05/2024
 */

public class ClaimView {

        public static void displayClaimDetails(Claim claim) {
            System.out.println("-------------------------------------------------------------");
            System.out.println("Claim Details:");
            System.out.println("Claim ID: " + claim.getClaimID());
            System.out.println("Claim Date: " + claim.getFormattedClaimDate());
            System.out.println("Insured Person: " + claim.getInsuredPerson());
            System.out.println("Card Number: " + claim.getCardNum());
            System.out.println("Exam Date: " + claim.getFormattedExamDate());
            System.out.println("List of Documents: ");
            for (int i = 0; i < claim.getListOfDocuments().size(); i++) {
                System.out.println("\tDocument #" + (i + 1) + ": " + claim.getListOfDocuments().get(i));
            }
            System.out.println("Claim Amount: " + claim.getClaimAmount());
            System.out.println("Claim Status: " + claim.getClaimStatus());
            System.out.println("Bank Name: " + claim.getBankName());
            System.out.println("Bank Account Name: " + claim.getBankAccountName());
            System.out.println("Account Number: " + claim.getAccountNumber());
            System.out.println("-------------------------------------------------------------\n");
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
