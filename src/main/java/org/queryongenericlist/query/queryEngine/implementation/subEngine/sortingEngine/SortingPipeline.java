package org.queryongenericlist.query.queryEngine.implementation.subEngine.sortingEngine;

import lombok.NonNull;
import org.queryongenericlist.query.abstractSyntaxTree.queryNode.subNodes.sortingNode.SortingNode;
import org.queryongenericlist.utils.OperandHelper;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortingPipeline<T> {
    Comparator<T> fromNode(@NonNull SortingNode sortingNode) {

        // Describe Comparator for sorting algorithm
        Comparator<T> comparatorResult = Comparator.comparing((final T element) -> {
            Comparable<? super Object> comparable;

            // resolve object from node value
            Object resolvedObject = OperandHelper.resolveObject(sortingNode.getHead(), element);

            resolvedObject = singularizeIfIsCollection(sortingNode.isAscending(), resolvedObject);

            comparable = (Comparable<? super Object>) resolvedObject;

            return comparable;
        });

        // Apply sorting order
        if (!sortingNode.isAscending()) {
            comparatorResult = comparatorResult.reversed();
        }

        // Apply secondary sorting recursively
        if (sortingNode.getNextSort() != null) {
            comparatorResult = comparatorResult.thenComparing(fromNode(sortingNode.getNextSort()));
        }

        return comparatorResult;
    }

    /**
     * If object is list or array retrieve the max or min element of that list for comparison
     * @param isAscendingOrder describes order of sorting pipeline which uses this method
     * @param resolvedObject the object which is getting singularized if it is an array or list
     * @return return a single instance of the object, could be min, max or untouched
     */
    @NonNull
    private static Object singularizeIfIsCollection(@NonNull boolean isAscendingOrder, @NonNull Object resolvedObject) {
        // TODO: Rethink if it is correct to use OperandHelper from Filter inside here.
        //  Maybe complete redesign recommended
        if (resolvedObject.getClass().isArray()) {
            // if is array of any type, convert to list for next transformation
            resolvedObject = OperandHelper.convertArrayToList(resolvedObject);
        }
        if (resolvedObject instanceof List<?>) {
            // if is list get max or min according to requested order
            List<Comparable<? super Object>> listRO = (List<Comparable<? super Object>>) resolvedObject;
            // According to the sorting order the value might change
            if (isAscendingOrder) {
                resolvedObject = listRO.isEmpty() ? Integer.MIN_VALUE : Collections.min((listRO)); // min if not null
            } else {
                resolvedObject = listRO.isEmpty() ? Integer.MAX_VALUE : Collections.max((listRO)); // max if not null
            }
        }
        return resolvedObject;
    }
}
