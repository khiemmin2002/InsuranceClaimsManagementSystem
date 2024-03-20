import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Min Chi Gia Khiem - S3878280
 */



class Main {
    public static void main(String[] args) {
        /*

        Menu insuranceClaimsMenu = new Menu();
        insuranceClaimsMenu.setTitle("Welcome to Insurance Claims Management System");

        // Add menu items
        MenuItem customer = new MenuItem("Customer");
        customer.setSubMenuTitle("\nCustomer Operations");
        customer.addSubMenu(new MenuItem("Add Customer"));
        customer.addSubMenu(new MenuItem("View Customer"));
        customer.addSubMenu(new MenuItem("Update Customer"));
        customer.addSubMenu(new MenuItem("Delete Customer"));
        insuranceClaimsMenu.addItem(customer);

        MenuItem admin = new MenuItem("Admin");
        admin.addSubMenu(new MenuItem("Customer"));
        // In a claims, we have 4 more options
        MenuItem claims = new MenuItem("Claims");
        claims.setSubMenuTitle("\nClaims Management");
        claims.addSubMenu(new MenuItem("Add Claims"));
        claims.addSubMenu(new MenuItem("View Claims"));
        claims.addSubMenu(new MenuItem("Update Claims"));
        claims.addSubMenu(new MenuItem("Delete Claims"));
        admin.addSubMenu(claims);
        insuranceClaimsMenu.addItem(admin);

        // Create an instance of MenuService to handle the display and interaction
        MenuService insuranceClaimsMenuService = new MenuService(insuranceClaimsMenu);

        // Display the menu to the user and handle their choices
        insuranceClaimsMenuService.start();

        */

        // Create insurance card
        InsuranceCard.addInsuranceCard();
    }
}