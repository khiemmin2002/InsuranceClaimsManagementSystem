import java.util.List;
import java.util.Scanner;

public class MenuService {
    private final Scanner scanner;
    private final Menu mainMenu;

    public MenuService(Menu mainMenu) {
        this.scanner = new Scanner(System.in);
        this.mainMenu = mainMenu;
    }

    public void displayMenu() {
        System.out.println(mainMenu.getTitle());
        while (true) {
            System.out.println("\nMain Menu:");
            int i = 1;
            for (MenuItem item : mainMenu.getItems()) {
                System.out.println(i + ". " + item.getName());
                i++;
            }
            System.out.println(i + ". Quit");

            int choice = getUserChoice(i);
            if (choice == i) {
                // Quit option
                System.out.println("Exiting program...");
                scanner.close();
                break;
            } else {
                MenuItem selectedItem = mainMenu.getItems().get(choice - 1);
                if (selectedItem.hasSubMenu()) {
                    displaySubMenu(selectedItem);
                } else {
                    System.out.println("Selected: " + selectedItem.getName());
                    // Here you can add specific actions for each menu item if needed
                }
                // After action, loop will automatically go back to main menu
            }
        }
    }

    private void displaySubMenu(MenuItem menuItem) {
        System.out.println("\n" + menuItem.getName() + ":");
        int i = 1;
        for (MenuItem item : menuItem.getSubMenu()) {
            System.out.println(i + ". " + item.getName());
            i++;
        }
        System.out.println(i + ". Return to previous page");

        int choice = getUserChoice(i);
        if (choice < i) {
            MenuItem selectedItem = menuItem.getSubMenu().get(choice - 1);
            System.out.println("Selected: " + selectedItem.getName());
            // Here you can add specific actions for sub-menu items if needed
            // After action, we return to the main menu
            displayMenu();
        }
        // If 'Return to previous page' is selected, do nothing as it will automatically go back to main menu
    }

    private int getUserChoice(int maxOption) {
        int choice = 0;
        do {
            System.out.println("Choose an option (1-" + maxOption + "):");
            while (!scanner.hasNextInt()) {
                System.out.println("That's not a number!");
                scanner.next(); // this is important!
            }
            choice = scanner.nextInt();
        } while (choice < 1 || choice > maxOption);
        return choice;
    }
}


