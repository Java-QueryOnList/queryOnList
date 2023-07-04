package org.queryongenericlist.query.queryengine.implementation.subengine.sortingengine;

import lombok.NonNull;
import org.queryongenericlist.exceptions.query.queryengine.implementation.subengine.sortingengine.sortingpipeline.SortingPipelineException;
import org.queryongenericlist.exceptions.query.queryengine.implementation.subengine.sortingengine.sortingpipeline.sortingsubpipelines.PrepareComparablePipelineException;
import org.queryongenericlist.exceptions.query.queryengine.implementation.subengine.sortingengine.sortingpipeline.sortingsubpipelines.SecondarySortingException;
import org.queryongenericlist.exceptions.query.queryengine.implementation.subengine.sortingengine.sortingpipeline.sortingsubpipelines.SingularizePipelineException;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.subnodes.sortingnode.SortingNode;
import org.queryongenericlist.utils.ComparativeHelper;
import org.queryongenericlist.utils.ObjectHandler;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortingPipeline<T> {
    Comparator<T> fromNode(@NonNull SortingNode sortingNode) {
        try {

            // Describe Comparator for sorting algorithm
            Comparator<T> comparatorResult = Comparator.comparing((final T element) -> {
                try {
                    Comparable<? super Object> comparable;

                    // resolve object from node value
                    Object resolvedObject = ObjectHandler.resolveObject(sortingNode.getHead(), element);

                    // take singular object if resolved object is array or list
                    resolvedObject = singularizeIfIsCollection(sortingNode.isAscending(), resolvedObject);

                    comparable = (Comparable<? super Object>) resolvedObject;

                    return comparable;
                } catch (Exception e) {
                    throw new PrepareComparablePipelineException("An error occurred while preparing the object for comparison.", e);
                }
            }, (Object value1, Object value2) -> {
                ComparativeHelper comparativeHelper = new ComparativeHelper();
                return comparativeHelper.compare(value1, value2);
            });

            // Apply sorting order
            if (!sortingNode.isAscending()) {
                comparatorResult = comparatorResult.reversed();
            }

            try {
                // Apply secondary sorting recursively
                if (sortingNode.getNextSort() != null) {
                    comparatorResult = comparatorResult.thenComparing(fromNode(sortingNode.getNextSort()));
                }
            } catch (Exception e) {
                throw new SecondarySortingException("An error occurred while applying secondary sorting.", e);
            }

            return comparatorResult;
        } catch (Throwable throwable) {
            if (throwable instanceof SortingPipelineException) {
                throw throwable;
            } else {
                throw new SortingPipelineException("An error occurred while applying the sorting pipeline.", throwable);
            }
        }
    }

    /**
     * If object is list or array retrieve the max or min element of that list for comparison
     *
     * @param isAscendingOrder describes order of sorting pipeline which uses this method
     * @param resolvedObject   the object which is getting singularized if it is an array or list
     * @return return a single instance of the object, could be min, max or untouched
     */
    private static Object singularizeIfIsCollection(@NonNull boolean isAscendingOrder, Object resolvedObject) {
        try {
            if (resolvedObject != null) {
                // if is array of any type, convert to list for next transformation
                resolvedObject = ObjectHandler.ifArrayConvertToList(resolvedObject);
                if (resolvedObject instanceof Collection<?>) {
                    // if is list get max or min according to requested order
                    List<Comparable<? super Object>> listRO = (List<Comparable<? super Object>>) resolvedObject;
                    // According to the sorting order the value might change
                    if (isAscendingOrder) {
                        resolvedObject = listRO.isEmpty() ? Integer.MIN_VALUE : Collections.min((listRO)); // min if not null
                    } else {
                        resolvedObject = listRO.isEmpty() ? Integer.MAX_VALUE : Collections.max((listRO)); // max if not null
                    }
                }
            }
            return resolvedObject;
        } catch (Exception e) {
            throw new SingularizePipelineException("An error occurred while singularizing the object.", e);
        }
    }
}
