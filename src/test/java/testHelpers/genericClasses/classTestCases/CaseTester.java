package testHelpers.genericClasses.classTestCases;

import testHelpers.utils.AssertionsExtended;

import java.util.ArrayList;
import java.util.List;

public class CaseTester<T> {
    private final List<PreparedCase<T>> cases = new ArrayList<>();

    public CaseTester<T> addCase(PreparedCase<T> preparedCase) {
        cases.add(preparedCase);
        return this;
    }

    public void testCases() {
        runTestOnAll(cases);
    }

    /**
     * Running assertion test on a prepared case object
     *
     * @param preparedCase the case with all properties for the assertion test
     * @param <T> type of the list where the assertion is tested on
     */
    private static <T> void unitTest(PreparedCase<T> preparedCase) {
        // Preparation of the objects to be compared
        List<T> expectedList = preparedCase.expectedList;
        List<T> queriedList = preparedCase.getQueriedList();

        AssertionsExtended.assertQuerySuccess2(
                preparedCase.getQuery(),
                expectedList,
                queriedList,
                preparedCase.gettersForOrderBy
        );
    }

    private static <T> void runTestOnAll(List<PreparedCase<T>> preparedCases) {
        preparedCases.forEach(CaseTester::unitTest);
    }

}
