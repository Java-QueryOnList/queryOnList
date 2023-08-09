package org.queryongenericlist.query.queryengine.implementation.subengine.sortingengine;

import lombok.NonNull;
import org.queryongenericlist.exceptions.query.queryengine.implementation.subengine.sortingengine.SortingEngineException;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.subnodes.sortingnode.SortingNode;
import org.queryongenericlist.query.queryengine.QueryEngine;

import java.util.stream.Stream;

public class SortingEngine implements QueryEngine<SortingNode> {
    @Override
    public @NonNull <T> Stream<T> apply(@NonNull SortingNode syntaxTree, @NonNull Stream<T> onStream) {
        try {
            Stream<T> queryResult;

            SortingPipeline<T> sortingPipeline = new SortingPipeline<>();
            queryResult = onStream.sorted(sortingPipeline.fromNode(syntaxTree));

            return queryResult;
        } catch (Throwable throwable) {
            if (throwable instanceof SortingEngineException) {
                throw throwable;
            } else {
                throw new SortingEngineException("An error occurred while applying the sorting engine.", throwable);
            }
        }
    }
}
