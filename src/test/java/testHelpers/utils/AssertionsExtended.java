package testHelpers.utils;

import org.junit.jupiter.api.Assertions;
import testHelpers.genericClasses.classTestCases.PreparedCase;

import java.util.List;

public class AssertionsExtended extends Assertions {

    public static <T> void assertQuerySuccess(PreparedCase<T> preparedCase, List<T> expectedList, List<T> queriedList) {
        boolean testSucceeded = false;
        AssertionError assertionError = null;

        try {
            // Execute Assertion
            if (preparedCase.getIfOrderMatters()) {
                // if order of the queried list does matter
                assertEquals(expectedList, queriedList);
            } else {
                // if order is not important
                boolean bothListsContainSameElements = expectedList.containsAll(queriedList) && queriedList.containsAll(expectedList);
                assertTrue(bothListsContainSameElements, "Elements of both lists are not the same.");
            }
            testSucceeded = true;
        } catch (AssertionError e) {
            assertionError = e;
        }

        // Logging test result
        String testResultString = LoggingUtils.getTestResultString(preparedCase.getQuery(), expectedList, queriedList, testSucceeded);
        System.out.println(testResultString);

        if (!testSucceeded) {
            // throw assertion error if test failed
            Assertions.fail(assertionError);
        }
    }
}
