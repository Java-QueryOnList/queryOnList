package testHelpers.genericClasses.classTestCases;

import testHelpers.utils.LoggingUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CaseTester<T> {
    private final List<PreparedCase<T>> allCases = new ArrayList<>();

    public CaseTester<T> addCase(PreparedCase<T> preparedCase) {
        allCases.add(preparedCase);
        return this;
    }

    public void executeCases() {
        runOnEach(allCases);
    }

    private static <T> void test(PreparedCase<T> preparedCase) {
        // Preparation of the objects to be compared
        List<T> expectedList = preparedCase.expectedList;
        List<T> queriedList = preparedCase.getQueriedList();

        // Preparation of Log of result
        String testResultString = LoggingUtils.getTestResultString(preparedCase.query, expectedList, queriedList);
        System.out.println(testResultString);

        // Execute Assertion
        assertEquals(expectedList, queriedList);
    }

    private static <T> void runOnEach(List<PreparedCase<T>> preparedCases) {
        preparedCases.forEach(CaseTester::test);
    }

}
