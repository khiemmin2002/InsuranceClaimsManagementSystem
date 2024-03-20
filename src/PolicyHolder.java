import java.util.List;
import java.util.Scanner;

public class PolicyHolder extends Customer {
    private List<Dependent> dependents;

    public PolicyHolder() {
        super();
    }

    public PolicyHolder(String fullName, InsuranceCard insuranceCard) {
        // Assuming generateCustomerID does not take parameters and is correctly implemented in Customer.
        super(fullName, insuranceCard);
    }


    public static void addPolicyHolder() {
        Scanner scanner = new Scanner(System.in);
        // Create a new policyholder
        System.out.print("Enter the policyholder's full name: ");
        String fullName = scanner.nextLine();
        InsuranceCard insuranceCard = InsuranceCard.addInsuranceCard(fullName);

        // Create a new PolicyHolder object
        PolicyHolder policyHolder = new PolicyHolder(fullName, insuranceCard);
        System.out.println("Policyholder added successfully!");
        System.out.println(policyHolder);


    }

    @Override
    public String toString() {
        return String.format("Customer ID: %s, Policy Holder Name: %s\nInsurance Card: %s", getId(), getFullName(), getInsuranceCard().toString());
    }


}
