package models;

import controllers.*;
import views.*;
import system.*;

import java.text.SimpleDateFormat;
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
    private BankInfo bankInfo;

    public Claim() {
    }

    public Claim(String claimID, Date claimDate, Customer insuredPerson, InsuranceCard cardNum, Date examDate, List<String> listOfDocuments, double claimAmount, String claimStatus, BankInfo bankInfo) {
        this.claimID = generateClaimID();
        this.claimDate = null;
        this.insuredPerson = insuredPerson.getFullName();
        this.cardNum = cardNum.getCardNum();
        this.examDate = null;
        this.listOfDocuments = listOfDocuments;
        this.claimAmount = 0;
        this.claimStatus = "NEW";
        this.bankInfo = bankInfo;
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

}
