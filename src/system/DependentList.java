package system;

import models.Dependent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DependentList {
    private List<Dependent> dependents;
    private String filePath;

    public DependentList(String filePath) {
        this.dependents = new ArrayList<>();
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
                        dependents.add(dependent);
                    }
                }
            } catch (IOException e) {
                System.err.println("An error occurred while loading dependents from the file: " + e.getMessage());
            }
        }
    }

    private Dependent parseLineToDependent(String line) {
        String[] parts = line.split(",");
        if (parts.length >= 3) {
            // Assuming the parts are ordered as dependentID, dependentName, policyHolderID, and optional insurance information
            String dependentID = parts[0];
            String dependentName = parts[1];
            String policyHolderID = parts[2];
            Dependent dependent = new Dependent(dependentName);
            dependent.setId(dependentID);
            // Set other properties as necessary, such as linking to policyholder or insurance card
            return dependent;
        }
        return null;
    }

    public void addDependent(Dependent dependent) {
        dependents.add(dependent);
        saveToFile();
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (Dependent dependent : dependents) {
                writer.write(dependentToFileFormat(dependent));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("An error occurred while saving dependents to the file: " + e.getMessage());
        }
    }

    private String dependentToFileFormat(Dependent dependent) {
        // Format the dependent details for file saving
        return String.format("%s,%s,%s", dependent.getId(), dependent.getFullName(), dependent.getPolicyHolder().getId());
        // Include insurance details if necessary
    }

    public List<Dependent> getDependents() {
        return this.dependents;
    }

    // Implement other necessary methods such as deleteDependent, updateDependent, etc.
}
