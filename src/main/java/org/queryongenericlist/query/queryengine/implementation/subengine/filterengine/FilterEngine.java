package org.queryongenericlist.query.queryengine.implementation.subengine.filterengine;

import lombok.NonNull;
import org.queryongenericlist.exceptions.query.queryengine.implementation.subengine.filterengine.FilterEngineException;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.subnodes.filternode.FilterNode;
import org.queryongenericlist.query.queryengine.QueryEngine;

import java.util.function.Predicate;
import java.util.stream.Stream;

public class FilterEngine implements QueryEngine<FilterNode> {
    @Override
    public @NonNull <T> Stream<T> apply(@NonNull FilterNode syntaxTree, @NonNull Stream<T> onList) {
        try {
            return onList.filter((final T element) -> {
                FilterPipeline<T> filterPipeline = new FilterPipeline<>();
                final Predicate<T> predicatePipeline = filterPipeline.fromNode(syntaxTree);
                return predicatePipeline.test(element);
            });
        } catch (Throwable throwable) {
            if (throwable instanceof FilterEngineException) {
                throw throwable;
            } else {
                throw new FilterEngineException("An error occurred while applying the filter engine.", throwable);
            }
        }
    }
}
