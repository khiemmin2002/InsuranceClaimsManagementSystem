package views;

import models.*;

import java.util.List;
import java.util.Scanner;

import static views.ClaimView.displayClaimDetails;

/**
 * RMIT University Vietnam - Assignment 1
 * @author <Min Chi Gia Khiem - S3878280>
 * @version 1.0
 * @since 04/05/2024
 */

public class PolicyHolderView {
    // Method to display the details of a policyholder
    public void displayPolicyHolderDetails(PolicyHolder policyHolder) {
        System.out.println("Policy Holder Details:");
        System.out.println("Customer ID: " + policyHolder.getId());
        System.out.println("Customer Full Name: " + policyHolder.getFullName());
        System.out.println("\nInsurance Card: " + getInsuranceCardDetails(policyHolder));
    }

    // Method to get the details of an insurance card when getting from the policyholder
    private String getInsuranceCardDetails(PolicyHolder policyHolder) {
        if (policyHolder.getInsuranceCard() == null) {
            return "No insurance card";
        } else {
            return "\nCard Number: " + policyHolder.getInsuranceCard().getCardNum() +
                    "\nCard Holder: " + policyHolder.getInsuranceCard().getCardHolder() +
                    "\nPolicy Owner: " + policyHolder.getInsuranceCard().getPolicyOwner() +
                    "\nExpired Date: " + policyHolder.getInsuranceCard().getFormattedExpiredDate();
        }
    }

    // Method to display the dependents of a policyholder
    public void displayDependents(List<Dependent> dependents) {
        if (!dependents.isEmpty()) {
            System.out.println("\nDependents:");
            for (Dependent dependent : dependents) {
                System.out.println("\t-----------------------------------");
                displayDependentDetails(dependent);
                System.out.println("\t-----------------------------------");
            }
        } else {
            System.out.println("\nNo dependents");
        }
    }

    // Method to display the details of a dependent
    public void displayDependentDetails(Dependent dependent) {
        System.out.println("\tCustomer ID: " + dependent.getId());
        System.out.println("\tCustomer Full Name: " + dependent.getFullName());
        System.out.println("\tPolicy Holder ID: " + dependent.getPolicyHolderID());
        if (dependent.getInsuranceCard() != null) {
            System.out.println("\tCard Number: " + dependent.getInsuranceCard().getCardNum());
        } else {
            System.out.println("\tNo insurance card");
        }
    }

    // Method to display the claims of a policyholder
    public void displayClaims(List<Claim> claims) {
        if (!claims.isEmpty()) {
            System.out.println("\nClaims:");
            for (Claim claim : claims) {
                displayClaimDetails(claim);
            }
        } else {
            System.out.println("\nNo claims\n");
        }
    }

    // Method to display messages to the user
    public void displayMessage(String message) {
        System.out.println(message);
    }

    // Method to prompt for input and return the input
    public String promptForInput(String prompt) {
        System.out.print(prompt);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    // Method to display the policyholder with dependents and claims
    public void displayPolicyHolderWithDependentsClaims(PolicyHolder policyHolder, List<Dependent> dependents, List<Claim> claims) {
        System.out.println("\n******************************************************");
        displayPolicyHolderDetails(policyHolder);
        displayDependents(dependents);
        displayClaims(claims);
        System.out.println("******************************************************\n");
    }
}
