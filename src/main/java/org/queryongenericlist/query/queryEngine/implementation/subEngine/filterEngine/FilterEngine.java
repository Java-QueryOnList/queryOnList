package org.queryongenericlist.query.queryEngine.implementation.subEngine.filterEngine;

import lombok.NonNull;
import org.queryongenericlist.query.queryEngine.QueryEngine;
import org.queryongenericlist.query.abstractSyntaxTree.queryNode.subNodes.filterNode.FilterNode;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilterEngine implements QueryEngine<FilterNode> {
    @Override
    public @NonNull <T> List<T> apply(@NonNull FilterNode syntaxTree, @NonNull List<T> onList) {
        return onList.stream()
                .filter((final T element) -> {
                    FilterPipeline<T> filterPipeline = new FilterPipeline<>();
                    final Predicate<T> predicatePipeline = filterPipeline.fromNode(syntaxTree);
                    return predicatePipeline.test(element);
                }).collect(Collectors.toList());
    }
}
