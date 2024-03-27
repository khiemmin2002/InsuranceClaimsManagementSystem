package controllers;

import models.InsuranceCard;
import views.InsuranceCardView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class InsuranceCardController {
    private InsuranceCard insuranceCardModel;
    private InsuranceCardView insuranceCardView;

    public InsuranceCardController(InsuranceCard insuranceCardModel, InsuranceCardView insuranceCardView) {
        this.insuranceCardModel = insuranceCardModel;
        this.insuranceCardView = insuranceCardView;
    }

    public void addInsuranceCard() {
        insuranceCardView.displayMessage("Adding an insurance card...");

        String cardHolder = insuranceCardView.promptForInput("Enter the card holder's name: ");
        String policyOwner = insuranceCardView.promptForInput("Enter the policy owner's name: ");

        int year = promptForYear();
        int month = promptForMonth();
        int day = promptForDay(year, month);

        Date expiredDate = null;
        try {
            expiredDate = new SimpleDateFormat("MM/dd/yyyy").parse(String.format("%02d/%02d/%04d", month, day, year));
        } catch (Exception e) {
            insuranceCardView.displayMessage("Failed to parse the date!");
        }

        InsuranceCard insuranceCardModel = new InsuranceCard(cardHolder, policyOwner, expiredDate);

        insuranceCardView.displayInsuranceCardDetails(
                insuranceCardModel.getCardNum(),
                insuranceCardModel.getCardHolder(),
                insuranceCardModel.getPolicyOwner(),
                insuranceCardModel.getFormattedExpiredDate()
        );
    }

    // Methods to handle date input

    // Prompt for year input, the year must larger than the current year and can only accept numeric input
    public int promptForYear() {
        while (true) {
            try {
                int year = Integer.parseInt(insuranceCardView.promptForInput("Enter the expired year (yyyy): "));
                if (year >= Calendar.getInstance().get(Calendar.YEAR)) {
                    return year;
                } else {
                    insuranceCardView.displayMessage("Invalid year, please enter a year in the future.");
                }
            } catch (NumberFormatException e) {
                insuranceCardView.displayMessage("Please enter a valid numeric year.");
            }
        }
    }

    // Prompt for month input, the month must be between 1 and 12
    public int promptForMonth() {
        while (true) {
            try {
                int month = Integer.parseInt(insuranceCardView.promptForInput("Enter the expired month (mm): "));
                if (month >= 1 && month <= 12) {
                    return month;
                } else {
                    insuranceCardView.displayMessage("Invalid month, please enter a month between 1 and 12.");
                }
            } catch (NumberFormatException e) {
                insuranceCardView.displayMessage("Please enter a valid numeric month.");
            }
        }
    }

    // Prompt for day input, the day must be valid for the given month and year
    public int promptForDay(int year, int month) {
        while (true) {
            try {
                int day = Integer.parseInt(insuranceCardView.promptForInput("Enter the expired day (dd): "));
                if (isValidDay(year, month, day)) {
                    return day;
                } else {
                    insuranceCardView.displayMessage("Invalid day for the given month and year, please enter a valid day.");
                }
            } catch (NumberFormatException e) {
                insuranceCardView.displayMessage("Please enter a valid numeric day.");
            }
        }
    }

    public boolean isValidDay(int year, int month, int day) {
        if (day < 1 || day > 31) {
            return false;
        }
        if (month == 2) {
            if (year % 4 == 0) {
                return day <= 29;
            } else {
                return day <= 28;
            }
        }
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            return day <= 30;
        }
        return true;
    }
}
