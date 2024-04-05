package system;

import models.*;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class ClaimList {
    private Map<String, Claim> claims = new HashMap<>();
    private String filePath;

    public ClaimList(String filePath) {
        this.filePath = filePath;
    }

    public Map<String, Claim> getClaims() {
        return new HashMap<>(claims);
    }

    // Get a claim by ID
    public Claim getClaim(String claimID) {
        return claims.get(claimID);
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (Claim claim : claims.values()) {
                writer.write(claim.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("An error occurred while saving claims to the file: " + e.getMessage());
        }
    }

    public void loadFromFile() {
        File file = new File(filePath);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Claim claim = parseLineToClaim(line);
                    if (claim != null) {
                        claims.put(claim.getClaimID(), claim);
                    }
                }
            } catch (IOException e) {
                System.err.println("An error occurred while loading policy holders from the file: " + e.getMessage());
            }
        }
    }

    private Claim parseLineToClaim(String line) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String[] parts = getStrings(line);

        if (parts.length >= 11) {
            try {
                String claimID = parts[0];
                Date claimDate = dateFormat.parse(parts[1]);
                String insuredPerson = parts[2];
                String cardNum = parts[3];
                Date examDate = dateFormat.parse(parts[4]);
                List<String> listOfDocuments = new ArrayList<>(Arrays.asList(parts[5].split(";")));
                double claimAmount = Double.parseDouble(parts[6]);
                String claimStatus = parts[7];
                String bankName = parts[8];
                String bankAccountName = parts[9];
                String accountNumber = parts[10];
                String customerID = parts[11];

                return new Claim(claimID, claimDate, insuredPerson, cardNum, examDate, listOfDocuments, claimAmount, claimStatus, bankName, bankAccountName, accountNumber, customerID);
            } catch (ParseException e) {
                System.err.println("Failed to parse the date: " + e.getMessage());
                return null;
            }
        }

        return null;
    }

    private static String[] getStrings(String line) {
        int listStart = line.indexOf('[');
        int listEnd = line.lastIndexOf(']');
        String preList = line.substring(0, listStart);
        String postList = line.substring(listEnd + 1);
        String listContents = line.substring(listStart + 1, listEnd).replace(", ", ";");

        // Combine the parts back together with the modified list contents
        String processedLine = preList + listContents + postList;

        // Now split the rest of the processed line
        String[] parts = processedLine.split(",", -1);
        return parts;
    }

    // Add a claim
    public void addClaim(Claim claim) {
        claims.put(claim.getClaimID(), claim);
        saveToFile();
    }

    // Delete a claim
    public void deleteClaim(String claimID) {
        if (claims.containsKey(claimID)) {
            claims.remove(claimID);
            saveToFile();
        }
    }

    // Update a claim
    public void updateClaim(String claimID, Claim updatedClaim) {
        if (claims.containsKey(claimID)) {
            claims.put(claimID, updatedClaim);
            saveToFile();
        }
    }

    public List<Claim> getClaimsForPolicyHolder(String policyHolderID) {
        // Filter the claims whose customerID matches the given ID and return them as a list
        return claims.values().stream()
                .filter(claim -> policyHolderID.equals(claim.getCustomerID()))
                .collect(Collectors.toList());
    }

}
