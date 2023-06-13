package org.queryongenericlist.query.queryExecutor.implementation;

import lombok.NonNull;
import org.queryongenericlist.query.queryEngine.implementation.SuperQueryEngine;
import org.queryongenericlist.query.queryExecutor.QueryExecutor;
import org.queryongenericlist.query.abstractSyntaxTree.queryNode.subNodes.SuperQueryNode;
import org.queryongenericlist.query.abstractSyntaxTree.queryParser.implementation.SuperQueryParser;

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

}
