package org.queryongenericlist.query.queryengine.implementation.subengine.filterengine;

import lombok.NonNull;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.QueryNode;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.subnodes.filternode.FilterNode;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.subnodes.filternode.filteroperator.comparativeoperator.ComparativeOperator;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.subnodes.filternode.filteroperator.logicaloperator.LogicalOperator;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.subnodes.filternode.filteroperator.logicaloperator.subclasses.LogicalAnd;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.subnodes.filternode.filteroperator.logicaloperator.subclasses.LogicalOr;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.subnodes.filternode.filteroperator.negativeoperator.NegativeOperator;
import org.queryongenericlist.utils.ObjectHandler;

import java.util.Collection;
import java.util.function.Predicate;

public class FilterPipeline<T> {

    public Predicate<T> fromNode(@NonNull FilterNode givenNode) {
        Predicate<T> predicateResult = (T ignore) -> true;

        QueryNode head = givenNode.getHead();

        if (head instanceof ComparativeOperator) {
            // if the node is a comparator which always compares two operands values

            // expand Predicate
            predicateResult = predicateResult.and((T element) -> {
                if (element == null) return false;

                boolean conditionMet;

                // prepare left and right value (e.g. "price gt 5" -> left: price; right: 5)
                Object leftValue = ObjectHandler.resolveObject((givenNode.getTailLeft().getHead()), element);
                if (leftValue == null) return false;
                final Object rightValue = ObjectHandler.resolveObject((givenNode.getTailRight().getHead()), element);


                // if leftValue is array, convert to list
                leftValue = ObjectHandler.ifArrayConvertToList(leftValue);
                if (leftValue instanceof Collection<?> leftElements) {
                    return leftElements.stream()
                            .anyMatch(leftElementValue ->
                                    ((ComparativeOperator) head).compare(leftElementValue, rightValue)
                            );
                } else {
                    // if condition of the comparison is met
                    conditionMet = ((ComparativeOperator) head).compare(leftValue, rightValue);
                }


                return conditionMet;
            });
        } else if (head instanceof NegativeOperator) {
            // get the only existing tail, which is right
            final Predicate<T> predicateTail = fromNode(givenNode.getTailRight());
            predicateResult = predicateTail.negate();
        } else if (head instanceof LogicalOperator) {
            // if the node is a logical operator like e.g "AND" or "OR"
            if (head instanceof LogicalAnd) {
                final Predicate<T> predicateLeft = fromNode(givenNode.getTailLeft());
                final Predicate<T> predicateRight = fromNode(givenNode.getTailRight());
                predicateResult = predicateLeft.and(predicateRight);
            } else if (head instanceof LogicalOr) {
                final Predicate<T> predicateLeft = fromNode(givenNode.getTailLeft());
                final Predicate<T> predicateRight = fromNode(givenNode.getTailRight());
                predicateResult = predicateLeft.or(predicateRight);
            } else {
                System.out.println("Logical Operator not implemented yet");
            }
        }

        return predicateResult;
    }
}
