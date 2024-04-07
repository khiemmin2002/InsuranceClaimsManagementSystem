/**
 * @author Min Chi Gia Khiem - S3878280
 */

import models.*;
import controllers.*;
import system.*;
import views.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * RMIT University Vietnam - Assignment 1
 * @author <Min Chi Gia Khiem - S3878280>
 * @version 1.0
 * @since 04/05/2024
 */

public class Main {
    public static void main(String[] args) throws IOException {
        // Setup Lists
        InsuranceList insuranceList = new InsuranceList("data/Insurance.txt");
        insuranceList.loadFromFile();

        PolicyHolderList policyHolderList = new PolicyHolderList("data/PolicyHolder.txt");
        policyHolderList.loadFromFile();

        DependentList dependentList = new DependentList("data/Dependent.txt");
        dependentList.loadFromFile();

        ClaimList claimList = new ClaimList("data/Claim.txt");
        claimList.loadFromFile();

        // Setup Views
        InsuranceCardView insuranceCardView = new InsuranceCardView();
        PolicyHolderView policyHolderView = new PolicyHolderView();
        ClaimView claimView = new ClaimView();

        // Setup Controllers
        InsuranceCardController insuranceCardController = new InsuranceCardController(insuranceCardView, insuranceList);
        PolicyHolderController policyHolderController = new PolicyHolderController(policyHolderView, policyHolderList, insuranceList , dependentList, claimList);
        ClaimController claimController = new ClaimController(claimView, claimList, policyHolderList);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Insurance Claims Management System!");
        // Main menu
        while (true) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Customer");
            System.out.println("2. Claims");
            System.out.println("3. Insurance Card");
            System.out.println("4. Exit");
            System.out.print("\nChoose an option: ");

            int choice = getIntInput(scanner);

            switch (choice) {
                case 1:
                    customerMenu(scanner, policyHolderController);
                    break;
                case 2:
                    claimMenu(scanner, claimController);
                    break;
                case 3:
                    insuranceCardMenu(scanner, insuranceCardController);
                    break;
                case 4:
                    System.out.println("Exiting application...");
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    // Customer menu
    private static void customerMenu(Scanner scanner, PolicyHolderController policyHolderController) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- Customer Menu ---");
            System.out.println("1. Add Policy Holder");
            System.out.println("2. View Policy Holders");
            System.out.println("3. Delete Policy Holder");
            System.out.println("4. Add Dependent");
            System.out.println("5. Delete Dependent");
            System.out.println("6. Assign Insurance Card");
            System.out.println("7. Back");
            System.out.print("\nSelect an option: ");

            int choice = getIntInput(scanner);

            switch (choice) {
                case 1:
                    // Add policy holder
                    try {
                        policyHolderController.addPolicyHolder();
                    } catch (IOException e) {
                        System.err.println("An error occurred while saving the policy holder.");
                    }
                    break;
                case 2:
                    // View policy holder
                    policyHolderController.displayAllPolicyHolders();
                    break;
                case 3:
                    // Delete policy holder
                    policyHolderController.deletePolicyHolder();
                    break;
                case 4:
                    // Add dependent
                    policyHolderController.addDependentToPolicyHolder();
                    break;
                case 5:
                    // Delete dependent
                    policyHolderController.deleteDependentFromPolicyHolder();
                    break;
                case 6:
                    // Assign insurance card
                    policyHolderController.assignInsuranceCardToPolicyHolder();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    // Insurance card menu
    private static void insuranceCardMenu(Scanner scanner, InsuranceCardController insuranceCardController) {
        while (true) {
            System.out.println("\n--- Insurance Card Menu ---");
            System.out.println("1. Add Insurance Card");
            System.out.println("2. View Insurance Cards");
            System.out.println("3. Update Insurance Card");
            System.out.println("4. Delete Insurance Card");
            System.out.println("5. Back");
            System.out.print("\nSelect an option: ");

            int choice = getIntInput(scanner);

            switch (choice) {
                case 1:
                    try {
                        insuranceCardController.addInsuranceCard();
                    } catch (IOException e) {
                        System.err.println("An error occurred while saving the insurance card.");
                    }
                    break;
                case 2:
                    insuranceCardController.displayAllInsuranceCards();
                    break;
                case 3:
                    insuranceCardController.updateInsuranceCard();
                    break;
                case 4:
                    insuranceCardController.deleteInsuranceCard();
                    break;
                case 5:
                    return; // Go back to the main menu
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    // Claim menu
    private static void claimMenu(Scanner scanner, ClaimController claimController) {
        while (true) {
            System.out.println("\n--- Claim Menu ---");
            System.out.println("1. Add Claim");
            System.out.println("2. View Claims");
            System.out.println("3. View a Claim");
            System.out.println("4. Update Claim");
            System.out.println("5. Delete Claim");
            System.out.println("6. Back");
            System.out.print("\nSelect an option: ");

            int choice = getIntInput(scanner);

            switch (choice) {
                case 1:
                    claimController.add();
                    break;
                case 2:
                    claimController.getAll();
                    break;
                case 3:
                    claimController.getOne();
                    break;
                case 4:
                    claimController.update();
                    break;
                case 5:
                    claimController.delete();
                    break;
                case 6:
                    return; // Go back to the main menu
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    // Method to ensure only integer input is accepted
    private static int getIntInput(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.next(); // discard the incorrect input
                System.out.print("Invalid input, please enter an integer: ");
            }
        }
    }
}