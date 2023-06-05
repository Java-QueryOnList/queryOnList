package testHelpers.genericClasses.classDecleration.shoppingList;

import java.util.List;

public class ShoppingList {
    final private String name;
    final private List<Item> items;
    private boolean isCompleted;

    public ShoppingList(String name, List<Item> items) {
        this.name = name;
        this.items = items;
        this.isCompleted = (items.isEmpty());
    }

    public String getName() {
        return name;
    }

    public List<Item> getItems() {
        return items;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void SetIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public static class Item {
        final private String name;
        final private int quantity;

        public Item(String name, int quantity) {
            this.name = name;
            this.quantity = quantity;
        }

        public String getName() {
            return name;
        }

        public int getQuantity() {
            return quantity;
        }
    }
}

