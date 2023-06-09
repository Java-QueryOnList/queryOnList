package org.queryongenericlist.query.queryEngine.predicateLogic.implementation;

import lombok.NonNull;
import org.queryongenericlist.query.queryEngine.predicateLogic.PredicateLogic;
import org.queryongenericlist.query.queryNode.implementation.filterNode.FilterNode;
import org.queryongenericlist.query.queryNode.implementation.filterNode.filterValue.FilterValue;
import org.queryongenericlist.query.queryNode.implementation.filterNode.filterValue.subClasses.operator.comparisonOperator.Comparator;
import org.queryongenericlist.query.queryNode.implementation.filterNode.filterValue.subClasses.operator.logicalOperator.Logician;
import org.queryongenericlist.query.queryNode.implementation.filterNode.filterValue.subClasses.operator.logicalOperator.subClasses.LogicalAnd;
import org.queryongenericlist.query.queryNode.implementation.filterNode.filterValue.subClasses.operator.logicalOperator.subClasses.LogicalOr;
import org.queryongenericlist.query.queryNode.implementation.filterNode.filterValue.subClasses.operator.negatorOperator.NegatorOperator;
import org.queryongenericlist.utils.OperandHelper;

import java.util.List;
import java.util.function.Predicate;

public class PredicateFilter<T> implements PredicateLogic<T, FilterNode> {

    @Override
    public Predicate<T> fromNode(@NonNull FilterNode givenNode) {
        Predicate<T> predicateToAdd = (T ignore) -> true;

        FilterValue head = givenNode.getHead();

        if (head instanceof Comparator) {
            // if the node is a comparator which always compares two operands values

            // expand Predicate
            predicateToAdd = predicateToAdd.and((T element) -> {
                boolean conditionMet;

                // prepare left and right value
                final Object leftValue = OperandHelper.resolveObject((givenNode.getTailLeft().getHead()), element);
                final Object rightValue = OperandHelper.resolveObject((givenNode.getTailRight().getHead()), element);

                if (leftValue instanceof List<?>) {
                    List<T> leftElements = (List<T>) leftValue;
                    return leftElements.stream()
                            .anyMatch(leftElementValue ->
                                    ((Comparator) head).compare(leftElementValue, rightValue)
                            );
                } else {
                    // if condition of the comparison is met
                    conditionMet = ((Comparator) head).compare(leftValue, rightValue);
                }


                return conditionMet;
            });
        } else if (head instanceof NegatorOperator) {
            // get the only existing tail, which is right
            final Predicate<T> predicateTail = fromNode(givenNode.getTailRight());
            predicateToAdd = predicateTail.negate();
        } else if (head instanceof Logician) {
            // if the node is a logical operator like e.g "AND" or "OR"
            if (head instanceof LogicalAnd) {
                final Predicate<T> predicateLeft = fromNode(givenNode.getTailLeft());
                final Predicate<T> predicateRight = fromNode(givenNode.getTailRight());
                predicateToAdd = predicateLeft.and(predicateRight);
            } else if (head instanceof LogicalOr) {
                final Predicate<T> predicateLeft = fromNode(givenNode.getTailLeft());
                final Predicate<T> predicateRight = fromNode(givenNode.getTailRight());
                predicateToAdd = predicateLeft.or(predicateRight);
            } else {
                System.out.println("Logical Operator not implemented yet");
            }
        }

        return predicateToAdd;
    }
}
