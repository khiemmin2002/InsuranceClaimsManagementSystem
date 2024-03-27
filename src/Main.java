import models.*;
import controllers.*;
import views.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Min Chi Gia Khiem - S3878280
 */



class Main {
    public static void main(String[] args) {


//        Menu insuranceClaimsMenu = new Menu();
//        insuranceClaimsMenu.setTitle("Welcome to the Insurance Claims Management System");
//
//        // Add menu items
//        MenuItem customer = new MenuItem("Customer");
//        customer.setSubMenuTitle("\nCustomer Operations");
//        customer.addSubMenu(new MenuItem("Add Policy Holder"));
//        customer.addSubMenu(new MenuItem("View Policy Holder"));
//        customer.addSubMenu(new MenuItem("Update Policy Holder"));
//        customer.addSubMenu(new MenuItem("Delete Policy Holder"));
//        customer.addSubMenu(new MenuItem("Add Dependent"));
//        customer.addSubMenu(new MenuItem("View Dependent"));
//        customer.addSubMenu(new MenuItem("Update Dependent"));
//        customer.addSubMenu(new MenuItem("Delete Dependent"));
//        insuranceClaimsMenu.addItem(customer);
//
//        MenuItem admin = new MenuItem("Admin");
//        admin.addSubMenu(new MenuItem("Customer"));
//        // In a claims, we have 4 more options
//        MenuItem claims = new MenuItem("Claims");
//        claims.setSubMenuTitle("\nClaims Management");
//        claims.addSubMenu(new MenuItem("Add Claims"));
//        claims.addSubMenu(new MenuItem("View Claims"));
//        claims.addSubMenu(new MenuItem("Update Claims"));
//        claims.addSubMenu(new MenuItem("Delete Claims"));
//        admin.addSubMenu(claims);
//        insuranceClaimsMenu.addItem(admin);
//
//        // Create an instance of MenuService to handle the display and interaction
//        MenuService insuranceClaimsMenuService = new MenuService(insuranceClaimsMenu);
//
//        // Display the menu to the user and handle their choices
//        insuranceClaimsMenuService.start();

        InsuranceCardView view = new InsuranceCardView();

        // Create a new InsuranceCard model here if you choose to keep the original constructor
        InsuranceCard model = new InsuranceCard(); // Default constructor or provide parameters as needed

        InsuranceCardController controller = new InsuranceCardController(model, view);

        // Proceed with adding a new insurance card
        controller.addInsuranceCard();





    }
}