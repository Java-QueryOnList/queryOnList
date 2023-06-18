package testHelpers.genericClasses.classTestCases;

import lombok.Getter;
import org.queryongenericlist.query.queryExecutor.implementation.SuperQueryExecutor;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * Represents a prepared test case with a raw list, a query, an expected list, and a flag indicating whether order matters.
 *
 * @param <T> Type of the elements of the list
 */
@Getter
public class PreparedCase<T> {
    List<T> rawList;
    String query;
    List<T> expectedList;
    List<Function<T, ?>> gettersForOrderBy;

    /**
     * PreparedCase Constructor for creating Test Cases with expected state of a list before and after a given query
     *
     * @param rawList the full list with elements which the query is run on
     * @param query the query itself of type String
     * @param expectedList the expected list after the rawList getting queried
     * @param gettersForOrderBy
     */
    public PreparedCase(List<T> rawList, String query, List<T> expectedList, List<Function<T, ?>> gettersForOrderBy) {
        this.rawList = rawList;
        this.query = query;
        this.expectedList = expectedList;
        this.gettersForOrderBy = gettersForOrderBy;
    }

    /**
     * PreparedCase Constructor with default Value for checkOrder = false
     *
     * @param rawList the full list with elements which the query is run on
     * @param query the query itself of type String
     * @param expectedList the expected list after the rawList getting queried
     */
    public PreparedCase(List<T> rawList, String query, List<T> expectedList) {
        this.rawList = rawList;
        this.query = query;
        this.expectedList = expectedList;
        this.gettersForOrderBy = Collections.emptyList();
    }

    /**
     * Retrieves the queried list based on the provided query and raw list.
     *
     * @return the queried list.
     */
    public List<T> getQueriedList() {
        SuperQueryExecutor engine = new SuperQueryExecutor();
        return engine.execute(query, rawList);
    }
}
