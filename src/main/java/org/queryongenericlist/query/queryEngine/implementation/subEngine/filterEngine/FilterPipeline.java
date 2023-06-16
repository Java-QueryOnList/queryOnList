package org.queryongenericlist.query.queryEngine.implementation.subEngine.filterEngine;

import lombok.NonNull;
import org.queryongenericlist.query.abstractSyntaxTree.queryNode.QueryNode;
import org.queryongenericlist.query.abstractSyntaxTree.queryNode.subNodes.filterNode.FilterNode;
import org.queryongenericlist.query.abstractSyntaxTree.queryNode.subNodes.filterNode.filterOperator.comparativeOperator.ComparativeOperator;
import org.queryongenericlist.query.abstractSyntaxTree.queryNode.subNodes.filterNode.filterOperator.logicalOperator.LogicalOperator;
import org.queryongenericlist.query.abstractSyntaxTree.queryNode.subNodes.filterNode.filterOperator.logicalOperator.subClasses.LogicalAnd;
import org.queryongenericlist.query.abstractSyntaxTree.queryNode.subNodes.filterNode.filterOperator.logicalOperator.subClasses.LogicalOr;
import org.queryongenericlist.query.abstractSyntaxTree.queryNode.subNodes.filterNode.filterOperator.negativeOperator.NegativeOperator;
import org.queryongenericlist.utils.OperandHelper;

import java.util.List;
import java.util.function.Predicate;

public class FilterPipeline<T> {

    public Predicate<T> fromNode(@NonNull FilterNode givenNode) {
        Predicate<T> predicateResult = (T ignore) -> true;

        QueryNode head = givenNode.getHead();

        if (head instanceof ComparativeOperator) {
            // if the node is a comparator which always compares two operands values

            // expand Predicate
            predicateResult = predicateResult.and((T element) -> {
                boolean conditionMet;

                // prepare left and right value (e.g. "price gt 5" -> left: price; right: 5)
                Object leftValue = OperandHelper.resolveObject((givenNode.getTailLeft().getHead()), element);
                final Object rightValue = OperandHelper.resolveObject((givenNode.getTailRight().getHead()), element);

                if (leftValue.getClass().isArray()) {
                    // if leftValue is array, convert to list
                    leftValue = OperandHelper.convertArrayToList(leftValue);
                }
                if (leftValue instanceof List<?> leftElements) {
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
