public class Customer {
    private String id;
    private String fullName;
    private InsuranceCard insuranceCard;

    public Customer() {
    }

    public Customer(String id, String fullName, InsuranceCard insuranceCard) {
        this.id = "Default ID";
        this.fullName = "Default Name";
        this.insuranceCard = new InsuranceCard();
    }

    public Customer(String fullName, InsuranceCard insuranceCard) {
        this.fullName = fullName;
        this.insuranceCard = insuranceCard;
    }

    public String generateCustomerID() {
        //
    }
}
