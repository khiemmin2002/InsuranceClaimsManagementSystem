package controllers;

import models.*;
import views.*;
import system.*;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PolicyHolderController {
    private final PolicyHolderView policyHolderView;
    private final PolicyHolderList policyHolderList;
    private final InsuranceList insuranceList;
    private final DependentList dependentList;

    public PolicyHolderController(PolicyHolderView policyHolderView, PolicyHolderList policyHolderList, InsuranceList insuranceList, DependentList dependentList) {
        this.policyHolderView = policyHolderView;
        this.policyHolderList = policyHolderList;
        this.insuranceList = insuranceList;
        this.dependentList = dependentList;
    }

    public void addPolicyHolder() throws IOException {
        policyHolderView.displayMessage("Adding a new policy holder...");
        String fullName = policyHolderView.promptForInput("Enter the full name of the policy holder: ");
        PolicyHolder newPolicyHolder = new PolicyHolder(fullName);
        policyHolderList.addPolicyHolder(newPolicyHolder);
        policyHolderView.displayMessage("Policy holder added successfully.");
    }

    public void displayAllPolicyHolders() {
        policyHolderView.displayMessage("\nDisplaying all policy holders (Sorted by ID):");

        List<PolicyHolder> sortedPolicyHolders = policyHolderList.getPolicyHolders().values().stream()
                .sorted(Comparator.comparing(PolicyHolder::getId))
                .toList();

        for (PolicyHolder policyHolder : sortedPolicyHolders) {
            List<Dependent> sortedDependents = dependentList.getDependentsForPolicyHolder(policyHolder.getId()).stream()
                    .sorted(Comparator.comparing(Dependent::getId))
                    .collect(Collectors.toList());

            // Call a method that handles the display of a policyholder and their dependents together
            policyHolderView.displayPolicyHolderWithDependents(policyHolder, sortedDependents);
        }
    }

    public void assignInsuranceCardToPolicyHolder() {
        String policyHolderId = policyHolderView.promptForInput("Enter the policyholder's ID: ");
        String cardNumber = policyHolderView.promptForInput("Enter the insurance card number: ");
        InsuranceCard insuranceCard = insuranceList.getInsuranceCard(cardNumber);

        if (insuranceCard != null) {
            PolicyHolder policyHolder = policyHolderList.getPolicyHolder(policyHolderId);
            if (policyHolder != null) {
                policyHolder.setInsuranceCard(insuranceCard);
                policyHolderList.updatePolicyHolder(policyHolder);
                policyHolderView.displayMessage("Insurance card assigned successfully.");
            } else {
                policyHolderView.displayMessage("Policyholder not found.");
            }
        } else {
            policyHolderView.displayMessage("Insurance card not found.");
        }
    }

    public void deletePolicyHolder() {
        String policyHolderId = policyHolderView.promptForInput("Enter the policyholder's ID to delete: ");
        if (policyHolderList.deletePolicyHolder(policyHolderId)) {
            policyHolderView.displayMessage("Policy holder deleted successfully.");
        } else {
            policyHolderView.displayMessage("Policy holder not found.");
        }
    }

    public void addDependentToPolicyHolder() {
        String policyHolderId = policyHolderView.promptForInput("Enter the policyholder's ID: ");
        PolicyHolder policyHolder = policyHolderList.getPolicyHolder(policyHolderId);
        if (policyHolder != null) {
            String fullName = policyHolderView.promptForInput("Enter the full name of the dependent: ");
            InsuranceCard insuranceCard = policyHolder.getInsuranceCard();
            Dependent newDependent = new Dependent(fullName, policyHolderId, insuranceCard);
            dependentList.addDependent(newDependent);
            policyHolderView.displayMessage("Dependent added successfully.");
        } else {
            policyHolderView.displayMessage("Policyholder not found.");
        }
    }

    // Delete a dependent from a policy holder
    public void deleteDependentFromPolicyHolder() {
        String dependentId = policyHolderView.promptForInput("Enter the dependent's ID to delete: ");
        Dependent dependent = dependentList.getDependent(dependentId);

        if (dependent != null) {
            // Remove the dependent from the dependent list
            boolean isDeleted = dependentList.deleteDependent(dependentId);

            if (isDeleted) {
                // Optionally, remove the dependent from the policyholder's list of dependents
                PolicyHolder policyHolder = policyHolderList.getPolicyHolder(dependent.getPolicyHolderID());
                if (policyHolder != null) {
                    policyHolder.removeDependent(dependent);
                    policyHolderList.updatePolicyHolder(policyHolder);
                }
                policyHolderView.displayMessage("Dependent deleted successfully.");
            } else {
                policyHolderView.displayMessage("Failed to delete the dependent.");
            }
        } else {
            policyHolderView.displayMessage("Dependent not found.");
        }
    }

}
