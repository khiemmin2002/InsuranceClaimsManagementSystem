package models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * RMIT University Vietnam - Assignment 1
 * @author <Min Chi Gia Khiem - S3878280>
 * @version 1.0
 * @since 04/05/2024
 */

public class Claim {
    private String claimID;
    private Date claimDate;
    private String insuredPerson;
    private String cardNum;
    private Date examDate;
    private List<String> listOfDocuments;
    private double claimAmount;
    private String claimStatus; // (New, Processing, Done)
    private String bankName;
    private String bankAccountName;
    private String accountNumber;
    private String customerID;

    // Default constructor with default values
    public Claim() {
        this.claimID = generateClaimID();
        this.claimDate = null;
        this.insuredPerson = "Default";
        this.cardNum = "Default";
        this.examDate = null;
        this.listOfDocuments = new ArrayList<>();
        this.claimAmount = 0;
        this.claimStatus = "NEW";
        this.bankName = "Default";
        this.bankAccountName = "Default";
        this.accountNumber = "Default";
        this.customerID = "Default";
    }

    // Constructor used in ClaimList class when parsing a line from the file
    public Claim(String claimID, Date claimDate, String insuredPerson, String cardNum, Date examDate, List<String> listOfDocuments, double claimAmount, String claimStatus, String bankName, String bankAccountName, String accountNumber, String customerID) {
        this.claimID = claimID;
        this.claimDate = claimDate;
        this.insuredPerson = insuredPerson;
        this.cardNum = cardNum;
        this.examDate = examDate;
        this.listOfDocuments = listOfDocuments;
        this.claimAmount = claimAmount;
        this.claimStatus = claimStatus;
        this.bankName = bankName;
        this.bankAccountName = bankAccountName;
        this.accountNumber = accountNumber;
        this.customerID = customerID;
    }

    // Getter
    public String getClaimID() {
        return claimID;
    }

    public String getInsuredPerson() {
        return insuredPerson;
    }

    public String getCardNum() {
        return cardNum;
    }

    public List<String> getListOfDocuments() {
        return listOfDocuments;
    }

    public double getClaimAmount() {
        return claimAmount;
    }

    public String getClaimStatus() {
        return claimStatus;
    }

    public String getBankName() {
        return bankName;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getCustomerID() {
        return customerID;
    }

    // Setter

    public void setClaimDate(Date claimDate) {
        this.claimDate = claimDate;
    }

    public void setInsuredPerson(String insuredPerson) {
        this.insuredPerson = insuredPerson;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public void setClaimAmount(double claimAmount) {
        this.claimAmount = claimAmount;
    }

    public void setClaimStatus(String claimStatus) {
        this.claimStatus = claimStatus;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setCustomerID(String policyHolderID) {
        this.customerID = policyHolderID;
    }

    // Generate a random claim ID with the format "f##########"
    private String generateClaimID() {
        StringBuilder claimIDBuilder = new StringBuilder("f");
        for (int i = 0; i < 10; i++) {
            claimIDBuilder.append((int) (Math.random() * 10));
        }
        return claimIDBuilder.toString();
    }

    // Format the date to mm/dd/yyyy
    public String getFormattedClaimDate() { // Format the claim date
        if (claimDate == null) {
            return "Claim Date is not set";
        }
        return new SimpleDateFormat("MM/dd/yyyy").format(claimDate);
    }

    public String getFormattedExamDate() { // Format the exam date
        if (examDate == null) {
            return "Exam Date is not set";
        }
        return new SimpleDateFormat("MM/dd/yyyy").format(examDate);
    }

    // toString method to format the claim object to a string
    @Override
    public String toString() {
        // Format the date to mm/dd/yyyy
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String claimDateString = claimDate != null ? dateFormat.format(claimDate) : "N/A";
        String examDateString = examDate != null ? dateFormat.format(examDate) : "N/A";

        return claimID + "," +
                claimDateString + "," +
                insuredPerson + "," +
                cardNum + "," +
                examDateString + "," +
                listOfDocuments + "," +
                claimAmount + "," +
                claimStatus + "," +
                bankName + "," +
                bankAccountName + "," +
                accountNumber + "," +
                customerID;
    }
}
