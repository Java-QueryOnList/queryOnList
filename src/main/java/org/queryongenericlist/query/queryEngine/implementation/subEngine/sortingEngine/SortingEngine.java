package org.queryongenericlist.query.queryEngine.implementation.subEngine.sortingEngine;

import lombok.NonNull;
import org.queryongenericlist.query.queryEngine.QueryEngine;
import org.queryongenericlist.query.abstractSyntaxTree.queryNode.subNodes.sortingNode.SortingNode;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SortingEngine implements QueryEngine<SortingNode> {
    @Override
    public @NonNull <T> Stream<T> apply(@NonNull SortingNode syntaxTree, @NonNull Stream<T> onStream) {
        Stream<T> queryResult;

        SortingPipeline<T> sortingPipeline = new SortingPipeline<>();
        queryResult = onStream
                .sorted(sortingPipeline.fromNode(syntaxTree));

        return queryResult;
    }
}
