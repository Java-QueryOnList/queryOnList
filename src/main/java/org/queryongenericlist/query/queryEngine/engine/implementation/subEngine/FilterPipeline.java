package org.queryongenericlist.query.queryEngine.engine.implementation.subEngine;

import lombok.NonNull;
import org.queryongenericlist.query.queryEngine.engine.QueryEngine;
import org.queryongenericlist.query.queryEngine.predicateLogic.implementation.PredicateFilter;
import org.queryongenericlist.query.queryNode.implementation.filterNode.FilterNode;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilterPipeline implements QueryEngine<FilterNode> {
    @Override
    public @NonNull <T> List<T> apply(@NonNull FilterNode syntaxTree, @NonNull List<T> onList) {
        return onList.stream()
                .filter((final T element) -> {
                    PredicateFilter<T> filterPipeline = new PredicateFilter<>();
                    final Predicate<T> predicatePipeline = filterPipeline.fromNode(syntaxTree);
                    return predicatePipeline.test(element);
                }).collect(Collectors.toList());
    }
}
