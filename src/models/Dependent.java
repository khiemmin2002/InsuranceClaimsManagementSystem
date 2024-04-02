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
        super(fullName, insuranceCard);
        this.policyHolderID = policyHolderId;
    }

    public String getPolicyHolderID() {
        return policyHolderID;
    }

    public void setPolicyHolderID(String policyHolderID) {
        this.policyHolderID = policyHolderID;
    }

    @Override
    public String toString() {
        String insuranceDetails = getInsuranceCard() != null ? getInsuranceCard().toString() : "No Insurance";
        return String.format("%s,%s,%s,%s", getId(), getFullName(), insuranceDetails, policyHolderID);
    }
}
