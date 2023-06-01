package org.queryongenericlist.query.queryNode.implementation.filterNode.filterValue.subClasses.operator.comparisonOperator.subClasses;

import lombok.NonNull;
import org.queryongenericlist.query.queryNode.implementation.filterNode.filterValue.subClasses.operator.comparisonOperator.Comparator;

public class NotEqual extends Comparator {

    @Override
    public boolean compare(@NonNull final Object value1, @NonNull final Object value2) {
        return super.relation(value1, value2) != 0;
    }

}
