package org.queryongenericlist.query.queryEngine.implementation;

import lombok.NonNull;
import org.queryongenericlist.query.queryEngine.QueryEngine;
import org.queryongenericlist.query.queryNode.QueryNode;
import org.queryongenericlist.query.queryParser.implementation.QueryParserImpl;

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
    @NonNull
    public <T> List<T> run(@NonNull final String query, @NonNull final List<T> onList) {
        // parse query
        final QueryParserImpl queryParser = new QueryParserImpl(query);
        final QueryNode parsedQuery = queryParser.parse();
        try {
            return parsedQuery.query(onList);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
