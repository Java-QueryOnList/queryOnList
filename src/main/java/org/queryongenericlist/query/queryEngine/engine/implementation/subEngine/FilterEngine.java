package org.queryongenericlist.query.queryEngine.engine.implementation.subEngine;

import lombok.NonNull;
import org.queryongenericlist.query.queryEngine.engine.QueryEngine;
import org.queryongenericlist.query.queryEngine.pipeline.implementation.FilterPipeline;
import org.queryongenericlist.query.queryNode.implementation.filterNode.FilterNode;

import java.util.List;
import java.util.function.Predicate;

public class FilterEngine implements QueryEngine<FilterNode> {
    @Override
    public @NonNull <T> List<T> apply(FilterNode syntaxTree, List<T> onList) {
        List<T> queriedList = onList;

        if (syntaxTree != null) {
            queriedList = query(syntaxTree, onList);
        }

        return queriedList;
    }

    @NonNull
    private <T> List<T> query(@NonNull FilterNode givenNode, @NonNull final List<T> onList) {

        return onList.stream()
                .filter((final T element) -> {
                    FilterPipeline<T> filterPipeline = new FilterPipeline<>();
                    final Predicate<T> predicatePipeline = filterPipeline.fromNode(givenNode);
                    return predicatePipeline.test(element);
                }).toList();
    }
}
