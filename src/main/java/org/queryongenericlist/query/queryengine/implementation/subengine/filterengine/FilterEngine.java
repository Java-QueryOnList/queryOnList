package org.queryongenericlist.query.queryengine.implementation.subengine.filterengine;

import lombok.NonNull;
import org.queryongenericlist.query.queryengine.QueryEngine;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.subnodes.filternode.FilterNode;

import java.util.function.Predicate;
import java.util.stream.Stream;

public class FilterEngine implements QueryEngine<FilterNode> {
    @Override
    public @NonNull <T> Stream<T> apply(@NonNull FilterNode syntaxTree, @NonNull Stream<T> onList) {
        return onList.filter((final T element) -> {
            FilterPipeline<T> filterPipeline = new FilterPipeline<>();
            final Predicate<T> predicatePipeline = filterPipeline.fromNode(syntaxTree);
            return predicatePipeline.test(element);
        });
    }
}
