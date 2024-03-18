/**
 * @author Min Chi Gia Khiem - S3878280
 */



class Main {
    public static void main(String[] args) {
        Menu insuranceClaimsMenu = new Menu();
        insuranceClaimsMenu.setTitle("Welcome to Insurance Claims Management System");
        insuranceClaimsMenu.addItem(new MenuItem("Customer"));
        insuranceClaimsMenu.addItem(new MenuItem("User"));

        // Create an instance of MenuService to handle the display and interaction
        MenuService insuranceClaimsMenuService = new MenuService(insuranceClaimsMenu);

        // Display the menu to the user and handle their choices
        insuranceClaimsMenuService.displayMenu();
    }
}