package system;

import models.Dependent;
import models.InsuranceCard;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class DependentList {
    private Map<String, Dependent> dependents = new HashMap<>();
    private String filePath;

    public DependentList(String filePath) {
        this.filePath = filePath;
    }

    public void loadFromFile() {
        File file = new File(filePath);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Dependent dependent = parseLineToDependent(line);
                    if (dependent != null) {
                        dependents.put(dependent.getId(), dependent);
                    }
                }
            } catch (IOException e) {
                System.err.println("An error occurred while loading dependents from the file: " + e.getMessage());
            }
        }
    }

    private Dependent parseLineToDependent(String line) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        // Attempt to isolate insurance information, if any
        String[] parts = line.split(",", 4);

        if (parts.length >= 4) {
            String id = parts[0];
            String name = parts[1];
            String policyHolderID = parts[2];
            String insuranceInfo = parts[3];

            // Check if insuranceInfo explicitly states "No Insurance"
            if ("\"No Insurance\"".equals(insuranceInfo)) {
                return new Dependent(id, name, policyHolderID, null);
            } else {
                // Remove leading and trailing quotes from insuranceInfo
                insuranceInfo = insuranceInfo.substring(1, insuranceInfo.length() - 1);

                String[] insuranceParts = insuranceInfo.split(",", 4);

                try {
                    if (insuranceParts.length == 4) {
                        Date expiredDate = dateFormat.parse(insuranceParts[3]);
                        InsuranceCard insuranceCard = new InsuranceCard(insuranceParts[0], insuranceParts[1], insuranceParts[2], expiredDate);
                        return new Dependent(id, name, policyHolderID, insuranceCard);
                    }
                } catch (ParseException e) {
                    System.err.println("Failed to parse the date: " + insuranceParts[3]);
                }
            }
        }

        return null;
    }


    public void addDependent(Dependent dependent) {
        dependents.put(dependent.getId(), dependent);
        saveToFile();
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (Dependent dependent : dependents.values()) {
                writer.write(dependentToFileFormat(dependent));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("An error occurred while saving dependents to the file: " + e.getMessage());
        }
    }

    private String dependentToFileFormat(Dependent dependent) {
        return dependent.toString();
    }

    public Map<String, Dependent> getDependents() {
        return new HashMap<>(dependents);
    }



    // Implement delete and update methods if necessary
    // Example:
    public boolean deleteDependent(String dependentID) {
        if (dependents.containsKey(dependentID)) {
            dependents.remove(dependentID);
            saveToFile();
            return true;
        }
        return false;
    }

    // New method to get dependents by policyHolderID
    public List<Dependent> getDependentsForPolicyHolder(String policyHolderID) {
        // Filter the dependents whose policyHolderID matches the given ID and return them as a list
        return dependents.values().stream()
                .filter(dependent -> policyHolderID.equals(dependent.getPolicyHolderID()))
                .collect(Collectors.toList());
    }

    public Dependent getDependent(String dependentID) {
        return dependents.get(dependentID);
    }
}
