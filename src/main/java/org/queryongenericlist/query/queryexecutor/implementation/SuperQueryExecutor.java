package org.queryongenericlist.query.queryexecutor.implementation;

import lombok.NonNull;
import org.queryongenericlist.query.queryengine.implementation.SuperQueryEngine;
import org.queryongenericlist.query.queryexecutor.QueryExecutor;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.subnodes.SuperQueryNode;
import org.queryongenericlist.query.abstractsyntaxtree.queryparser.implementation.SuperQueryParser;
import org.queryongenericlist.utils.StringParser;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The implementation of the QueryEngine interface that runs the engine for querying a given list.
 */
public class SuperQueryExecutor implements QueryExecutor {

    /**
     * Executes the predefined methods of a query on a list of objects.
     *
     * @param query  The query string to be executed, e.g. "$filter=name eq 'david'&$orderBy=hireDate"
     * @param onList The list of objects on which the query is executed.
     * @param <T>    The type of objects in the list.
     * @return The result of the query execution.
     */
    @NonNull
    public <T> List<T> execute(@NonNull final String query, @NonNull final List<T> onList) {
        List<T> queryResult;

        // parse query
        final SuperQueryParser queryParser = new SuperQueryParser(query);
        final SuperQueryNode parsedQuery = queryParser.parse();

        // apply parsed query which is now an abstract syntax tree on the given list
        SuperQueryEngine queryEngine = new SuperQueryEngine();
        Stream<T> queryStream = queryEngine.apply(parsedQuery, onList.stream());
        queryResult = queryStream.collect(Collectors.toList());

        return queryResult;
    }

    /**
     * Get query from full url
     * @param fullURL e.g.: "https://www.example.com/?$filter=name eq 'david'&$orderBy=hireDate" to get "$filter=name eq 'david'&$orderBy=hireDate"
     * @return query string
     */
    public String getQueryFromURL(@NonNull final String fullURL) {
        return StringParser.getFirst(fullURL, "\\?(.*)");
    }


}
