package models;

import java.util.Scanner;

public class Dependent extends Customer {
    private String policyHolderID;

    public Dependent() {
        super();
    }

    public Dependent(String fullName) {
        super(fullName);
    }


    public Dependent(String fullName, String policyHolderId, InsuranceCard insuranceCard) {
        super(fullName, insuranceCard); // Assuming the Customer (superclass) constructor can handle this setup
        this.policyHolderID = policyHolderId;
        // Set other necessary fields as required
    }


    @Override
    public String toString() {
        // Assuming the format is dependentID,dependentName,insurance,policyHolderID
        String insuranceDetails = getInsuranceCard() != null ? getInsuranceCard().toString() : "No Insurance";
        return String.format("%s,%s,%s,%s", getId(), getFullName(), insuranceDetails, policyHolderID);
    }
}
