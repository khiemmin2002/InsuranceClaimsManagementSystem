import java.util.ArrayList;
import java.util.List;

public class MenuItem {
    private String name;
    private final List<MenuItem> subMenu;

    public MenuItem(String name) {
        this.name = name;
        this.subMenu = new ArrayList<>();
    }

    public void addSubMenu(MenuItem item) {
        subMenu.add(item);
    }

    public String getName() {
        return name;
    }

    public List<MenuItem> getSubMenu() {
        return subMenu;
    }

    public boolean hasSubMenu() {
        return !subMenu.isEmpty();
    }
}

