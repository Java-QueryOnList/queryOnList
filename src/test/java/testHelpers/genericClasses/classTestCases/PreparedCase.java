package testHelpers.genericClasses.classTestCases;

import org.queryongenericlist.query.queryEngine.implementation.QueryEngineImpl;

import java.util.List;

public class PreparedCase<T> {
    List<T> rawList;
    String query;
    List<T> expectedList;

    public PreparedCase(List<T> rawList, String query, List<T> expectedList) {
        this.rawList = rawList;
        this.query = query;
        this.expectedList = expectedList;
    }

    public List<T> getQueriedList() {
        QueryEngineImpl engine = new QueryEngineImpl();
        return engine.run(query, rawList);
    }
}
