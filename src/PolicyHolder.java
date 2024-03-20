import java.util.List;

public class PolicyHolder extends Customer {
    private List<Dependent> dependents;

    public PolicyHolder() {
        super();
    }

    public PolicyHolder(String fullName, InsuranceCard insuranceCard) {
        super(fullName, insuranceCard);
    }


}
