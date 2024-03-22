import java.util.Scanner;

public class Dependent extends Customer {
    private PolicyHolder policyHolder;

    public Dependent() {
        super();
    }

    public Dependent(String fullName, InsuranceCard insuranceCard) {
        super(fullName, insuranceCard);
    }

    public PolicyHolder getPolicyHolder() {
        return policyHolder;
    }

    public void setPolicyHolder(PolicyHolder policyHolder) {
        this.policyHolder = policyHolder;
    }



    @Override
    public String toString() {
        return String.format("Customer ID: %s, Dependent Name: %s\nInsurance Card: %s", getId(), getFullName(), getInsuranceCard().toString());
    }
}
