package org.queryongenericlist.query.queryexecutor.implementation;

import lombok.NonNull;
import org.queryongenericlist.exceptions.query.queryexecutor.implementation.SuperQueryExecutorException;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.subnodes.SuperQueryNode;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.subnodes.paginationnode.PaginationNode;
import org.queryongenericlist.query.abstractsyntaxtree.queryparser.implementation.SuperQueryParser;
import org.queryongenericlist.query.queryengine.implementation.SuperQueryEngine;
import org.queryongenericlist.query.queryexecutor.QueryExecutor;
import org.queryongenericlist.query.result.PaginatedResult;

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
        try {
            List<T> queryResult;

            // parse query
            final SuperQueryParser queryParser = new SuperQueryParser(query);
            final SuperQueryNode parsedQuery = queryParser.parse();

            // apply parsed query which is now an abstract syntax tree on the given list
            SuperQueryEngine queryEngine = new SuperQueryEngine();
            Stream<T> queryStream = queryEngine.apply(parsedQuery, onList.stream());
            queryResult = queryStream.collect(Collectors.toList());

            return queryResult;
        } catch (Throwable throwable) {
            if (throwable instanceof SuperQueryExecutorException) {
                throw throwable;
            } else {
                throw new SuperQueryExecutorException("Error while executing query: " + query, throwable);
            }
        }
    }

    /**
     * Executes the predefined methods of a query on a list of objects and returns a paginated result.
     * PaginatedResult is used to add pagination information and functions like iterating to the next page.
     *
     * @param query   The query string to be executed, e.g. "$filter=name eq 'david'&$orderBy=hireDate&$skip=10&$top=10"
     * @param onList  The list of objects on which the query is executed.
     * @param baseUrl The base url of the request, e.g. "http://example.com/api/people"
     * @param <T>     The type of objects in the list.
     * @return The result of the query execution.
     */
    @NonNull
    public <T> PaginatedResult<T> executeForPaginatedResult(@NonNull final String query, @NonNull final List<T> onList, final String baseUrl) {
        try {
            // parse query
            final SuperQueryParser queryParser = new SuperQueryParser(query);
            final SuperQueryNode parsedQuery = queryParser.parse();

            // apply parsed query which is now an abstract syntax tree on the given list
            SuperQueryEngine queryEngine = new SuperQueryEngine();
            Stream<T> queryStream = queryEngine.apply(parsedQuery, onList.stream());

            // Prepare queries
            // filterQuery
            String filterQuery = queryParser.getFilterQuery();
            filterQuery = filterQuery.isEmpty() ? "" : "$filter=" + filterQuery;
            // sortingQuery
            String sortingQuery = queryParser.getSortingQuery();
            sortingQuery = sortingQuery.isEmpty() ? "" : "$orderBy=" + sortingQuery;

            String paginationQuery = getPaginationQuery(onList, parsedQuery);

            // Create and return the new paginatedResult
            return new PaginatedResult<>(
                    queryStream.collect(Collectors.toList()), // paginated result
                    queryEngine.getFilteredResultSize(), // total result size when filtered only
                    filterQuery,
                    sortingQuery,
                    paginationQuery,
                    parsedQuery.getPaginationNode().getSkip(),
                    parsedQuery.getPaginationNode().getTop(),
                    baseUrl
            );
        } catch (Throwable throwable) {
            if (throwable instanceof SuperQueryExecutorException) {
                throw throwable;
            } else {
                throw new SuperQueryExecutorException("Error while executing query: " + query, throwable);
            }
        }
    }

    private static <T> String getPaginationQuery(List<T> onList, SuperQueryNode parsedQuery) {
        // paginationQuery
        String skipQuery;
        String topQuery;
        if (parsedQuery.getPaginationNode() == null) {
            skipQuery = "0";
            final PaginationNode paginationNode = new PaginationNode(0, onList.size());
            topQuery=String.valueOf(onList.size());
            parsedQuery.setPaginationNode(paginationNode);
        } else {
            skipQuery = String.valueOf(parsedQuery.getPaginationNode().getSkip());
            topQuery = String.valueOf(parsedQuery.getPaginationNode().getTop());
        }

        return String.format("$skip=%s&$top=%s", skipQuery, topQuery);
    }
}
