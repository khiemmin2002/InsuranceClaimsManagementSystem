package models;

import java.util.ArrayList;
import java.util.List;

/**
 * RMIT University Vietnam - Assignment 1
 * @author <Min Chi Gia Khiem - S3878280>
 * @version 1.0
 * @since 04/05/2024
 */

public abstract class Customer {
    private String id;
    private String fullName;
    private InsuranceCard insuranceCard;
    private List<Claim> claims;


    // Default constructor with default values
    public Customer() {
        this.id = generateCustomerID();
        this.fullName = "Default";
        this.insuranceCard = new InsuranceCard();
        this.claims = new ArrayList<Claim>();
    }

    public Customer(String fullName, InsuranceCard insuranceCard) {
        this.id = generateCustomerID();
        this.fullName = fullName;
        this.insuranceCard = insuranceCard;
        this.claims = new ArrayList<Claim>();
    }

    public Customer(String id, String fullName, InsuranceCard insuranceCard) {
        this.id = id;
        this.fullName = fullName;
        this.insuranceCard = insuranceCard;
        this.claims = new ArrayList<Claim>();
    }

    public Customer(String fullName) {
        this.id = generateCustomerID();
        this.fullName = fullName;
        this.claims = new ArrayList<Claim>();
    }

    public Customer(String id, String fullName) {
        this.id = id;
        this.fullName = fullName;
        this.claims = new ArrayList<Claim>();
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public InsuranceCard getInsuranceCard() {
        return insuranceCard;
    }

    public void setInsuranceCard(InsuranceCard insuranceCard) {
        this.insuranceCard = insuranceCard;
    }

    // Generate a random customer ID with the format "cxxxxxxx"
    public String generateCustomerID() {
        StringBuilder CustomerIDBuilder = new StringBuilder("c");
        for (int i = 0; i < 7; i++) {
            CustomerIDBuilder.append((int) (Math.random() * 10));
        }
        return CustomerIDBuilder.toString();
    }

    // toString method to format the customer object to a string
    @Override
    public String toString() {
        String insuranceCardDetails = (insuranceCard != null) ? insuranceCard.toString() : "No Insurance Card";
        return id + "," + fullName + "," + "\"" + insuranceCardDetails + "\"";
    }
}
