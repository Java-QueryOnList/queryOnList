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

    public void executeCases() {
        runOnAll(cases);
    }

    private static <T> void test(PreparedCase<T> preparedCase) {
        // Preparation of the objects to be compared
        List<T> expectedList = preparedCase.expectedList;
        List<T> queriedList = preparedCase.getQueriedList();

        AssertionsExtended.assertQuerySuccess(preparedCase, expectedList, queriedList);
    }

    private static <T> void runOnAll(List<PreparedCase<T>> preparedCases) {
        preparedCases.forEach(CaseTester::test);
    }

}
