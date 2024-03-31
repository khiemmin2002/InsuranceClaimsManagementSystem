package system;

import models.InsuranceCard;
import models.PolicyHolder;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PolicyHolderList {
    private List<PolicyHolder> policyHolders;
    private String filePath;

    public PolicyHolderList(String filePath) {
        this.policyHolders = new ArrayList<>();
        this.filePath = filePath;
    }

    public List<PolicyHolder> getPolicyHolders() {
        return policyHolders;
    }

    public void loadFromFile() {
        File file = new File(filePath);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    PolicyHolder policyHolder = parseLineToPolicyHolder(line);
                    if (policyHolder != null) {
                        policyHolders.add(policyHolder);
                    }
                }
            } catch (IOException e) {
                System.err.println("An error occurred while loading insurance cards from the file: " + e.getMessage());
            }
        }
    }

    private PolicyHolder parseLineToPolicyHolder(String line) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String[] parts = line.split(",");

        if (parts.length == 4) {
            try {
                return new PolicyHolder(parts[0], parts[1]);
            } catch (Exception e) {
                System.err.println("Exception occurred: " + e.getMessage());
                return null;
            }
        }

        if (parts.length == 6) {
            try {
                Date expiredDate = dateFormat.parse(parts[5]);
                return new PolicyHolder(parts[0], parts[1], new InsuranceCard(parts[2], parts[3], parts[4], expiredDate));
            } catch (Exception e) {
                System.err.println("Exception occurred: " + e.getMessage());
                return null;
            }
        }
        return null;
    }

    // Get all policyholders
    public PolicyHolder getPolicyHolder(String customerID) {
        for (PolicyHolder policyHolder : policyHolders) {
            if (policyHolder.getId().equals(customerID)) {
                return policyHolder;
            }
        }
        return null;
    }

    // Updates the details of a specific policyholder
    public void updatePolicyHolder(String customerID, PolicyHolder updatedPolicyHolder) {
        for (int i = 0; i < policyHolders.size(); i++) {
            if (policyHolders.get(i).getId().equals(customerID)) {
                policyHolders.set(i, updatedPolicyHolder);
                saveToFile(); // Save changes back to the file
                break;
            }
        }
    }

    // Saves the current list of policyholders back to the file
    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (PolicyHolder policyHolder : policyHolders) {
                writer.write(policyHolderToFileFormat(policyHolder));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("An error occurred while saving policy holders to the file: " + e.getMessage());
        }
    }

    // Helper method to convert a policyholder object to a string format for saving to the file
    private String policyHolderToFileFormat(PolicyHolder policyHolder) {
        return policyHolder.getId() + "," + policyHolder.getFullName() + "," + policyHolder.getInsuranceCard();
        // Add other details as necessary
    }

    // Add a new policyholder to the list
    public void addPolicyHolder(PolicyHolder policyHolder) {
        policyHolders.add(policyHolder);
        saveToFile(); // Save the new list to the file
    }

}
