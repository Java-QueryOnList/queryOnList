package testHelpers.genericClasses.classTestCases.shoppingList;

import testHelpers.genericClasses.classDecleration.shoppingList.ShoppingList;
import testHelpers.genericClasses.classObjects.shoppingList.ShoppingListObjects;
import testHelpers.genericClasses.classTestCases.PreparedCase;

import java.util.ArrayList;
import java.util.List;

public final class ShoppingListTestCases {
    private static final List<ShoppingList> rawList01 = ShoppingListObjects.getRawList();
    public static final PreparedCase<ShoppingList> case01;

    static {
        case01 = createCase01();
    }

    private static PreparedCase<ShoppingList> createCase01() {
        // Create query
        String query = "$filter=isCompleted eq true";

        // prepare the List which is expected after the query
        List<ShoppingList> expectedList = new ArrayList<>();
        expectedList.add(rawList01.get(4));

        // Return Created PreparedCase Object
        return new PreparedCase<>(rawList01, query, expectedList);
    }
}