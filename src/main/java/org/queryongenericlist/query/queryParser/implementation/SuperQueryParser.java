package org.queryongenericlist.query.queryParser.implementation;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.queryongenericlist.query.queryNode.implementation.SuperQueryNode;
import org.queryongenericlist.query.queryNode.implementation.filterNode.FilterNode;
import org.queryongenericlist.query.queryNode.implementation.paginationNode.PaginationNode;
import org.queryongenericlist.query.queryNode.implementation.sortingNode.SortingNode;
import org.queryongenericlist.query.queryParser.QueryParser;
import org.queryongenericlist.query.queryParser.implementation.subClasses.FilterParser;
import org.queryongenericlist.query.queryParser.implementation.subClasses.PaginationParser;
import org.queryongenericlist.query.queryParser.implementation.subClasses.SortingParser;
import org.queryongenericlist.utils.StringParser;


/**
 * Parse given query and return the parsed QueryNode
 */
@SuperBuilder
@RequiredArgsConstructor
public class SuperQueryParser implements QueryParser<SuperQueryNode> {

    private static final String FILTER_PATTERN = "\\$filter=([^&]+)";
    private static final String SORTING_PATTERN = "\\$orderBy=([^&]+)";
    private static final String TOP_PATTERN = "\\$top=(\\d+)";
    private static final String SKIP_PATTERN = "\\$skip=(\\d+)";

    @NonNull
    private final String query; // e.g. "$filter=name eq 'david'&$orderBy=hireDate"


    @NonNull
    public SuperQueryNode parse() {
        // init SuperQueryNode via Builder
        SuperQueryNode.SuperQueryNodeBuilder nodeBuilder = SuperQueryNode.builder();

        // filter query
        final String filterQuery = StringParser.getFirst(query, FILTER_PATTERN);
        if (!filterQuery.isEmpty()) {
            final FilterNode filterNode = new FilterParser(filterQuery).parse();
            nodeBuilder.filterNode(filterNode);
        }

        // sorting query
        final String sortingQuery = StringParser.getFirst(query, SORTING_PATTERN);
        if (!sortingQuery.isEmpty()) {
            final SortingNode sortingNode = new SortingParser(sortingQuery).parse();
            nodeBuilder.sortingNode(sortingNode);
        }

        // pagination query
        final String paginationTopQuery = StringParser.getFirst(query, TOP_PATTERN);
        final String paginationSkipQuery = StringParser.getFirst(query, SKIP_PATTERN);
        if (!paginationTopQuery.isEmpty() && !paginationSkipQuery.isEmpty()) {
            final PaginationNode paginationNode = new PaginationParser(paginationTopQuery, paginationSkipQuery).parse();
            nodeBuilder.paginationNode(paginationNode);
        }


        // build and return SuperQueryNode
        return nodeBuilder.build();
    }
}