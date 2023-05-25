package org.example.query.queryParser.implementation;

import org.example.query.queryNode.QueryNode;
import org.example.query.queryNode.implementation.SuperQueryNode;
import org.example.query.queryNode.implementation.filterNode.FilterNode;
import org.example.query.queryNode.implementation.paginationNode.PaginationNode;
import org.example.query.queryNode.implementation.sortingNode.SortingNode;
import org.example.query.queryParser.QueryParser;
import org.example.query.queryParser.implementation.subClasses.FilterParser;
import org.example.query.queryParser.implementation.subClasses.PaginationParser;
import org.example.query.queryParser.implementation.subClasses.SortingParser;
import org.example.utils.StringParser;

/**
 * Parse given query and return the parsed QueryNode
 */
public class QueryParserImpl implements QueryParser {
    private static final String FILTER_PATTERN = "\\$filter=([^&]+)";
    private static final String SORTING_PATTERN = "\\$orderBy=([^&]+)";
    private static final String TOP_PATTERN = "\\$top=(\\d+)";
    private static final String SKIP_PATTERN = "\\$skip=(\\d+)";

    String query; // e.g. "$filter=name eq 'david'&$orderBy=hireDate"

    public QueryParserImpl(String query) {
        this.query = query;
    }

    public QueryNode parse() {
        String filterQuery = StringParser.getFirst(query, FILTER_PATTERN);
        String sortingQuery = StringParser.getFirst(query, SORTING_PATTERN);
        String paginationTopQuery = StringParser.getFirst(query, TOP_PATTERN);
        String paginationSkipQuery = StringParser.getFirst(query, SKIP_PATTERN);

        FilterNode filterNode = new FilterParser(filterQuery).parse();
        SortingNode sortingNode = new SortingParser(sortingQuery).parse();
        PaginationNode paginationNode = new PaginationParser(paginationTopQuery, paginationSkipQuery).parse();
        return new SuperQueryNode(filterNode, sortingNode, paginationNode);
    }
}