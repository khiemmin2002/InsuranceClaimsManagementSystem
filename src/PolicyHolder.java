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
        System.out.println("\nPolicyholder added successfully!");
        System.out.println(policyHolder);


    }

    public static void addDependent() {
        Scanner scanner = new Scanner(System.in);
        // Create a new dependent
        System.out.print("Enter the dependent's full name: ");
        String fullName = scanner.nextLine();
        InsuranceCard insuranceCard = InsuranceCard.addInsuranceCard(fullName);

        // Create a new Dependent object
        Dependent dependent = new Dependent(fullName, insuranceCard, null);
        System.out.println("\nDependent added successfully!");
        System.out.println(dependent);
    }

    // Assign a dependent to a policyholder which is defined by the policyholder's ID
    public static void assignDependentToPolicyHolder(List<PolicyHolder> policyHolders, List<Dependent> dependents) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Assign a dependent to a policyholder");
        System.out.print("Enter the policyholder's ID: ");
        String policyHolderId = scanner.nextLine();
        PolicyHolder policyHolder = findPolicyHolderById(policyHolders, policyHolderId);
        if (policyHolder == null) {
            System.out.println("Policyholder not found!");
            return;
        }
        System.out.print("Enter the dependent's ID: ");
        String dependentId = scanner.nextLine();
        Dependent dependent = findDependentById(dependents, dependentId);
        if (dependent == null) {
            System.out.println("Dependent not found!");
            return;
        }
        dependent.setPolicyHolder(policyHolder);
        policyHolder.getDependents().add(dependent);
        System.out.println("Dependent assigned successfully!");
    }

    @Override
    public String toString() {
        return String.format("Customer ID: %s, Policy Holder Name: %s\nInsurance Card: %s", getId(), getFullName(), getInsuranceCard().toString());
    }


}
