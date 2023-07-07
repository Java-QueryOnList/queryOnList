package org.queryongenericlist.query.abstractsyntaxtree.queryparser.implementation;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.queryongenericlist.exceptions.query.abstractsyntaxtree.queryparser.implementation.SuperQueryParserException;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.subnodes.SuperQueryNode;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.subnodes.filternode.FilterNode;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.subnodes.paginationnode.PaginationNode;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.subnodes.sortingnode.SortingNode;
import org.queryongenericlist.query.abstractsyntaxtree.queryparser.implementation.subparser.SortingParser;
import org.queryongenericlist.query.abstractsyntaxtree.queryparser.QueryParser;
import org.queryongenericlist.query.abstractsyntaxtree.queryparser.implementation.subparser.FilterParser;
import org.queryongenericlist.query.abstractsyntaxtree.queryparser.implementation.subparser.PaginationParser;
import org.queryongenericlist.utils.stringparser.StringParser;


/**
 * Parse given query and return the parsed QueryNode
 */
@SuperBuilder
@Getter
public class SuperQueryParser implements QueryParser<SuperQueryNode> {

    /**
     * Regex explanation example filter "\\$filter=(.*?)(?:\\$|$)":
     * \\$filter= -> Matches the literal string "$filter=".
     * (.*?) -> Matches any characters (non-greedy) and captures them in a group.
     * (?:\$|&|$) -> Matches either the literal character "$" or "&" or the end of the string, but doesn't capture these.
     */
    private static final String FILTER_PATTERN = "\\$filter=(.*?)(?:\\$|&|$)";
    private static final String SORTING_PATTERN = "\\$orderBy=(.*?)(?:\\$|&|$)";
    private static final String TOP_PATTERN = "\\$top=(.*?)(?:\\$|&|$)";
    private static final String SKIP_PATTERN = "\\$skip=(.*?)(?:\\$|&|$)";

    @NonNull
    private final String query; // e.g. "$filter=name eq 'david'&$orderBy=hireDate"
    private final String filterQuery; // e.g. "name eq 'david'"
    private final String sortingQuery; // e.g. "hireDate"
    private final String paginationTopQuery; // e.g. "10"
    private final String paginationSkipQuery; // e.g. "20"

    public SuperQueryParser(@NonNull String query) {
        this.query = query;
        this.filterQuery = StringParser.getFirst(query, FILTER_PATTERN);
        this.sortingQuery = StringParser.getFirst(query, SORTING_PATTERN);
        this.paginationTopQuery = StringParser.getFirst(query, TOP_PATTERN);
        this.paginationSkipQuery = StringParser.getFirst(query, SKIP_PATTERN);
    }



    @NonNull
    public SuperQueryNode parse() {
        try {
            // init SuperQueryNode via Builder
            SuperQueryNode.SuperQueryNodeBuilder nodeBuilder = SuperQueryNode.builder();

            // filter query
            if (!filterQuery.isEmpty()) {
                final FilterNode filterNode = new FilterParser(filterQuery).parse();
                nodeBuilder.filterNode(filterNode);
            }

            // sorting query
            if (!sortingQuery.isEmpty()) {
                final SortingNode sortingNode = new SortingParser(sortingQuery).parse();
                nodeBuilder.sortingNode(sortingNode);
            }

            // pagination query
            if (!paginationTopQuery.isEmpty() || !paginationSkipQuery.isEmpty()) {
                final PaginationNode paginationNode = new PaginationParser(paginationTopQuery, paginationSkipQuery).parse();
                nodeBuilder.paginationNode(paginationNode);
            }


            // build and return SuperQueryNode
            return nodeBuilder.build();
        } catch (SuperQueryParserException e) {
            throw new SuperQueryParserException("Failed to parse query with SuperQueryParser: " + query, e);
        }
    }
}