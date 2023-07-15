package org.queryongenericlist;

import lombok.Getter;
import org.queryongenericlist.query.queryexecutor.implementation.SuperQueryExecutor;
import org.queryongenericlist.query.result.PaginatedResult;
import org.queryongenericlist.utils.stringparser.applied.UrlParser;

import java.util.List;

/**
 * Example class for executing a query on a raw list and getting the queried list alone or wrapped in a PaginatedResult
 */
public class Example {

    @Getter
    private static class Person {
        private final String name;
        private final int hireDate;

        public Person(String name, int hireDate) {
            this.name = name;
            this.hireDate = hireDate;
        }
    }

    /**
     * Raw list of people on which the query will be executed
     */
    static List<Person> rawList = List.of(
            new Person("David", 2010),
            new Person("John", 2015),
            new Person("David", 2009)
    );

    public static void main(String[] args) {
        // Example 1 for executing a query on a raw list and getting the queried list
        List<Person> result_1 = example_1();

        // Example 2 for executing a query on a raw list and getting the paginated result
        PaginatedResult<Person> result_2 = example_2();
    }

    /**
     * Example 1 for executing a query on a raw list and getting the queried list
     *
     * @return the queried list alone
     */
    private static List<Person> example_1() {
        // given
        String query = "$filter=name eq 'David'&$orderBy=hireDate";
        List<Person> onList = rawList;

        // searched
        List<Person> queriedList;

        // Create a SuperQueryExecutor
        SuperQueryExecutor superQueryExecutor = new SuperQueryExecutor();

        // Execute the given query on the raw list to get the queried list
        queriedList = superQueryExecutor.execute(query, onList);
        return queriedList;
    }

    /**
     * Example 2 for executing a query on a raw list and getting the queried list inside a paginated result
     *
     * @return the paginated result
     */
    private static PaginatedResult<Person> example_2() {
        // given
        String fullUrl = "https://www.example.com/people?$filter=name eq 'David'&$orderBy=hireDate";
        List<Person> onList = rawList;

        // searched
        PaginatedResult<Person> queriedListWithWrapper;

        // Create a SuperQueryExecutor
        SuperQueryExecutor superQueryExecutor = new SuperQueryExecutor();

        // Get the query and baseUrl from the full url
        String query = UrlParser.getQueryFromUrl(fullUrl);
        String baseUrl = UrlParser.getBaseUrlFromUrl(fullUrl);

        // Execute the given query on the raw list to get the queried list inside a PaginatedResult wrapper
        queriedListWithWrapper = superQueryExecutor.executeForPaginatedResult(query, onList, baseUrl);
        return queriedListWithWrapper;
    }
}
