package org.queryongenericlist.query.queryengine.implementation.subengine.sortingengine;

import lombok.NonNull;
import org.queryongenericlist.query.queryengine.QueryEngine;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.subnodes.sortingnode.SortingNode;

import java.util.stream.Stream;

public class SortingEngine implements QueryEngine<SortingNode> {
    @Override
    public @NonNull <T> Stream<T> apply(@NonNull SortingNode syntaxTree, @NonNull Stream<T> onStream) {
        Stream<T> queryResult;

        SortingPipeline<T> sortingPipeline = new SortingPipeline<>();
        queryResult = onStream.sorted(sortingPipeline.fromNode(syntaxTree));

        return queryResult;
    }
}
