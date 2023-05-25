package testHelpers.genericClasses.classObjects.shoppingList;

import testHelpers.genericClasses.classDecleration.shoppingList.ShoppingList;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListObjects {
    public static final List<ShoppingList> TEST_CASE_1 = new ArrayList<>();

    static {
        prepTestCase01();
    }

    private static void prepTestCase01() {
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
        TEST_CASE_1.add(list1);
        TEST_CASE_1.add(list2);
        TEST_CASE_1.add(list3);
        TEST_CASE_1.add(list4);
        TEST_CASE_1.add(list5);
        TEST_CASE_1.add(list6);
        TEST_CASE_1.add(list7);
        TEST_CASE_1.add(list8);
        TEST_CASE_1.add(list9);
    }
}
