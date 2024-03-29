package org.queryongenericlist.query.abstractsyntaxtree.queryparser.implementation.subparser;

import lombok.NonNull;
import org.queryongenericlist.exceptions.query.abstractsyntaxtree.queryparser.implementation.subparser.sortingparser.SortingParserException;
import org.queryongenericlist.exceptions.query.abstractsyntaxtree.queryparser.implementation.subparser.sortingparser.sortingsubparser.CommaParserException;
import org.queryongenericlist.exceptions.query.abstractsyntaxtree.queryparser.implementation.subparser.sortingparser.sortingsubparser.SortingFieldParserException;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.leafnode.subclasses.ReferenceValue;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.subnodes.sortingnode.SortingNode;
import org.queryongenericlist.query.abstractsyntaxtree.queryparser.QueryParser;

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
        try {
            SortingNode resultNode = new SortingNode();
            while (index < splitQuery.size()) {
                String subString = splitQuery.get(index);

                switch (subString) {
                    case "asc" -> resultNode.setAscending(true);
                    case "desc" -> resultNode.setAscending(false);
                    case "," -> {
                        try {
                            index++;
                            SortingParser subParser = new SortingParser(splitQuery.subList(index, splitQuery.size()));
                            resultNode.setNextSort(subParser.parse());
                            index += subParser.index;
                        } catch (Exception e) {
                            throw new CommaParserException("Exception parsing Comma ','", e);
                        }
                    }
                    default -> {
                        try {
                            // if substring is field
                            final ReferenceValue referenceValue = ReferenceValue.fromSubstring(subString);
                            resultNode.setHead(referenceValue);
                        } catch (Exception e) {
                            throw new SortingFieldParserException("Exception parsing field", e);
                        }
                    }
                }

                index++;
            }
            return resultNode;
        } catch (Throwable throwable) {
            if (throwable instanceof SortingParserException) {
                throw throwable;
            } else {
                throw new SortingParserException("Error parsing sorting token at index " + index + ": " + splitQuery.get(index) + " of " + String.join(", ", splitQuery), throwable);
            }
        }
    }
}
