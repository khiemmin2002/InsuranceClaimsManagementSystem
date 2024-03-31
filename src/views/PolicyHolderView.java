package views;

import models.PolicyHolder;

import java.util.Scanner;

public class PolicyHolderView {
    public void displayPolicyHolderDetails(PolicyHolder policyHolder) {
        System.out.println("\n-----------------------------------");
        System.out.println("Policy Holder Details:");
        System.out.println("Customer ID: " + policyHolder.getId());
        System.out.println("Customer Full Name: " + policyHolder.getFullName());

        // Check for Insurance Card
        String insuranceCardInfo = policyHolder.getInsuranceCard() != null ?
                "\nCard Number: " + policyHolder.getInsuranceCard().getCardNum() +
                "\nCard Holder: " + policyHolder.getInsuranceCard().getCardHolder() +
                "\nPolicy Owner: " + policyHolder.getInsuranceCard().getPolicyOwner() +
                "\nExpired Date: " + policyHolder.getInsuranceCard().getFormattedExpiredDate()
                : "No insurance card";
        System.out.println("\nInsurance Card: " + insuranceCardInfo);

        // Check for Dependents
        String dependentsInfo = !policyHolder.getDependents().isEmpty() ?
                "Number of Dependents: " + policyHolder.getDependents().size() : "No dependents";
        System.out.println("\nDependents: " + dependentsInfo);

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
