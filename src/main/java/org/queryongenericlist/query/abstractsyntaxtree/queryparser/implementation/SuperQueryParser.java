package org.queryongenericlist.query.abstractsyntaxtree.queryparser.implementation;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.subnodes.SuperQueryNode;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.subnodes.filternode.FilterNode;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.subnodes.paginationnode.PaginationNode;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.subnodes.sortingnode.SortingNode;
import org.queryongenericlist.query.abstractsyntaxtree.queryparser.implementation.subparser.SortingParser;
import org.queryongenericlist.query.abstractsyntaxtree.queryparser.QueryParser;
import org.queryongenericlist.query.abstractsyntaxtree.queryparser.implementation.subparser.FilterParser;
import org.queryongenericlist.query.abstractsyntaxtree.queryparser.implementation.subparser.PaginationParser;
import org.queryongenericlist.utils.StringParser;


/**
 * Parse given query and return the parsed QueryNode
 */
@SuperBuilder
@RequiredArgsConstructor
public class SuperQueryParser implements QueryParser<SuperQueryNode> {

    /**
     * Regex explanation example filter "\\$filter=(.*?)(?:\\$|$)":
     * \\$filter= -> Matches the literal string "$filter=".
     * (.*?) -> Matches any characters (non-greedy) and captures them in a group.
     * (?:\\$|$) -> Matches either the literal character "$" or the end of the string, but doesn't capture it.
     */
    private static final String FILTER_PATTERN = "\\$filter=(.*?)(?:\\$|$)";
    private static final String SORTING_PATTERN = "\\$orderBy=(.*?)(?:\\$|$)";
    private static final String TOP_PATTERN = "\\$top=(.*?)(?:\\$|$)";
    private static final String SKIP_PATTERN = "\\$skip=(.*?)(?:\\$|$)";

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
        if (!paginationTopQuery.isEmpty() || !paginationSkipQuery.isEmpty()) {
            final PaginationNode paginationNode = new PaginationParser(paginationTopQuery, paginationSkipQuery).parse();
            nodeBuilder.paginationNode(paginationNode);
        }


        // build and return SuperQueryNode
        return nodeBuilder.build();
    }
}