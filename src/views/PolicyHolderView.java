package views;

import models.Dependent;
import models.PolicyHolder;

import java.util.List;
import java.util.Scanner;

public class PolicyHolderView {

    public void displayPolicyHolderDetails(PolicyHolder policyHolder) {
        System.out.println("\n********************************************");
        System.out.println("Policy Holder Details:");
        System.out.println("Customer ID: " + policyHolder.getId());
        System.out.println("Customer Full Name: " + policyHolder.getFullName());
        System.out.println("Insurance Card: " + getInsuranceCardDetails(policyHolder));
        System.out.println("-----------------------------------\n");
    }

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

    public void displayDependents(List<Dependent> dependents) {
        if (!dependents.isEmpty()) {
            System.out.println("Dependents:");
            for (Dependent dependent : dependents) {
                System.out.println("\t-----------------------------------");
                displayDependentDetails(dependent);
                System.out.println("\t-----------------------------------");
            }
        } else {
            System.out.println("No dependents");
        }
        System.out.println("********************************************");
    }

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

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public String promptForInput(String prompt) {
        System.out.print(prompt);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void displayPolicyHolderWithDependents(PolicyHolder policyHolder, List<Dependent> dependents) {
        displayPolicyHolderDetails(policyHolder);
        displayDependents(dependents);
    }
}
