package org.queryongenericlist.query.queryEngine.implementation.subEngine;

import lombok.NonNull;
import org.queryongenericlist.query.queryEngine.QueryEngine;
import org.queryongenericlist.query.queryNode.implementation.filterNode.FilterNode;
import org.queryongenericlist.query.queryNode.implementation.filterNode.filterValue.subClasses.operand.Operand;
import org.queryongenericlist.query.queryNode.implementation.filterNode.filterValue.subClasses.operator.comparisonOperator.Comparator;
import org.queryongenericlist.query.queryNode.implementation.filterNode.filterValue.subClasses.operator.logicalOperator.Logician;
import org.queryongenericlist.query.queryNode.implementation.filterNode.filterValue.subClasses.operator.logicalOperator.subClasses.LogicalAnd;
import org.queryongenericlist.query.queryNode.implementation.filterNode.filterValue.subClasses.operator.logicalOperator.subClasses.LogicalOr;

import java.util.ArrayList;
import java.util.List;

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
        List<T> result = new ArrayList<>();

        // If left and right is null, which should never happen
        if (givenNode.getLeft() == null || givenNode.getRight() == null) return result;

        if (givenNode.getValue() instanceof Comparator) {
            // if the node is a comparator which always compares two operands values
            for (T element : onList) {
                // prepare left and right value
                final Object leftValue = Operand.resolveOperand((givenNode.getLeft().getValue()), element);
                final Object rightValue = Operand.resolveOperand((givenNode.getRight().getValue()), element);

                // if condition is met add to result list
                boolean conditionMet = ((Comparator) givenNode.getValue()).compare(leftValue, rightValue);
                if (conditionMet) result.add(element);
            }
        } else if (givenNode.getValue() instanceof Logician) {
            // if the node is a logical operator like e.g "AND" or "OR"
            if (givenNode.getValue() instanceof LogicalAnd) {
                final List<T> queriedLeft = query(givenNode.getLeft(), onList);
                final List<T> queriedRight = query(givenNode.getRight(), onList);
                result = ((LogicalAnd) givenNode.getValue()).resolve(queriedLeft, queriedRight);
            } else if (givenNode.getValue() instanceof LogicalOr) {
                result = ((LogicalOr) givenNode.getValue()).resolve(query(givenNode.getLeft(), onList), query(givenNode.getRight(), onList));
            } else {
                System.out.println("Logical Operator not implemented yet");
            }
        }

        return result;
    }
}
