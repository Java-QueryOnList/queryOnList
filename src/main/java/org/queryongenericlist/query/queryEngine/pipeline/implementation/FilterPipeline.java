package org.queryongenericlist.query.queryEngine.pipeline.implementation;

import lombok.NonNull;
import org.queryongenericlist.query.queryEngine.pipeline.PredicatePipeline;
import org.queryongenericlist.query.queryNode.implementation.filterNode.FilterNode;
import org.queryongenericlist.query.queryNode.implementation.filterNode.filterValue.subClasses.operator.comparisonOperator.Comparator;
import org.queryongenericlist.query.queryNode.implementation.filterNode.filterValue.subClasses.operator.logicalOperator.Logician;
import org.queryongenericlist.query.queryNode.implementation.filterNode.filterValue.subClasses.operator.logicalOperator.subClasses.LogicalAnd;
import org.queryongenericlist.query.queryNode.implementation.filterNode.filterValue.subClasses.operator.logicalOperator.subClasses.LogicalOr;
import org.queryongenericlist.utils.OperandHelper;

import java.util.function.Predicate;

public class FilterPipeline<T> implements PredicatePipeline<T, FilterNode> {

    @Override
    public Predicate<T> fromNode(@NonNull FilterNode givenNode, @NonNull Predicate<T> currentPredicateChain) {
        Predicate<T> predicateToAdd = (T ignore) -> true;

        if (givenNode.getValue() instanceof Comparator) {
            // if the node is a comparator which always compares two operands values

            // expand Predicate
            predicateToAdd = predicateToAdd.and((T element) -> {
                boolean conditionMet;

                // prepare left and right value
                final Object leftValue = OperandHelper.resolveObject((givenNode.getLeft().getValue()), element);
                final Object rightValue = OperandHelper.resolveObject((givenNode.getRight().getValue()), element);

                // if condition of the comparison is met
                conditionMet = ((Comparator) givenNode.getValue()).compare(leftValue, rightValue);

                return conditionMet;
            });
        } else if (givenNode.getValue() instanceof Logician) {
            // if the node is a logical operator like e.g "AND" or "OR"
            if (givenNode.getValue() instanceof LogicalAnd) {
                final Predicate<T> predicateLeft = fromNode(givenNode.getLeft(), predicateToAdd);
                final Predicate<T> predicateRight = fromNode(givenNode.getRight(), predicateToAdd);
                predicateToAdd = predicateLeft.and(predicateRight);
            } else if (givenNode.getValue() instanceof LogicalOr) {
                final Predicate<T> predicateLeft = fromNode(givenNode.getLeft(), predicateToAdd);
                final Predicate<T> predicateRight = fromNode(givenNode.getRight(), predicateToAdd);
                predicateToAdd = predicateLeft.or(predicateRight);
            } else {
                System.out.println("Logical Operator not implemented yet");
            }
        }

        return predicateToAdd;
    }
}
