package controllers;

import models.*;
import system.*;
import views.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * RMIT University Vietnam - Assignment 1
 * @author <Min Chi Gia Khiem - S3878280>
 * @version 1.0
 * @since 04/05/2024
 */

public class InsuranceCardController {
    private final InsuranceCardView insuranceCardView;
    private final InsuranceList insuranceList;

    // Constructor to set up the Insurance Card controller
    public InsuranceCardController(InsuranceCardView insuranceCardView, InsuranceList insuranceList) {
        this.insuranceCardView = insuranceCardView;
        this.insuranceList = insuranceList;
    }

    // Method to add an insurance card
    public void addInsuranceCard() throws IOException{
        insuranceCardView.displayMessage("Adding an insurance card...");

        String cardHolder = insuranceCardView.promptForInput("Enter the card holder's name: ");
        String policyOwner = insuranceCardView.promptForInput("Enter the policy owner's name: ");

        int year = promptForYear();
        int month = promptForMonth();
        int day = promptForDay(year, month);

        Date expiredDate = null;
        try {
            expiredDate = new SimpleDateFormat("MM/dd/yyyy").parse(String.format("%02d/%02d/%04d", month, day, year));
        } catch (Exception e) {
            insuranceCardView.displayMessage("Failed to parse the date!");
        }

        InsuranceCard newInsuranceCard = new InsuranceCard(cardHolder, policyOwner, expiredDate);
        insuranceList.addInsuranceCard(newInsuranceCard); // Add to list
        saveInsuranceCardToFile(newInsuranceCard); // Save to file
    }

    // Methods to handle date input

    // Prompt for year input, the year must larger than the current year and can only accept numeric input
    public int promptForYear() {
        while (true) {
            try {
                int year = Integer.parseInt(insuranceCardView.promptForInput("Enter the expired year (yyyy): "));
                if (year >= Calendar.getInstance().get(Calendar.YEAR)) {
                    return year;
                } else {
                    insuranceCardView.displayMessage("Invalid year, please enter a year in the future.");
                }
            } catch (NumberFormatException e) {
                insuranceCardView.displayMessage("Please enter a valid numeric year.");
            }
        }
    }

    // Prompt for month input, the month must be between 1 and 12
    public int promptForMonth() {
        while (true) {
            try {
                int month = Integer.parseInt(insuranceCardView.promptForInput("Enter the expired month (mm): "));
                if (month >= 1 && month <= 12) {
                    return month;
                } else {
                    insuranceCardView.displayMessage("Invalid month, please enter a month between 1 and 12.");
                }
            } catch (NumberFormatException e) {
                insuranceCardView.displayMessage("Please enter a valid numeric month.");
            }
        }
    }

    // Prompt for day input, the day must be valid for the given month and year
    public int promptForDay(int year, int month) {
        while (true) {
            try {
                int day = Integer.parseInt(insuranceCardView.promptForInput("Enter the expired day (dd): "));
                if (isValidDay(year, month, day)) {
                    return day;
                } else {
                    insuranceCardView.displayMessage("Invalid day for the given month and year, please enter a valid day.");
                }
            } catch (NumberFormatException e) {
                insuranceCardView.displayMessage("Please enter a valid numeric day.");
            }
        }
    }

    public boolean isValidDay(int year, int month, int day) {
        if (day < 1 || day > 31) {
            return false;
        }
        if (month == 2) {
            if (year % 4 == 0) {
                return day <= 29;
            } else {
                return day <= 28;
            }
        }
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            return day <= 30;
        }
        return true;
    }

    // Display the insurance card details
    public void displayInsuranceCardDetails(InsuranceCard insuranceCardModel) {
        insuranceCardView.displayInsuranceCardDetails(
                insuranceCardModel.getCardNum(),
                insuranceCardModel.getCardHolder(),
                insuranceCardModel.getPolicyOwner(),
                insuranceCardModel.getFormattedExpiredDate()
        );
    }

    // Saving the insurance card details to the file
    public void saveInsuranceCardToFile(InsuranceCard insuranceCard) throws IOException {
        String fileName = "./data/Insurance.txt";
        File dataFile = new File(fileName);

        // Check and create the data directory if it does not exist
        if (!dataFile.getParentFile().exists() && !dataFile.getParentFile().mkdirs()) {
            throw new IOException("Failed to create directory " + dataFile.getParentFile().getAbsolutePath());
        }

        try (BufferedWriter insuranceWriter = new BufferedWriter(new FileWriter(dataFile, true))) {
            insuranceWriter.write(insuranceCard.toString());
            insuranceWriter.newLine(); // Add a newline after writing the insurance card details
            insuranceCardView.displayMessage("\nInsurance card saved successfully to " + fileName);
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file.");
            insuranceCardView.displayMessage("\nFailed to save the insurance card to " + fileName);
        }
    }

    // Read the insurance card details from the file
    public void displayAllInsuranceCards() {
        Map<String, InsuranceCard> cards = insuranceList.getInsuranceCards();
        if (cards.isEmpty()) {
            insuranceCardView.displayMessage("No insurance cards available.");
        } else {
            cards.values().stream() // Convert collection of cards to stream
                    .sorted(Comparator.comparing(InsuranceCard::getCardNum)) // Sort by cardNum
                    .forEach(this::displayInsuranceCardDetails); // Use method reference to display details of each card
        }
    }

    // A method to update the insurance card details
    public void updateInsuranceCard() {
        String cardNumber = insuranceCardView.promptForInput("Enter the card number of the insurance card to update: ");
        InsuranceCard cardToUpdate = insuranceList.getInsuranceCard(cardNumber);

        if (cardToUpdate != null) {
            String cardHolder = insuranceCardView.promptForInput("Enter the new card holder's name: ");
            String policyOwner = insuranceCardView.promptForInput("Enter the new policy owner's name: ");

            int year = promptForYear();
            int month = promptForMonth();
            int day = promptForDay(year, month);

            Date expiredDate = null;
            try {
                expiredDate = new SimpleDateFormat("MM/dd/yyyy").parse(String.format("%02d/%02d/%04d", month, day, year));
            } catch (Exception e) {
                insuranceCardView.displayMessage("Failed to parse the date!");
            }

            // Create the updated card object
            InsuranceCard updatedCard = new InsuranceCard(cardNumber, cardHolder, policyOwner, expiredDate);

            // Update the card in the list
            insuranceList.updateInsuranceCard(cardNumber, updatedCard);
            insuranceCardView.displayMessage("Insurance card updated successfully.");
        } else {
            insuranceCardView.displayMessage("Insurance card not found.");
        }
    }

    // A method to delete the insurance card
    public void deleteInsuranceCard() {
        // Prompt user for card number
        String cardNumber = insuranceCardView.promptForInput("Enter the card number of the insurance card to delete: ");

        // Delete the card from the list
        insuranceList.deleteInsuranceCard(cardNumber);
        insuranceCardView.displayMessage("Insurance card deleted successfully.");
    }
}
