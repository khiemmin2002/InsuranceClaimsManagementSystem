package models;

/**
 * RMIT University Vietnam - Assignment 1
 * @author <Min Chi Gia Khiem - S3878280>
 * @version 1.0
 * @since 04/05/2024
 */

public class Dependent extends Customer {
    private String policyHolderID;

    // Constructor used in PolicyHolderController when adding a new dependent to a policyholder
    public Dependent(String fullName, String policyHolderId, InsuranceCard insuranceCard) {
        super(fullName, insuranceCard);
        this.policyHolderID = policyHolderId;
    }

    // Constructor used in DependentList class when parsing a line from the file
    public Dependent(String id, String fullName, String policyHolderId, InsuranceCard insuranceCard) {
        super(id, fullName, insuranceCard);
        this.policyHolderID = policyHolderId;
    }

    // Getter
    public String getPolicyHolderID() {
        return policyHolderID;
    }

    // toString method to format the dependent information to be written to the file
    @Override
    public String toString() {
        String insuranceDetails = getInsuranceCard() != null ? getInsuranceCard().toString() : "No Insurance";
        return getId() + "," + getFullName() + "," + policyHolderID + "," + "\"" + insuranceDetails + "\"";
    }
}
