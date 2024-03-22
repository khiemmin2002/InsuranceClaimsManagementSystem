import java.util.List;
import java.util.Scanner;

public class MenuService {
    private final Scanner scanner;
    private final Menu mainMenu;

    public MenuService(Menu mainMenu) {
        this.scanner = new Scanner(System.in);
        this.mainMenu = mainMenu;
    }

    public void displayMenu(Menu menu) {
        System.out.println(menu.getTitle());
        while (true) {

            int i = 1;
            System.out.println("\nChoose an option:");
            for (MenuItem item : menu.getItems()) {
                System.out.println(i + ". " + item.getName());
                i++;
            }
            System.out.println(i + ". " + (menu == mainMenu ? "Quit" : "Return to previous page"));

            int choice = getUserChoice(i);
            if (choice == i && menu == mainMenu) {
                handleQuitOption();
                return; // Quit the application
            } else if (choice == i) {
                return; // Return to previous menu, exit this method invocation
            }

            MenuItem selectedItem = menu.getItems().get(choice - 1);
            if (selectedItem.hasSubMenu()) {
                displayMenu(selectedItem.getSubMenu()); // Recursive call for sub-menus
            } else {
                System.out.println("\nYou have selected: " + selectedItem.getName() + "\n");
                // Add the logic to handle the selected option here
                if (selectedItem.getName().equals("Add Customer")) {
                    PolicyHolder.addPolicyHolder();
                }
            }
            // If we are in a sub-menu, loop will allow choosing another option in the same sub-menu
            // If "Return to previous page" is selected, we exit this method invocation and go back up one level
        }
    }

    private int getUserChoice(int maxOption) {
        int choice = 0;
        do {
            System.out.print("\nChoose an option (1-" + maxOption + "): ");
            while (!scanner.hasNextInt()) {
                System.out.println("That's not a number!");
                scanner.next();
            }
            choice = scanner.nextInt();
        } while (choice < 1 || choice > maxOption);
        return choice;
    }

    private void handleQuitOption() {
        System.out.println("Are you sure you want to quit? (Y/N)");
        String confirm = scanner.next();
        if (confirm.equalsIgnoreCase("Y")) {
            System.out.println("Exiting program...");
            try {
                Thread.sleep(2000);
                scanner.close();
                System.out.println("Goodbye!");
                System.exit(0);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Properly handle the InterruptedException
                System.err.println("The sleep was interrupted");
            }
        }
    }

    // Entry point to start displaying the main menu
    public void start() {
        displayMenu(this.mainMenu);
    }
}
