package org.example.query.queryNode.implementation.filterNode.filterValue.subClasses.operator.comparisonOperator.subClasses;

import org.example.query.queryNode.implementation.filterNode.filterValue.subClasses.operator.comparisonOperator.Comparator;

public class Equal extends Comparator {

    @Override
    public boolean compare(Object value1, Object value2) {
        return super.relation(value1, value2) == 0;
    }
}
