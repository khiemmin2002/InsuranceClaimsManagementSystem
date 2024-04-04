package controllers;

import models.*;
import services.ClaimProcessManager;
import system.*;
import views.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ClaimController implements ClaimProcessManager {
    private ClaimView claimView;
    private ClaimList claimList;
    private PolicyHolderList policyHolderList;

    public ClaimController(ClaimView claimView, ClaimList claimList, PolicyHolderList policyHolderList) {
        this.claimView = claimView;
        this.claimList = claimList;
        this.policyHolderList = policyHolderList;
    }

    @Override
    public void add() {
        // Create a new claim
        Claim newClaim = new Claim();

        // Prompt for policyholder ID
        String policyHolderID = claimView.promptForInput("Enter the policyholder's ID: ");
        PolicyHolder policyHolder = policyHolderList.getPolicyHolder(policyHolderID);

        if (policyHolder != null) {
            InsuranceCard insuranceCard = policyHolder.getInsuranceCard();

            // Check if the policyHolder has an insurance card
            if (insuranceCard == null) {
                // Policyholder does NOT have an insurance card, notify and handle accordingly
                claimView.displayMessage("Policyholder does not have an insurance card. Please assign an insurance card to continue.");
                // Exiting the method as there is no insurance card
                return;
            }

            // Prompt for claim date
            claimView.promptForInput("Enter the claim date:");
            int year = promptForYear();
            int month = promptForMonth();
            int day = promptForDay(year, month);

            Date claimDate = null;
            try {
                claimDate = new SimpleDateFormat("MM/dd/yyyy").parse(String.format("%02d/%02d/%04d", month, day, year));
            } catch (Exception e) {
                claimView.displayMessage("Failed to parse the claim date!");
            }

            // Prompt for exam date
            claimView.promptForInput("Enter the exam date:");
            int examYear = promptForYear();
            int examMonth = promptForMonth();
            int examDay = promptForDay(examYear, examMonth);

            Date examDate = null;
            try {
                examDate = new SimpleDateFormat("MM/dd/yyyy").parse(String.format("%02d/%02d/%04d", examMonth, examDay, examYear));
            } catch (Exception e) {
                claimView.displayMessage("Failed to parse the exam date!");
            }

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

            // Prompt for bank account name
            String bankAccountName = claimView.promptForInput("Enter the bank account name: ");

            // Prompt for account number
            String accountNumber = claimView.promptForInput("Enter the account number: ");

            // Set the claim details
            newClaim.setClaimDate(claimDate);
            newClaim.setExamDate(examDate);
            newClaim.setInsuredPerson(policyHolder.getFullName());
            newClaim.setCardNum(policyHolder.getInsuranceCard().getCardNum());
            newClaim.setClaimAmount(claimAmount);
            newClaim.setBankName(bankName);
            newClaim.setBankAccountName(bankAccountName);
            newClaim.setAccountNumber(accountNumber);

            // Add the claim to the list
            claimView.displayMessage("Claim added successfully.");


            // Display the claim details
            claimView.displayClaimDetails(newClaim);

        } else {
            claimView.displayMessage("Policyholder not found.");
        }
    }

    @Override
    public void update(Claim claim) {

    }

    @Override
    public void delete(String claimId) {

    }

    @Override
    public models.Claim getOne(String claimId) {
        return null;
    }

    @Override
    public java.util.List<Claim> getAll() {
        return null;
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
        // Logic to generate the file name, ensuring to replace spaces with underscores or another character
        documentName = documentName.replaceAll("\\s+", "_");
        return claimId + "_" + cardNumber + "_" + documentName + ".pdf";
    }

}
