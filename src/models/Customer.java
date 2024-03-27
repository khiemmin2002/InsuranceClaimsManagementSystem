package models;

public abstract class Customer {
    private String id;
    private String fullName;
    private InsuranceCard insuranceCard;

    // Default constructor with default values
    public Customer() {
        this.id = generateCustomerID(); // Generate ID for every Customer instance
        this.fullName = "Default";
        this.insuranceCard = new InsuranceCard();
    }

    public Customer(String fullName, InsuranceCard insuranceCard) {
        this.id = generateCustomerID(); // Generate ID for every Customer instance
        this.fullName = fullName;
        this.insuranceCard = insuranceCard;
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

    public String generateCustomerID() {
        StringBuilder idBuilder = new StringBuilder("c");
        for (int i = 0; i < 7; i++) {
            idBuilder.append((int) (Math.random() * 10));
        }
        return idBuilder.toString();
    }

}
