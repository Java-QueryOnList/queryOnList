package org.example.query.queryNode.implementation.filterNode;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.example.query.queryNode.QueryNode;
import org.example.query.queryNode.implementation.filterNode.filterValue.FilterValue;
import org.example.query.queryNode.implementation.filterNode.filterValue.subClasses.operand.Operand;
import org.example.query.queryNode.implementation.filterNode.filterValue.subClasses.operator.comparisonOperator.Comparator;
import org.example.query.queryNode.implementation.filterNode.filterValue.subClasses.operator.logicalOperator.Logician;
import org.example.query.queryNode.implementation.filterNode.filterValue.subClasses.operator.logicalOperator.subClasses.LogicalAnd;
import org.example.query.queryNode.implementation.filterNode.filterValue.subClasses.operator.logicalOperator.subClasses.LogicalOr;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class FilterNode implements QueryNode {

    @NonNull
    private final FilterValue value;
    private FilterNode left;
    private FilterNode right;

    @NonNull
    @Override
    public <T> List<T> query(@NonNull final List<T> onList) throws NoSuchFieldException, IllegalAccessException {
        List<T> result = new ArrayList<>();

        // If left and right is null, which should never happen
        if (left == null || right == null) return result;

        if (value instanceof Comparator) {
            // if the node is a comparator which always compares two operands values
            for (T element : onList) {
                // prepare left and right value
                final Object leftValue = Operand.resolveOperand((left.getValue()), element);
                final Object rightValue = Operand.resolveOperand((right.getValue()), element);

                // if condition is met add to result list
                boolean conditionMet = ((Comparator) value).compare(leftValue, rightValue);
                if (conditionMet) result.add(element);
            }
        } else if (value instanceof Logician) {
            // if the node is a logical operator like e.g "AND" or "OR"
            if (value instanceof LogicalAnd) {
                final List<T> queriedLeft = left.query(onList);
                final List<T> queriedRight = right.query(onList);
                result = ((LogicalAnd) value).resolve(queriedLeft, queriedRight);
            } else if (value instanceof LogicalOr) {
                result = ((LogicalOr) value).resolve(left.query(onList), right.query(onList));
            } else {
                System.out.println("Logical Operator not implemented yet");
            }
        }

        return result;
    }

}
