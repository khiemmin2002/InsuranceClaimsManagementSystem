package system;

import models.Dependent;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        String[] parts = line.split(",");
        if (parts.length >= 3) {
            String dependentID = parts[0];
            String dependentName = parts[1];
            String policyHolderID = parts[2];
            Dependent dependent = new Dependent(dependentName);
            dependent.setId(dependentID);
            dependent.setPolicyHolderID(policyHolderID); // Make sure this is set
            return dependent;
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
