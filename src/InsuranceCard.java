import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.IntPredicate;

public class InsuranceCard {
    private String cardNum;
    private String cardHolder;
    private String policyOwner;
    private Date expiredDate;

    public InsuranceCard() {
    }

    public InsuranceCard(String cardHolder, String policyOwner, Date expiredDate) {
        this.cardHolder = cardHolder;
        this.policyOwner = policyOwner;
        this.expiredDate = expiredDate;
        this.cardNum = generateCardNum();
    }

    // Set the card number whenever the card is created
    // The card number is 10 digits long, generate randomly, and unique (The num can start with 0)
    // This method now returns a String that represents the card number
    public String generateCardNum() {
        StringBuilder cardNumBuilder = new StringBuilder();
        // Ensure the first digit can be '0'
        cardNumBuilder.append((int) (Math.random() * 10));

        // Now proceed with the rest of the digits
        for (int i = 1; i < 10; i++) { // Start from 1 since we've already added the first digit
            cardNumBuilder.append((int) (Math.random() * 10));
        }
        return cardNumBuilder.toString();
    }

    // Format the expired date to mm/dd/yyyy
    public String getFormattedExpiredDate() {
        if (expiredDate == null) {
            return "Date is not set";
        }
        return new SimpleDateFormat("MM/dd/yyyy").format(expiredDate);
    }

    // Add an insurance card
    public static InsuranceCard addInsuranceCard(String cardHolder) {
        // Create insurance card

        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter the policy owner's name
        System.out.print("Enter the policy owner's name: ");
        String policyOwner = scanner.nextLine();

        // Prompt the user to enter the expired date

        // Get the current year to compare with the input year
        // To ensure the year is not in the past (< current year)
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        int year = promptForIntInput(scanner, "Enter the year of the expired date (yyyy): ", n -> n >= currentYear, "Invalid year. The year must be greater than or equal to " + currentYear);

        int month = promptForIntInput(scanner, "Enter the month of the expired date (mm): ", n -> n >= 1 && n <= 12, "Invalid month. Please enter a number from 1 to 12");

        int day = promptForIntInput(scanner, "Enter the day of the expired date (dd): ", n -> isValidDay(month, n, year), "Invalid day. Please enter a valid day for the month");

        Date expiredDate = handleDateInput(year, month, day);
        return new InsuranceCard(cardHolder, policyOwner, expiredDate);
    }

    private static int promptForIntInput(Scanner scanner, String prompt, IntPredicate valid, String errorMessage) {
        int input = 0;
        boolean validInput = false;
        while (!validInput) {
            System.out.print(prompt);
            try {
                input = scanner.nextInt();
                if (valid.test(input)) {
                    validInput = true;
                } else {
                    System.out.println(errorMessage);
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid integer.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
        return input;
    }

    // Handling date input mm/dd/yyyy
    public static Date handleDateInput(int year, int month, int day) {
        Date expiredDate = null;
        try {
            expiredDate = new SimpleDateFormat("MM/dd/yyyy").parse(String.format("%02d/%02d/%04d", month, day, year));
        } catch (ParseException e) {
            System.err.println("Invalid date format");
        }
        return expiredDate;
    }

    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    private static boolean isValidDay(int month, int day, int year) {
        return switch (month) {
            case 2 -> // February
                    isLeapYear(year) ? day >= 1 && day <= 29 : day >= 1 && day <= 28;
            case 4, 6, 9, 11 -> // April, June, September, November
                    day >= 1 && day <= 30;
            default -> // January, March, May, July, August, October, December
                    day >= 1 && day <= 31;
        };
    }

    @Override
    public String toString() {
        return String.format ("Card Number: %s, Card Holder: %s, Policy Owner: %s, Expired Date: %s", cardNum, cardHolder, policyOwner, getFormattedExpiredDate());
    }
}
