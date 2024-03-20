public abstract class Customer {
    private String id;
    private String fullName;
    private InsuranceCard insuranceCard;

    public Customer() {
        this.id = generateCustomerID(); // Generate ID for every Customer instance
    }

    public Customer(String fullName, InsuranceCard insuranceCard) {
        this.id = generateCustomerID(); // Generate ID for every Customer instance
        this.fullName = fullName;
        this.insuranceCard = insuranceCard;
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

    public String generateCustomerID() {
        StringBuilder idBuilder = new StringBuilder("c");
        for (int i = 0; i < 7; i++) {
            idBuilder.append((int) (Math.random() * 10));
        }
        return idBuilder.toString();
    }
}
