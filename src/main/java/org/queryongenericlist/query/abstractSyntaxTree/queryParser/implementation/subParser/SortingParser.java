package org.queryongenericlist.query.abstractSyntaxTree.queryParser.implementation.subParser;

import lombok.NonNull;
import org.queryongenericlist.query.abstractSyntaxTree.queryNode.leafNode.subClasses.ReferenceValue;
import org.queryongenericlist.query.abstractSyntaxTree.queryNode.subNodes.sortingNode.SortingNode;
import org.queryongenericlist.query.abstractSyntaxTree.queryParser.QueryParser;

import java.util.Arrays;
import java.util.List;

public class SortingParser implements QueryParser<SortingNode> {

    private static final String SPLIT_PATTERN = "\\s|(?<=,)|(?=,)";
    final private List<String> splitQuery;
    private int index;

    public SortingParser(@NonNull final String query) {
        this.splitQuery = Arrays.stream(query.split(SPLIT_PATTERN)).toList();
        this.index = 0;
    }

    public SortingParser(List<String> splitQuery) {
        this.splitQuery = splitQuery;
        this.index = 0;
    }

    @Override
    public @NonNull SortingNode parse() {
        SortingNode resultNode = new SortingNode();
        while(index < splitQuery.size()) {
            String subString = splitQuery.get(index);

            switch (subString) {
                case "asc" -> resultNode.setAscending(true);
                case "desc" -> resultNode.setAscending(false);
                case "," -> {
                    index++;
                    SortingParser subParser = new SortingParser(splitQuery.subList(index, splitQuery.size()));
                    resultNode.setNextSort(subParser.parse());
                    index += subParser.index;
                }
                default -> {
                    // if substring is field
                    final ReferenceValue referenceValue = ReferenceValue.fromSubstring(subString);
                    resultNode.setHead(referenceValue);
                }
            }

            index++;
        }
        return resultNode;
    }
}
