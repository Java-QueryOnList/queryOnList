package testHelpers.genericClasses.classObjects.shoppingList;

import testHelpers.genericClasses.classDecleration.shoppingList.ShoppingList;

import java.util.ArrayList;
import java.util.List;

public final class ShoppingListObjects {
    private static final List<ShoppingList> RAW_LIST = new ArrayList<>();

    static {
        initRawList();
    }

    public static List<ShoppingList> getRawList() {
        return RAW_LIST;
    }

    private static void initRawList() {
        // Create items
        ShoppingList.Item item1 = new ShoppingList.Item("Apple", 5);
        ShoppingList.Item item2 = new ShoppingList.Item("Banana", 3);
        ShoppingList.Item item3 = new ShoppingList.Item("Milk", 2);
        ShoppingList.Item item4 = new ShoppingList.Item("Bread", 1);
        ShoppingList.Item item5 = new ShoppingList.Item("Eggs", 12);

        // Create shopping lists
        ShoppingList list1 = new ShoppingList("List 1", List.of(item1, item2));
        ShoppingList list2 = new ShoppingList("List 2", List.of(item3, item4, item5));
        ShoppingList list3 = new ShoppingList("List 3", List.of(item1, item3, item5));
        ShoppingList list4 = new ShoppingList("List 4", List.of(item2, item4));
        ShoppingList list5 = new ShoppingList("List 5", new ArrayList<>());
        ShoppingList list6 = new ShoppingList("List 6", List.of(item1));
        ShoppingList list7 = new ShoppingList("List 7", List.of(item2, item3, item4, item5));
        ShoppingList list8 = new ShoppingList("List 8", List.of(item4));
        ShoppingList list9 = new ShoppingList("List 9", List.of(item1, item5));

        // Add to Test Case
        RAW_LIST.add(list1);
        RAW_LIST.add(list2);
        RAW_LIST.add(list3);
        RAW_LIST.add(list4);
        RAW_LIST.add(list5);
        RAW_LIST.add(list6);
        RAW_LIST.add(list7);
        RAW_LIST.add(list8);
        RAW_LIST.add(list9);
    }
}
