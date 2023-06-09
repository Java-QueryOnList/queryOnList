package org.queryongenericlist.query.abstractSyntaxTree.queryParser.implementation.subClasses;

import lombok.NonNull;
import org.queryongenericlist.query.abstractSyntaxTree.queryNode.subNodes.sortingNode.SortingNode;
import org.queryongenericlist.query.abstractSyntaxTree.queryParser.QueryParser;
import org.queryongenericlist.utils.StringParser;

import java.util.List;

public class SortingParser implements QueryParser<SortingNode> {

    private static final String SPLIT_PATTERN = "\\$orderBy=([^(&)]+)";
    final private List<String> splitQuery;
    private int index;

    public SortingParser(@NonNull final String query) {
        this.splitQuery = StringParser.getAll(query, SPLIT_PATTERN);
        this.index = 0;
    }

    public SortingParser(String query, int index, List<String> splitQuery) {
        this.splitQuery = splitQuery;
        this.index = index;
    }

    @Override
    public @NonNull SortingNode parse() {
        return null;
    }
}
