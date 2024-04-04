package views;

import models.*;
import controllers.*;

import java.util.Scanner;

public class ClaimView {

        public void displayClaimDetails(Claim claim) {
            System.out.println("Claim ID: " + claim.getClaimID());
            System.out.println("Claim Date: " + claim.getClaimDate());
            System.out.println("Insured Person: " + claim.getInsuredPerson());
            System.out.println("Card Number: " + claim.getCardNum());
            System.out.println("Exam Date: " + claim.getExamDate());
            System.out.println("List of Documents: " + claim.getListOfDocuments());
            System.out.println("Claim Amount: " + claim.getClaimAmount());
            System.out.println("Claim Status: " + claim.getClaimStatus());
            System.out.println("Bank Name: " + claim.getBankName());
            System.out.println("Bank Account Name: " + claim.getBankAccountName());
            System.out.println("Account Number: " + claim.getAccountNumber());
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
