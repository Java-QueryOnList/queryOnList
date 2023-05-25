package testHelpers.genericClasses.classDecleration.shoppingList;

import java.util.List;

public class ShoppingList {
    private String name;
    private List<Item> items;
    private boolean isCompleted;

    public ShoppingList(String name, List<Item> items) {
        this.name = name;
        this.items = items;
        this.isCompleted = false;
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
        private String name;
        private int quantity;

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

