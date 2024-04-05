package system;

import models.Claim;
import models.Customer;
import models.InsuranceCard;
import models.PolicyHolder;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ClaimList {
    private Map<String, Claim> claims = new HashMap<>();
    private String filePath;

    public ClaimList(String filePath) {
        this.filePath = filePath;
    }

    public Map<String, Claim> getClaims() {
        return new HashMap<>(claims);
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

    public void addClaim(Claim claim) {
        claims.put(claim.getClaimID(), claim);
        saveToFile();
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
        // Identify and preprocess the list of documents
        int listStart = line.indexOf('[');
        int listEnd = line.lastIndexOf(']');
        String preList = line.substring(0, listStart);
        String postList = line.substring(listEnd + 1);
        String listContents = line.substring(listStart + 1, listEnd).replace(", ", ";");

        // Combine the parts back together with the modified list contents
        String processedLine = preList + listContents + postList;

        // Now split the rest of the processed line
        String[] parts = processedLine.split(",", -1);

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

                return new Claim(claimID, claimDate, insuredPerson, cardNum, examDate, listOfDocuments, claimAmount, claimStatus, bankName, bankAccountName, accountNumber);
            } catch (ParseException e) {
                System.err.println("Failed to parse the date: " + e.getMessage());
                return null;
            }
        }

        return null;
    }


}
