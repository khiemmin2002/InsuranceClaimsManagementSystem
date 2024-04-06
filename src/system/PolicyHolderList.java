package system;

import models.InsuranceCard;
import models.PolicyHolder;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * RMIT University Vietnam - Assignment 1
 * @author <Min Chi Gia Khiem - S3878280>
 * @version 1.0
 * @since 04/05/2024
 */

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

        // Split the line by comma, but limit the number of splits to ensure that insurance card info remains intact
        String[] parts = line.split(",", 3);

        if (parts.length >= 3) {
            String id = parts[0];
            String name = parts[1];
            String insuranceInfo = parts[2];

            // Check if insuranceInfo explicitly states "No Insurance Card"
            if("\"No Insurance Card\"".equals(insuranceInfo)) {
                return new PolicyHolder(id, name, null);
            } else {
                // Remove leading and trailing quotes from insuranceInfo
                insuranceInfo = insuranceInfo.substring(1, insuranceInfo.length() - 1);

                String[] insuranceParts = insuranceInfo.split(",", 4);
                if (insuranceParts.length == 4) {
                    try {
                        Date expiredDate = dateFormat.parse(insuranceParts[3]);
                        InsuranceCard insuranceCard = new InsuranceCard(insuranceParts[0], insuranceParts[1], insuranceParts[2], expiredDate);
                        return new PolicyHolder(id, name, insuranceCard);
                    } catch (ParseException e) {
                        System.err.println("Failed to parse the date: " + insuranceParts[3]);
                    }
                }
            }
        }

        System.err.println("Invalid line format: " + line);
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
