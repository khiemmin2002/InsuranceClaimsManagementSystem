package controllers;

import models.*;
import services.ClaimProcessManager;
import system.*;
import views.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * RMIT University Vietnam - Assignment 1
 * @author <Min Chi Gia Khiem - S3878280>
 * @version 1.0
 * @since 04/05/2024
 */

public class ClaimController implements ClaimProcessManager {
    private ClaimView claimView;
    private ClaimList claimList;
    private PolicyHolderList policyHolderList;

    // Constructor to set up the ClaimController in the Main class
    public ClaimController(ClaimView claimView, ClaimList claimList, PolicyHolderList policyHolderList) {
        this.claimView = claimView;
        this.claimList = claimList;
        this.policyHolderList = policyHolderList;
    }

    // Implementing the methods from the ClaimProcessManager interface

    // Method to add a claim
    @Override
    public void add() {
        claimView.displayMessage("\nAdding a claim...");
        // Create a new claim
        Claim newClaim = new Claim();

        // Prompt for policyholder ID
        String policyHolderID = claimView.promptForInput("Enter the policyholder's ID: ");
        PolicyHolder policyHolder = policyHolderList.getPolicyHolder(policyHolderID);

        if (policyHolder != null) {
            InsuranceCard insuranceCard = policyHolder.getInsuranceCard();

            // Check if the policyHolder has an insurance card
            if (insuranceCard == null) {
                // Policyholder does NOT have an insurance card, notify and exit the method
                claimView.displayMessage("Policyholder does not have an insurance card. Please assign an insurance card to continue.");
                return;
            }

            // Prompt for claim date
            claimView.displayMessage("Enter the claim date:");
            int year = promptForYear();
            int month = promptForMonth();
            int day = promptForDay(year, month);

            Date claimDate = null;
            try {
                claimDate = new SimpleDateFormat("MM/dd/yyyy").parse(String.format("%02d/%02d/%04d", month, day, year));
            } catch (Exception e) {
                claimView.displayMessage("Failed to parse the claim date!");
            }

            newClaim.setClaimDate(claimDate);

            // Prompt for exam date
            claimView.displayMessage("Enter the exam date:");
            int examYear = promptForExamYear();
            int examMonth = promptForMonth();
            int examDay = promptForDay(examYear, examMonth);

            Date examDate = null;
            try {
                examDate = new SimpleDateFormat("MM/dd/yyyy").parse(String.format("%02d/%02d/%04d", examMonth, examDay, examYear));
            } catch (Exception e) {
                claimView.displayMessage("Failed to parse the exam date!");
            }

            newClaim.setExamDate(examDate);

            newClaim.setInsuredPerson(policyHolder.getFullName());
            newClaim.setCardNum(policyHolder.getInsuranceCard().getCardNum());

            // Prompt for documents
            // Ask for a number of documents (At least 1)
            int numOfDocuments;

            do {
                numOfDocuments = promptForNumberOfDocuments();
                if (numOfDocuments <= 0) {
                    claimView.displayMessage("At least one document is required. Please enter a valid number.");
                }
            } while (numOfDocuments <= 0);

            for (int i = 0; i < numOfDocuments; i++) {
                String documentName = claimView.promptForInput("Enter the name for document #" + (i + 1) + ": ");
                String documentFileName = generateDocumentFileName(newClaim.getClaimID(), newClaim.getCardNum(), documentName);
                newClaim.getListOfDocuments().add(documentFileName);
            }

            // Prompt for claim amount and the amount must be a double and larger than 0
            double claimAmount = 0;
            while (claimAmount <= 0) {
                try {
                    claimAmount = Double.parseDouble(claimView.promptForInput("Enter the claim amount: "));
                    if (claimAmount <= 0) {
                        claimView.displayMessage("Please enter a number greater than 0.");
                    }
                } catch (NumberFormatException e) {
                    claimView.displayMessage("Please enter a valid numeric value.");
                }
            }

            // Prompt for bank name
            String bankName = claimView.promptForInput("Enter the bank name: ");

            // Prompt for account number
            String accountNumber = claimView.promptForInput("Enter the account number: ");

            // Set the claim details
            newClaim.setClaimAmount(claimAmount);
            newClaim.setBankName(bankName);
            newClaim.setBankAccountName(policyHolder.getFullName());
            newClaim.setAccountNumber(accountNumber);
            newClaim.setCustomerID(policyHolder.getId());

            // Add the claim to the list
            claimList.addClaim(newClaim);
            claimView.displayMessage("\nClaim added successfully.");


            // Display the claim details
            claimView.displayClaimDetails(newClaim);

        } else {
            claimView.displayMessage("Policyholder not found.");
        }
    }

