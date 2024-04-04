package system;

import models.Claim;
import models.Customer;
import models.InsuranceCard;
import models.PolicyHolder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ClaimList {
    private Map<String, Claim> claims = new HashMap<>();
    private String filePath;

    public ClaimList(String filePath) {
        this.filePath = filePath;
    }

    public Map<String, Claim> getClaims() {
        return new HashMap<>(claims);
    }

//    public void loadFromFile() {
//        File file = new File(filePath);
//        if (file.exists()) {
//            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    Claim claim = parseLineToClaim(line);
//                    if (claim != null) {
//                        claims.put(claim.getClaimID(), claim);
//                    }
//                }
//            } catch (IOException e) {
//                System.err.println("An error occurred while loading policy holders from the file: " + e.getMessage());
//            }
//        }
//    }
//
//    private Claim parseLineToClaim(String line) {
//        // Split the line by comma
//        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
//        String[] parts = line.split(",");
//
//        if (parts.length == 11) {
//            try {
//                Date claimDate = dateFormat.parse(parts[3]);
//                Date examDate = dateFormat.parse(parts[5]);
//                Customer insuredPerson = new PolicyHolder(parts[2]);
//                InsuranceCard card = new InsuranceCard(parts[4]);
//                // List of documents
//
//                return new Claim(parts[0], claimDate, insuredPerson, card, examDate, parts[6], Double.parseDouble(parts[7]), parts[8], parts[9], parts[10], parts[11]);
//            } catch (ParseException e) {
//                System.err.println("Failed to parse the date: " + parts[3]);
//                return null;
//            }
//        }
//
//        return null;
//    }
}
