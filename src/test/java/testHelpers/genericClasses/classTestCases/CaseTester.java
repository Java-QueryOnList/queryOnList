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

    private static <T> void run(PreparedCase<T> preparedCase) {
        List<T> expectedList = preparedCase.expectedList;
        List<T> queriedList = preparedCase.getQueriedList();
        printResults(preparedCase.query, expectedList, queriedList);
        assertEquals(expectedList, queriedList);
    }

    private static <T> void runOnEach(List<PreparedCase<T>> preparedCases) {
        preparedCases.forEach(CaseTester::run);
    }

    private static <T> void printResults(String query, List<T> expectedList, List<T> queriedList) {
        System.out.println("########## NEW CASE ##########\n");
        System.out.println("Query:");
        System.out.println(query + "\n");
        System.out.println("Expected:");
        System.out.println(LoggingUtils.genericListToString(expectedList));
        System.out.println("Queried:");
        System.out.println(LoggingUtils.genericListToString(queriedList));
    }

}
