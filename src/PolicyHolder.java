import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PolicyHolder extends Customer implements FileDataService {
    private List<Dependent> dependents = new ArrayList<>();
    private static final String POLICY_HOLDERS_PATH = "./src/Database/PolicyHolder.txt";

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
        policyHolder.writeDataToFile();
    }

    public static void displayPolicyHolders() {
        PolicyHolder policyHolder = new PolicyHolder();
        System.out.println(policyHolder.readDataToFile());
    }



    @Override
    public String toString() {
        return String.format("Customer ID: %s, Policy Holder Name: %s\nInsurance Card: %s", getId(), getFullName(), getInsuranceCard().toString());
    }

    @Override
    public void writeDataToFile() {
        String filePath = "./src/Database/PolicyHolder.txt";
        String content = String.format("%s, %s, %s, %s, %s, %s",
                getId(), getFullName(), // Customer fields
                // Insurance card fields
                getInsuranceCard().getCardNum(),
                getInsuranceCard().getCardHolder(),
                getInsuranceCard().getPolicyOwner(),
                getInsuranceCard().getFormattedExpiredDate());
        try  {
            FileWriter fileWriter = new FileWriter(filePath, true);// true to append to the file
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(content);
            bufferedWriter.newLine();
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file.");
        }
    }

    @Override
    public String readDataToFile() {
        StringBuilder stringBuilder = new StringBuilder();

        if (!isFileExists()) {
            return "Cannot find the file for reading.";
        }

        if (isFileEmpty()) {
            return "The file is empty.";
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(POLICY_HOLDERS_PATH))) {
            String line;
            System.out.println("Displaying all policy holders\n");
            while ((line = bufferedReader.readLine()) != null) {
                String[] policyHolderInfo = line.split(", ");
                stringBuilder.append(String.format("Customer ID: %s, Policy Holder Name: %s\nInsurance Card: Card Number: %s, Card Holder: %s, Policy Owner: %s, Expired Date: %s\n\n",
                        policyHolderInfo[0], policyHolderInfo[1], policyHolderInfo[2], policyHolderInfo[3], policyHolderInfo[4], policyHolderInfo[5]));
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file.");
        }
        return stringBuilder.toString();
    }

    @Override
    public void deleteDataToFile() {

    }

    @Override
    public void updateDataToFile() {

    }

    // Check if the file exists
    @Override
    public boolean isFileExists() {
        File file = new File(POLICY_HOLDERS_PATH);
        return file.exists();
    }

    @Override
    public boolean isFileEmpty() {
        File file = new File(POLICY_HOLDERS_PATH);
        return file.length() == 0;
    }

    @Override
    public boolean isDuplicate(String data) {
        return false;
    }
}
