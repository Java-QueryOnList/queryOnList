package org.queryongenericlist.query.abstractsyntaxtree.querynode.subnodes.filternode.filteroperator.comparativeoperator.subclasses;

import lombok.NonNull;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.subnodes.filternode.filteroperator.comparativeoperator.ComparativeOperator;
import org.queryongenericlist.utils.ComparativeHelper;

public class Equal implements ComparativeOperator {

    @Override
    public boolean compare(@NonNull final Object value1, @NonNull final Object value2) {
        ComparativeHelper comparator = new ComparativeHelper();
        return comparator.compare(value1, value2) == 0;
    }

}
