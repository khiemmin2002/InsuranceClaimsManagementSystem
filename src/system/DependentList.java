package system;

import models.Dependent;
import models.InsuranceCard;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * RMIT University Vietnam - Assignment 1
 * @author <Min Chi Gia Khiem - S3878280>
 * @version 1.0
 * @since 04/05/2024
 */

/***************************************************************************************
 *    Title: Split() String method in Java with examples
 *    Author: Vaibhav, B
 *    Date: 05/22/2023
 *    Availability: https://www.geeksforgeeks.org/split-string-java-examples/
 * ***************************************************************************************/

public class DependentList {
    private Map<String, Dependent> dependents = new HashMap<>();
    private String filePath;

    // Constructor used to specify the location of the file
    // from which dependents will be loaded or to which they will be saved
    public DependentList(String filePath) {
        this.filePath = filePath;
    }

    // A method to load dependents from the file
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

    // A method to save dependents to the file
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

    // A method to parse a line from the file into a Dependent object
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

    // A method to convert a dependent object to a string format for writing to the file
    private String dependentToFileFormat(Dependent dependent) {
        return dependent.toString();
    }

    // A method to add a dependent
    public void addDependent(Dependent dependent) {
        dependents.put(dependent.getId(), dependent);
        saveToFile();
    }

    // A method to delete a dependent
    public boolean deleteDependent(String dependentID) {
        if (dependents.containsKey(dependentID)) {
            dependents.remove(dependentID);
            saveToFile();
            return true;
        }
        return false;
    }

    // A method to get dependents by policyHolderID
    public List<Dependent> getDependentsForPolicyHolder(String policyHolderID) {
        // Filter the dependents whose policyHolderID matches the given ID and return them as a list
        return dependents.values().stream()
                .filter(dependent -> policyHolderID.equals(dependent.getPolicyHolderID()))
                .collect(Collectors.toList());
    }

    // A method to get a dependent by ID
    public Dependent getDependent(String dependentID) {
        return dependents.get(dependentID);
    }
}
