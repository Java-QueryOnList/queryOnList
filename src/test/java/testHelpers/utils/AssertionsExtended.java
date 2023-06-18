package testHelpers.utils;

import org.junit.jupiter.api.Assertions;

import java.util.*;
import java.util.function.Function;

public class AssertionsExtended extends Assertions {

    /**
     * Custom assertion for testing equality of a queried and expected list and printing the result
     *
     * @param expectedList the expected list after the rawList getting queried
     * @param queriedList  the queried list after querying the initial list
     * @param orderMatters if order matters
     * @param rawQuery     the raw query of type string
     * @param <T>          type of the elements of the lists
     */
    public static <T> void assertQuerySuccess(
            List<T> expectedList,
            List<T> queriedList,
            boolean orderMatters,
            String rawQuery
    ) {
        boolean testSucceeded = false;
        AssertionError assertionError = null;

        try {
            // Execute Assertion
            if (orderMatters) {
                // if order of the queried list does matter
                assertEquals(expectedList, queriedList);
            } else {
                // if order is not important
                boolean hasSameListElements = hasSameListElements(expectedList, queriedList);
                assertTrue(hasSameListElements, "Elements of both lists are not the same.");
            }
            testSucceeded = true;
        } catch (AssertionError e) {
            assertionError = e;
        }

        // Logging test result
        String testResultString = LoggingUtils.getTestResultString(rawQuery, expectedList, queriedList, testSucceeded);
        System.out.println(testResultString);

        if (!testSucceeded) {
            // throw assertion error if test failed
            Assertions.fail(assertionError);
        }
    }

    private static <T> boolean hasSameListElements(List<T> expectedList, List<T> queriedList) {
        return expectedList.containsAll(queriedList) && queriedList.containsAll(expectedList);
    }

    /****************************************************************************************************************/


    public static <T> void assertQuerySuccess2(
            String rawQuery,
            List<T> expectedList,
            List<T> queriedList
    ) {
        assertQuerySuccess2(
                rawQuery,
                expectedList,
                queriedList,
                Collections.emptyList()
        );
    }


    public static <T> void assertQuerySuccess2(
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

    public static <T> boolean assertElementsAndOrdersAreEqual(
            List<List<T>> expectedSubLists,
            List<List<T>> queriedSubLists,
            List<Function<T, ?>> gettersForOrderBy
    ) {
        // init variables
        boolean testSucceeded = true;
        boolean orderMatters = gettersForOrderBy.size() > 0;

        // Two sublists size should always be equal
        assert (expectedSubLists.size() == queriedSubLists.size());

        // check every sublist for same elements and same order according to getter
        for (int i = 0; i < expectedSubLists.size(); i++) {
            // init variables
            List<T> expectedSubList = expectedSubLists.get(i);
            List<T> queriedSubList = queriedSubLists.get(i);

            // If order matters, check for order, by dividing into more sublists and checking its elements
            if (orderMatters) {
                // prepare for higher order sorting check
                Function<T, ?> getter = gettersForOrderBy.get(0);
                List<List<T>> expectedSubSubListsForNextCheck = divideIntoSublistsbyGetter(expectedSubList, getter);
                List<List<T>> queriedSubSubListsForNextCheck = divideIntoSublistsbyGetter(queriedSubList, getter);
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
            }
        }

        return testSucceeded;
    }

    private static <T> List<List<T>> divideIntoSublistsbyGetter(List<T> wholeList, Function<T, ?> getter) {
        Map<Object, List<T>> map = new HashMap<>();
        for (T element : wholeList) {
            var key = getter.apply(element);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(element);
        }
        return new ArrayList<>(map.values());
    }

}
