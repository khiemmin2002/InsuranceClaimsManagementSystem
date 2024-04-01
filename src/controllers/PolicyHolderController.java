package controllers;

import models.Customer;
import models.Dependent;
import models.InsuranceCard;
import models.PolicyHolder;
import views.PolicyHolderView;
import system.InsuranceList;
import system.PolicyHolderList;
import system.DependentList;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

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

    // Add a policyholder to the model
    public void addPolicyHolder() throws IOException {
        policyHolderView.displayMessage("Adding a new policy holder...");
        String fullName = policyHolderView.promptForInput("Enter the full name of the policy holder: ");

        PolicyHolder newPolicyHolder = new PolicyHolder(fullName);

        displayPolicyHolderDetails(newPolicyHolder);
        savePolicyHolderToFile(newPolicyHolder);
    }

    // Display the policyholder details
    public void displayPolicyHolderDetails(PolicyHolder policyHolder) {
        policyHolderView.displayPolicyHolderDetails(policyHolder);
    }

    public void savePolicyHolderToFile(PolicyHolder policyHolder) throws IOException {
        String fileName = "./data/PolicyHolder.txt";
        File dataFile = new File(fileName);

        // Check and create the data directory if it does not exist
        if (!dataFile.getParentFile().exists() && !dataFile.getParentFile().mkdirs()) {
            throw new IOException("Failed to create directory " + dataFile.getParentFile().getAbsolutePath());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile, true))) {
            writer.write(policyHolder.toString());
            writer.newLine(); // Add a newline after writing the insurance card details
            policyHolderView.displayMessage("Insurance card saved successfully to " + dataFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file.");
            policyHolderView.displayMessage("Failed to save the insurance card to " + dataFile.getAbsolutePath());
        }
    }

    // In PolicyHolderController.java


    // Display all policyholder
    public void displayAllPolicyHolders() {
        List<PolicyHolder> policyHolders = policyHolderList.getPolicyHolders();
        if (policyHolders.isEmpty()) {
            policyHolderView.displayMessage("No insurance cards available.");
        } else {
            for (PolicyHolder policyHolder : policyHolders) {
                displayPolicyHolderDetails(policyHolder);
            }
        }
    }

    // Assign an insurance card to a policyholder
    public void assignInsuranceCardToPolicyHolder() {
        String policyHolderId = policyHolderView.promptForInput("Enter the policyholder's ID: ");
        String cardNumber = policyHolderView.promptForInput("Enter the insurance card number: ");

        PolicyHolder policyHolder = policyHolderList.getPolicyHolder(policyHolderId);
        InsuranceCard insuranceCard = insuranceList.getInsuranceCard(cardNumber);

        if (policyHolder != null && insuranceCard != null) {
            policyHolder.setInsuranceCard(insuranceCard);
            policyHolderList.updatePolicyHolder(policyHolderId, policyHolder); // Assuming you have a method to update the policyholder in the list
            policyHolderView.displayMessage("Insurance card " + cardNumber + " assigned to policyholder " + policyHolderId);
        } else {
            if (policyHolder == null) {
                policyHolderView.displayMessage("Policyholder not found.");
            }
            if (insuranceCard == null) {
                policyHolderView.displayMessage("Insurance card not found.");
            }
        }
    }

    public void deletePolicyHolder() {
        String policyHolderId = policyHolderView.promptForInput("Enter the policyholder's ID to delete: ");
        boolean deleted = policyHolderList.deletePolicyHolder(policyHolderId);
        if (deleted) {
            policyHolderView.displayMessage("Policy holder deleted successfully.");
        } else {
            policyHolderView.displayMessage("Policy holder not found.");
        }
    }

    // Adding a dependent to a policyholder
    public void addDependentToPolicyHolder() {
        String policyHolderId = policyHolderView.promptForInput("Enter the policyholder's ID: ");
        PolicyHolder policyHolder = policyHolderList.getPolicyHolder(policyHolderId);

        if (policyHolder != null) {
            String fullName = policyHolderView.promptForInput("Enter the full name of the dependent: ");
            // Fetch the insurance card from the policyholder
            InsuranceCard insuranceCard = policyHolder.getInsuranceCard();

            // Create a new Dependent object with the insurance card
            Dependent newDependent = new Dependent(fullName, policyHolderId, insuranceCard);
            dependentList.addDependent(newDependent); // Assuming you have a list called dependentList for managing dependents

            policyHolderView.displayMessage("Dependent added successfully to policy holder with ID: " + policyHolderId);
        } else {
            policyHolderView.displayMessage("Policyholder not found.");
        }
    }


}
