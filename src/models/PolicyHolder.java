//package models;
//
//import java.io.*;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Scanner;
//
//public class PolicyHolder extends Customer implements FileDataService {
//    private List<Dependent> dependents;
//    private static List<PolicyHolder> policyHolders = new ArrayList<>();
//    private static final String POLICY_HOLDERS_PATH = "./src/Database/PolicyHolder.txt";
//
//    public PolicyHolder() {
//        super();
//        dependents = new ArrayList<Dependent>();
//    }
//
//    public PolicyHolder(String fullName, InsuranceCard insuranceCard) {
//        super(fullName, insuranceCard);
//    }
//
//
//    public static void addPolicyHolder() {
//        Scanner scanner = new Scanner(System.in);
//        // Create a new policyholder
//        System.out.print("Enter the policyholder's full name: ");
//        String fullName = scanner.nextLine();
//        InsuranceCard insuranceCard = InsuranceCard.addInsuranceCard(fullName);
//
//        // Create a new PolicyHolder object
//        PolicyHolder policyHolder = new PolicyHolder(fullName, insuranceCard);
//        System.out.println("\nPolicyholder added successfully!");
////        policyHolder.writeDataToFile();
//    }
//
//    public static void displayPolicyHolders() {
//        PolicyHolder policyHolder = new PolicyHolder();
//        System.out.println(policyHolder.readDataToFile());
//    }
//
//    /**
//     * Now we need to implement a few methods to handle the dependents, each policyholder can have multiple dependents.
//     * Now when add a dependent, we need to assign the Insurance Card of the policyholder to the dependent.
//     * The insurance can be shared among the dependents and the policyholder.
//     * @return the dependents
//     */
//    public List<Dependent> getDependents() {
//        return dependents;
//    }
//
//    public void setDependents(Dependent newDependents) {
//        dependents.add(newDependents);
//    }
//
//    //
//
//
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(String.format("Customer ID: %s, Policy Holder Name: %s\nInsurance Card: %s\nDependents:\n\n",
//                getId(), getFullName(), getInsuranceCard().toString()));
//
//        if (dependents != null && !dependents.isEmpty()) {
//            for (Dependent dep : dependents) {
//                sb.append(dep.toString()).append("\n\n");
//            }
//        } else {
//            sb.append("No dependents\n");
//        }
//        // Add ------ to finish print a policyholder
//        sb.append("---------------------------\n");
//
//        return sb.toString();
//    }
//
//    @Override
//    public void writeDataToFile() {
//        String filePath = "./src/Database/PolicyHolder.txt";
//        String content = String.format("%s, %s, %s, %s, %s, %s",
//                getId(), getFullName(), // Customer fields
//                // Insurance card fields
//                getInsuranceCard().getCardNum(),
//                getInsuranceCard().getCardHolder(),
//                getInsuranceCard().getPolicyOwner(),
//                getInsuranceCard().getFormattedExpiredDate());
//        try  {
//            FileWriter fileWriter = new FileWriter(filePath, true);// true to append to the file
//            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
//            bufferedWriter.write(content);
//            bufferedWriter.newLine();
//            bufferedWriter.close();
//            fileWriter.close();
//        } catch (IOException e) {
//            System.err.println("An error occurred while writing to the file.");
//        }
//    }
//
//    @Override
//    public String readDataToFile() {
//        StringBuilder stringBuilder = new StringBuilder();
//
//        if (!isFileExists()) {
//            return "Cannot find the file for reading.";
//        }
//
//        if (isFileEmpty()) {
//            return "The file is empty.";
//        }
//
//        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(POLICY_HOLDERS_PATH))) {
//            String line;
//            System.out.println("Displaying all policy holders\n");
//            while ((line = bufferedReader.readLine()) != null) {
//                String[] policyHolderInfo = line.split(", ");
//                stringBuilder.append(String.format(
//                        "Customer ID: %s, Policy Holder Name: %s\nInsurance Card: Card Number: %s, Card Holder: %s, Policy Owner: %s, Expired Date: %s\n\n",
//                        policyHolderInfo[0], policyHolderInfo[1], policyHolderInfo[2], policyHolderInfo[3], policyHolderInfo[4], policyHolderInfo[5])
//                );
//            }
//        } catch (IOException e) {
//            System.err.println("An error occurred while reading the file.");
//        }
//        return stringBuilder.toString();
//    }
//
//    @Override
//    public void deleteDataToFile() {
//
//    }
//
//    @Override
//    public void updateDataToFile() {
//
//    }
//
//    // Check if the file exists
//    @Override
//    public boolean isFileExists() {
//        File file = new File(POLICY_HOLDERS_PATH);
//        return file.exists();
//    }
//
//    @Override
//    public boolean isFileEmpty() {
//        File file = new File(POLICY_HOLDERS_PATH);
//        return file.length() == 0;
//    }
//
//    @Override
//    public boolean isDuplicate(String data) {
//        return false;
//    }
//
//    public static void addDependentToPolicyHolder(String insuranceCardNumber, String dependentFullName) {
//        // Assuming you have a method to find a PolicyHolder by insurance card number
//        PolicyHolder policyHolder = findPolicyHolderByInsuranceCardNumber(insuranceCardNumber);
//        if (policyHolder != null) {
//            InsuranceCard insuranceCard = policyHolder.getInsuranceCard();
//            Dependent newDependent = new Dependent(dependentFullName, insuranceCard);
//            policyHolder.addDependent(newDependent);
//            System.out.println("\nDependent added successfully!");
//            System.out.println(policyHolder);
//        } else {
//            System.out.println("No policy holder found with the given insurance card number.");
//        }
//    }
//
//
//    public static void loadPolicyHoldersFromFile() {
//        policyHolders = new ArrayList<>();
//        try (BufferedReader reader = new BufferedReader(new FileReader(POLICY_HOLDERS_PATH))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] details = line.split(", ");
//                // Assuming the constructor of InsuranceCard and PolicyHolder can handle these details appropriately
//                InsuranceCard insuranceCard = new InsuranceCard(details[2], details[3], details[4], handleDateFromFile(details[5]));
//                PolicyHolder policyHolder = new PolicyHolder(details[1], insuranceCard);
//                policyHolder.setId(details[0]); // Assuming you have a setter for ID or modify the constructor
//                policyHolders.add(policyHolder);
//            }
//        } catch (IOException e) {
//            System.err.println("An error occurred while loading policy holders from the file: " + e.getMessage());
//        }
//    }
//
//    // Handle the string date MM/dd/yyyy to Date object
//    public static Date handleDateFromFile(String dateString) {
//        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
//        try {
//            return formatter.parse(dateString); // Output the date
//        } catch (ParseException e) {
//            System.err.println("The date format is invalid: " + e.getMessage());
//        }
//        return null;
//    }
//
//    public static PolicyHolder findPolicyHolderByInsuranceCardNumber(String insuranceCardNumber) {
//        // This method assumes you have a collection of PolicyHolder objects to search through.
//        for (PolicyHolder policyHolder : policyHolders) { // policyHolders being a collection of PolicyHolder objects
//            if (policyHolder.getInsuranceCard().getCardNum().equals(insuranceCardNumber)) {
//                return policyHolder;
//            }
//        }
//        return null; // Return null if no match is found
//    }
//
//    public void addDependent(Dependent newDependent) {
//        if (this.dependents == null) {
//            this.dependents = new ArrayList<Dependent>(); // Initialize the list if it's null
//        }
//        this.dependents.add(newDependent);
//    }
//
//}
