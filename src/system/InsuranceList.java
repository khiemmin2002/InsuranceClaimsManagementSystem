package system;

import models.InsuranceCard;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

public class InsuranceList {
    private Map<String, InsuranceCard> insuranceCards;
    private String filePath;

    // Constructor used to specify the location of the file
    // from which insurance cards will be loaded or to which they will be saved
    public InsuranceList(String filePath) {
        this.insuranceCards = new HashMap<>();
        this.filePath = filePath;
    }

    public Map<String, InsuranceCard> getInsuranceCards() { // Return type changed
        return insuranceCards;
    }

    // Load insurance cards from the file
    public void loadFromFile() {
        File file = new File(filePath);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    InsuranceCard card = parseLineToInsuranceCard(line);
                    if (card != null) {
                        insuranceCards.put(card.getCardNum(), card);
                    }
                }
            } catch (IOException e) {
                System.err.println("Error loading insurance cards: " + e.getMessage());
            }
        }
    }

    // Save insurance cards to the file
    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (InsuranceCard card : insuranceCards.values()) {
                writer.write(cardToFileFormat(card));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("An error occurred while saving insurance cards to the file: " + e.getMessage());
        }
    }

    // A method to parse a line from the file into an InsuranceCard object
    private InsuranceCard parseLineToInsuranceCard(String line) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String[] parts = line.split(",");

        if (parts.length == 4) {
            try {
                Date expiredDate = dateFormat.parse(parts[3]);
                return new InsuranceCard(parts[0], parts[1], parts[2], expiredDate);
            } catch (ParseException e) {
                System.err.println("Failed to parse the date: " + parts[3]);
                return null;
            }
        }
        return null;
    }

    // A method to format an InsuranceCard object into a line for the file
    private String cardToFileFormat(InsuranceCard card) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String expiredDateString = card.getExpiredDate() != null ? dateFormat.format(card.getExpiredDate()) : "N/A";

        return card.getCardNum() + "," + card.getCardHolder() + "," + card.getPolicyOwner() + "," + expiredDateString;
    }

    // Add a new InsuranceCard
    public void addInsuranceCard(InsuranceCard card) {
        insuranceCards.put(card.getCardNum(), card);
        saveToFile(); // Save changes
    }

    // Update an existing InsuranceCard
    public void updateInsuranceCard(String cardNumber, InsuranceCard updatedCard) {
        if (insuranceCards.containsKey(cardNumber)) {
            insuranceCards.put(cardNumber, updatedCard); // Update the card
            saveToFile(); // Save changes
        }
    }

    // Delete an InsuranceCard
    public void deleteInsuranceCard(String cardNumber) {
        if (insuranceCards.containsKey(cardNumber)) {
            insuranceCards.remove(cardNumber); // Remove the card
            saveToFile(); // Save changes
        }
    }

    // Get a single InsuranceCard by card number
    public InsuranceCard getInsuranceCard(String cardNumber) {
        return insuranceCards.get(cardNumber); // Use get method
    }
}
