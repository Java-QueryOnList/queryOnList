package org.example.query.queryEngine.implementation;

import org.example.query.queryEngine.QueryEngine;
import org.example.query.queryNode.QueryNode;
import org.example.query.queryParser.implementation.QueryParserImpl;

import java.util.List;

/**
 * The implementation of the QueryEngine interface that runs the engine for querying a given list.
 */
public class QueryEngineImpl implements QueryEngine {

    /**
     * Executes a query on a list of objects.
     *
     * @param query  The query string to be executed, e.g. "$filter=name eq 'david'&$orderBy=hireDate"
     * @param onList The list of objects on which the query is executed.
     * @param <T>    The type of objects in the list.
     * @return The result of the query execution.
     */
    public <T> List<T> run(String query, List<T> onList) {
        // parse query
        QueryParserImpl queryParser = new QueryParserImpl(query);
        QueryNode parsedQuery = queryParser.parse();
        try {
            return parsedQuery.query(onList);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
