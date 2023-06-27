package testHelpers.utils;

import org.junit.jupiter.api.Assertions;
import org.queryongenericlist.utils.ComparativeHelper;

import java.util.*;
import java.util.function.Function;

public class AssertionsExtended extends Assertions {

    /**
     * Custom assertion for testing equality of a queried and expected list and printing the result
     *
     * @param rawQuery          the raw query of type string
     * @param expectedList      the expected list after the rawList getting queried
     * @param queriedList       the queried list after querying the initial list
     * @param gettersForOrderBy all getters to check order
     * @param <T>               type of the elements of the lists
     */
    public static <T> void assertQuerySuccess(
            String rawQuery,
            List<T> expectedList,
            List<T> queriedList,
            List<Function<T, ?>> gettersForOrderBy
    ) {
        boolean testSucceeded = assertElementsAndOrdersAreEqual(
                List.of(expectedList),
                List.of(queriedList),
                gettersForOrderBy
        );

        // Logging test result
        String testResultString = LoggingUtils.getTestResultString(rawQuery, expectedList, queriedList, testSucceeded);
        System.out.println(testResultString);

        if (!testSucceeded) {
            // throw assertion error if test failed
            Assertions.fail();
        }
    }

    private static <T> boolean assertElementsAndOrdersAreEqual(
            List<List<T>> expectedSubLists,
            List<List<T>> queriedSubLists,
            List<Function<T, ?>> gettersForOrderBy
    ) {
        // init variables
        boolean testSucceeded;
        boolean orderMatters = gettersForOrderBy.size() > 0;

        // Two sublists size should always be equal
        testSucceeded = expectedSubLists.size() == queriedSubLists.size();
        if (!testSucceeded) return false;

        // check every sublist for same elements and same order according to getter
        for (int i = 0; i < expectedSubLists.size(); i++) {
            // init variables
            List<T> expectedSubList = expectedSubLists.get(i);
            List<T> queriedSubList = queriedSubLists.get(i);

            // If order matters, check for order, by dividing into more sublists and checking its elements
            if (orderMatters) {
                // prepare for higher order sorting check
                Function<T, ?> getter = gettersForOrderBy.get(0);
                List<List<T>> expectedSubSubListsForNextCheck = divideIntoSubListsByGetter(expectedSubList, getter);
                List<List<T>> queriedSubSubListsForNextCheck = divideIntoSubListsByGetter(queriedSubList, getter);
                // recursive call until order doesn't matter and elements equality is getting checked
                testSucceeded = assertElementsAndOrdersAreEqual(
                        expectedSubSubListsForNextCheck,
                        queriedSubSubListsForNextCheck,
                        gettersForOrderBy.subList(1, gettersForOrderBy.size()) // getters without first one
                );
                if (!testSucceeded) break;
            } else {
                // first check for same elements
                testSucceeded = hasSameListElements(expectedSubList, queriedSubList);
                if (!testSucceeded) break;
            }
        }

        return testSucceeded;
    }

    private static <T> boolean hasSameListElements(List<T> expectedList, List<T> queriedList) {
        return expectedList.containsAll(queriedList) && queriedList.containsAll(expectedList);
    }

    /**
     * Divides a given list into sublists based on the values obtained by applying a getter function to the elements.
     *
     * @param parentList the list to be divided into sublists
     * @param getter     the function that extracts a value from an element
     * @param <T>        the type of elements in the list
     * @return a list of sublists, where elements with the same getter value and being next each other are grouped together
     */
    private static <T> List<List<T>> divideIntoSubListsByGetter(List<T> parentList, Function<T, ?> getter) {
        // Create a new list to hold the sublists
        List<List<T>> subLists = new ArrayList<>();

        // Represents the current sublist being processed
        List<T> currentSubList = null;

        // Iterate over each element in the parentList
        for (T element : parentList) {

            // Check if we need to start a new sublist
            if (currentSubList == null || elementWithNewValue(getter, currentSubList, element)) {
                // Create a new sublist and add it to subLists
                currentSubList = new ArrayList<>();
                subLists.add(currentSubList);
            }

            // Add the current element to the currentSubList
            currentSubList.add(element);
        }

        // Return the list of sublists
        return subLists;
    }

    private static <T> boolean elementWithNewValue(Function<T, ?> getter, List<T> currentSubList, T element) {
        ComparativeHelper comparativeHelper = new ComparativeHelper();
        return comparativeHelper.compare(getter.apply(element), getter.apply(currentSubList.get(0))) != 0;
    }

}
