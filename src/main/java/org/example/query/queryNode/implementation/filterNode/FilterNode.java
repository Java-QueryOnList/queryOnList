package org.example.query.queryNode.implementation.filterNode;

import org.example.query.queryNode.QueryNode;
import org.example.query.queryNode.implementation.filterNode.filterValue.FilterValue;
import org.example.query.queryNode.implementation.filterNode.filterValue.subClasses.operand.Operand;
import org.example.query.queryNode.implementation.filterNode.filterValue.subClasses.operator.comparisonOperator.Comparator;
import org.example.query.queryNode.implementation.filterNode.filterValue.subClasses.operator.logicalOperator.Logician;
import org.example.query.queryNode.implementation.filterNode.filterValue.subClasses.operator.logicalOperator.subClasses.LogicalAnd;
import org.example.query.queryNode.implementation.filterNode.filterValue.subClasses.operator.logicalOperator.subClasses.LogicalOr;

import java.util.ArrayList;
import java.util.List;

public class FilterNode implements QueryNode {
    private final FilterValue value;
    public FilterNode left;
    public FilterNode right;

    public FilterNode(FilterValue value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public FilterValue getValue() {
        return value;
    }

    @Override
    public <T> List<T> query(List<T> onList) throws NoSuchFieldException, IllegalAccessException {
        List<T> result = new ArrayList<>();

        // If left and right is null, which should never happen
        if (left == null || right == null) return result;

        if (value instanceof Comparator){
            // if the node is a comparator which always compares two operands values
            for (T element : onList){
                // prepare left and right value
                Object leftValue = Operand.resolveOperand((left.getValue()), element);
                Object rightValue = Operand.resolveOperand((right.getValue()), element);

                // if condition is met add to result list
                boolean conditionMet = ((Comparator) value).compare(leftValue, rightValue);
                if (conditionMet) result.add(element);
            }
        } else if (value instanceof Logician){
            // if the node is a logical operator like e.g "AND" or "OR"
            if (value instanceof LogicalAnd) {
                List<T> queriedLeft = left.query(onList);
                List<T> queriedRight = right.query(onList);
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
