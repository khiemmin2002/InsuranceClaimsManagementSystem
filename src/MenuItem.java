public class MenuItem {
    private final String name;
    private final Menu subMenu; // Sub-menu is correctly typed as Menu

    public MenuItem(String name) {
        this.name = name;
        this.subMenu = new Menu(); // Initialize the submenu
    }

    // Add a MenuItem to the submenu
    public void addSubMenu(MenuItem item) {
        this.subMenu.addItem(item); // Use Menu's addItem method to add a MenuItem
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Return the submenu itself, not a list of items
    public Menu getSubMenu() {
        return subMenu;
    }

    // Check if the submenu has items
    public boolean hasSubMenu() {
        return !subMenu.getItems().isEmpty();
    }

    public void setSubMenuTitle(String title) {
        this.subMenu.setTitle(title);
    }
}
