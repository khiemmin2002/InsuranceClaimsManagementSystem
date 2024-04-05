package models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public String getClaimID() {
        return claimID;
    }

    public Date getClaimDate() {
        return claimDate;
    }

    public String getInsuredPerson() {
        return insuredPerson;
    }

    public String getCardNum() {
        return cardNum;
    }

    public Date getExamDate() {
        return examDate;
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

    // Setters


    public void setClaimID(String claimID) {
        this.claimID = claimID;
    }

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

    public void setListOfDocuments(List<String> listOfDocuments) {
        this.listOfDocuments = listOfDocuments;
    }

    public void setClaimAmount(double claimAmount) {
        this.claimAmount = claimAmount;
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

    public String getCustomerID() {
        return customerID;
    }

    // Generate random claim ID
    private String generateClaimID() {
        StringBuilder claimIDBuilder = new StringBuilder("f");
        for (int i = 0; i < 10; i++) {
            claimIDBuilder.append((int) (Math.random() * 10));
        }
        return claimIDBuilder.toString();
    }

    // Format the date to mm/dd/yyyy
    public String getFormattedClaimDate() {
        if (claimDate == null) {
            return "Claim Date is not set";
        }
        return new SimpleDateFormat("MM/dd/yyyy").format(claimDate);
    }

    // Format the date to mm/dd/yyyy
    public String getFormattedExamDate() {
        if (examDate == null) {
            return "Exam Date is not set";
        }
        return new SimpleDateFormat("MM/dd/yyyy").format(examDate);
    }

    public void setClaimStatus(String claimStatus) {
        this.claimStatus = claimStatus;
    }

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