    // Method to update a claim
    @Override
    public void update() {
        String claimID = claimView.promptForInput("Enter the claim ID: ");
        Claim updatedClaim = claimList.getClaim(claimID);

        if (updatedClaim != null) {
            int option;
            do {
                claimView.displayMessage("\nEnter the claim status:");
                claimView.displayMessage("1. PROCESSING");
                claimView.displayMessage("2. DONE");
                claimView.displayMessage("3. CANCELED");
                claimView.displayMessage("Choose an option (1-3): ");

                String input = claimView.promptForInput("");
                try {
                    option = Integer.parseInt(input);
                    if (option < 1 || option > 3) {
                        claimView.displayMessage("Invalid option, please enter a number between 1 and 3.");
                    }
                } catch (NumberFormatException e) {
                    option = 0; // Set to an invalid value to trigger the loop again
                    claimView.displayMessage("Invalid input, please enter a number.");
                }
            } while (option < 1 || option > 3);

            // Map the option to the corresponding claim status
            String claimStatus = switch (option) {
                case 1 -> "PROCESSING";
                case 2 -> "DONE";
                case 3 -> "CANCELED";
                default -> throw new IllegalStateException("Unexpected value: " + option);
            };
            updatedClaim.setClaimStatus(claimStatus);
            claimList.updateClaim(claimID, updatedClaim);
            claimView.displayMessage("Claim updated successfully.");
        } else {
            claimView.displayMessage("Claim not found.");
        }
    }

    // Method to delete a claim
    @Override
    public void delete() {
        // Prompt for claim ID
        String claimID = claimView.promptForInput("Enter the claim ID: ");
        claimList.deleteClaim(claimID);
        claimView.displayMessage("Insurance card deleted successfully.");
    }

    // Method to get one claim
    @Override
    public void getOne() {
        // Prompt for claim ID
        String claimID = claimView.promptForInput("Enter the claim ID: ");
        Claim claim = claimList.getClaim(claimID);
        if (claim != null) {
            claimView.displayClaimDetails(claim);
        } else {
            claimView.displayMessage("Claim not found.");
        }
    }

    // Method to get all claims
    @Override
    public void getAll() {
        // Display all claims
        claimView.displayMessage("\nDisplaying all claims...");
        Map<String, Claim> claims = claimList.getClaims();
        if (claims.isEmpty()) {
            claimView.displayMessage("No claims found.");
        } else {
            for (Map.Entry<String, Claim> entry : claims.entrySet()) {
                claimView.displayClaimDetails(entry.getValue());
            }
        }

    }

    // Methods to handle date input
    // Prompt for year input, the year must larger than the current year and can only accept numeric input
    public int promptForYear() {
        while (true) {
            try {
                int year = Integer.parseInt(claimView.promptForInput("Enter the year (yyyy): "));
                if (year >= Calendar.getInstance().get(Calendar.YEAR)) {
                    return year;
                } else {
                    claimView.displayMessage("Invalid year, please enter a year in the future.");
                }
            } catch (NumberFormatException e) {
                claimView.displayMessage("Please enter a valid numeric year.");
            }
        }
    }

    // Prompt for month input, the month must be between 1 and 12
    public int promptForMonth() {
        while (true) {
            try {
                int month = Integer.parseInt(claimView.promptForInput("Enter the month (mm): "));
                if (month >= 1 && month <= 12) {
                    return month;
                } else {
                    claimView.displayMessage("Invalid month, please enter a month between 1 and 12.");
                }
            } catch (NumberFormatException e) {
                claimView.displayMessage("Please enter a valid numeric month.");
            }
        }
    }

    // Prompt for day input, the day must be valid for the given month and year
    public int promptForDay(int year, int month) {
        while (true) {
            try {
                int day = Integer.parseInt(claimView.promptForInput("Enter the day (dd): "));
                if (isValidDay(year, month, day)) {
                    return day;
                } else {
                    claimView.displayMessage("Invalid day for the given month and year, please enter a valid day.");
                }
            } catch (NumberFormatException e) {
                claimView.displayMessage("Please enter a valid numeric day.");
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

    // Prompt for exam year
    public int promptForExamYear() {
        while (true) {
            try {
                return Integer.parseInt(claimView.promptForInput("Enter the year (yyyy): "));
            } catch (NumberFormatException e) {
                claimView.displayMessage("Please enter a valid numeric year.");
            }
        }
    }

    // Process for the documents
    // Process for the number of documents to add
    private int promptForNumberOfDocuments() {
        int numOfDocuments = 0;
        while (numOfDocuments <= 0) {
            try {
                numOfDocuments = Integer.parseInt(claimView.promptForInput("Enter the number of documents: "));
                if (numOfDocuments <= 0) {
                    claimView.displayMessage("Please enter a number greater than 0.");
                }
            } catch (NumberFormatException e) {
                claimView.displayMessage("Please enter a valid numeric value.");
            }
        }
        return numOfDocuments;
    }

    // Process the document file name
    private String generateDocumentFileName(String claimId, String cardNumber, String documentName) {
        // Generate the file name
        documentName = documentName.replaceAll("\\s+", " ");
        return claimId + "_" + cardNumber + "_" + documentName + ".pdf";
    }
}
