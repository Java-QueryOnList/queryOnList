package org.queryongenericlist.query.queryEngine.implementation.subEngine.sortingEngine;

import lombok.NonNull;
import org.queryongenericlist.query.abstractSyntaxTree.queryNode.subNodes.sortingNode.SortingNode;
import org.queryongenericlist.utils.OperandHelper;

import java.util.Comparator;
import java.util.List;

public class SortingPipeline<T> {
    Comparator<T> fromNode(@NonNull SortingNode sortingNode) {

        Comparator<T> comparatorResult = Comparator.comparing((final T element) -> {
            Object resolvedObject = OperandHelper.resolveObject(sortingNode.getHead(), element);
            assert !(resolvedObject instanceof List<?>);

            Comparable<? super Object> comparableResult = (Comparable<? super Object>) resolvedObject;
            return comparableResult;
        });

        if (!sortingNode.isAscending()) {
            comparatorResult = comparatorResult.reversed();
        }

        if(sortingNode.getNextSort() != null) {
            comparatorResult = comparatorResult.thenComparing(fromNode(sortingNode.getNextSort()));
        }

        return comparatorResult;
    }
}
