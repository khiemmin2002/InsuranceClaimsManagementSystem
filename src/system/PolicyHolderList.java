package system;

import models.InsuranceCard;
import models.PolicyHolder;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PolicyHolderList {
    private Map<String, PolicyHolder> policyHolders = new HashMap<>();
    private String filePath;

    public PolicyHolderList(String filePath) {
        this.filePath = filePath;
    }

    public Map<String, PolicyHolder> getPolicyHolders() {
        return new HashMap<>(policyHolders);
    }

    public void loadFromFile() {
        File file = new File(filePath);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    PolicyHolder policyHolder = parseLineToPolicyHolder(line);
                    if (policyHolder != null) {
                        policyHolders.put(policyHolder.getId(), policyHolder);
                    }
                }
            } catch (IOException e) {
                System.err.println("An error occurred while loading policy holders from the file: " + e.getMessage());
            }
        }
    }

    private PolicyHolder parseLineToPolicyHolder(String line) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String[] parts = line.split(",");

        try {
            if (parts.length >= 3) {
                String id = parts[0];
                String name = parts[1];
                InsuranceCard insuranceCard = null;
                if (parts.length == 6) {
                    Date expiredDate = dateFormat.parse(parts[5]);
                    insuranceCard = new InsuranceCard(parts[2], parts[3], parts[4], expiredDate);
                }
                return new PolicyHolder(id, name, insuranceCard);
            }
        } catch (Exception e) {
            System.err.println("Exception occurred: " + e.getMessage());
        }
        return null;
    }

    public PolicyHolder getPolicyHolder(String customerID) {
        return policyHolders.get(customerID);
    }

    public void updatePolicyHolder(PolicyHolder updatedPolicyHolder) {
        policyHolders.put(updatedPolicyHolder.getId(), updatedPolicyHolder);
        saveToFile();
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (PolicyHolder policyHolder : policyHolders.values()) {
                writer.write(policyHolderToFileFormat(policyHolder));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("An error occurred while saving policy holders to the file: " + e.getMessage());
        }
    }

    private String policyHolderToFileFormat(PolicyHolder policyHolder) {
        // Assume InsuranceCard is nullable and properly handled in PolicyHolder's toString method
        return policyHolder.toString();
    }

    public void addPolicyHolder(PolicyHolder policyHolder) {
        policyHolders.put(policyHolder.getId(), policyHolder);
        saveToFile();
    }

    public boolean deletePolicyHolder(String customerID) {
        if (policyHolders.containsKey(customerID)) {
            policyHolders.remove(customerID);
            saveToFile(); // This saves the change to the file immediately
            return true;
        }
        return false;
    }
}
