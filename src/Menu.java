import java.util.ArrayList;
import java.util.List;

public class Menu {
    private String title; // Title of the menu
    private List<MenuItem> items; // List of menu items

    public Menu() {
        this.items = new ArrayList<>();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    // Adds a MenuItem to the menu
    public void addItem(MenuItem item) {
        items.add(item);
    }

    // Retrieves the list of menu items
    public List<MenuItem> getItems() {
        return items;
    }
}
