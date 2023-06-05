package testHelpers.utils;

import org.junit.jupiter.api.Assertions;

import java.util.List;

public class AssertionsExtended extends Assertions {

    /**
     * Custom assertion for testing equality of a queried and expected list and printing the result
     *
     * @param expectedList the expected list after the rawList getting queried
     * @param queriedList the queried list after querying the initial list
     * @param orderMatters if order matters
     * @param rawQuery the raw query of type string
     * @param <T> type of the elements of the lists
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
}
