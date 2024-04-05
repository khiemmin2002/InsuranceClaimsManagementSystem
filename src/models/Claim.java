package models;

import controllers.*;
import views.*;
import system.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.Date;

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
    }

    public Claim(String claimID, Date claimDate, Customer insuredPerson, InsuranceCard cardNum, Date examDate, List<String> listOfDocuments, double claimAmount, String claimStatus, String bankName, String bankAccountName, String accountNumber) {
        this.claimID = generateClaimID();
        this.claimDate = null;
        this.insuredPerson = insuredPerson.getFullName();
        this.cardNum = cardNum.getCardNum();
        this.examDate = null;
        this.listOfDocuments = new ArrayList<>();
        this.claimAmount = 0;
        this.claimStatus = "NEW";
        this.bankName = bankName;
        this.bankAccountName = bankAccountName;
        this.accountNumber = accountNumber;
    }

    public Claim(Date claimDate, Customer insuredPerson, InsuranceCard insuranceCard, Date examDate, List<String> listOfDocuments, double claimAmount, String bankName, String bankAccountName, String accountNumber) {
        this.claimID = generateClaimID();
        this.claimDate = claimDate;
        this.insuredPerson = insuredPerson.getFullName();
        this.cardNum = insuranceCard.getCardNum();
        this.examDate = examDate;
        this.listOfDocuments = listOfDocuments != null ? listOfDocuments : new ArrayList<>();
        this.claimAmount = claimAmount;
        this.claimStatus = "NEW";
        this.bankName = bankName;
        this.bankAccountName = bankAccountName;
        this.accountNumber = accountNumber;
    }

    public Claim(String claimID, Date claimDate, String insuredPerson, String cardNum, Date examDate, List<String> listOfDocuments, double claimAmount, String claimStatus, String bankName, String bankAccountName, String accountNumber) {
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

    // Set the status
    public boolean setClaimStatus(String claimStatus) {
        String [] claimStatusList = {"NEW" , "PROCESSING", "DONE"};
        boolean checked = false;
        for (String availableClaimStatus: claimStatusList){
            if (availableClaimStatus.equals(claimStatus))
                checked = true;
        }
        if (checked){
            this.claimStatus = claimStatus;
            return true;
        }
        return false;
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
                accountNumber;
    }
}
