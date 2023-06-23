package org.queryongenericlist.query.abstractsyntaxtree.querynode.subnodes.filternode.filteroperator.comparativeoperator.subclasses;

import lombok.NonNull;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.subnodes.filternode.filteroperator.comparativeoperator.ComparativeOperator;
import org.queryongenericlist.utils.ComparativeHelper;

public class LessThan implements ComparativeOperator {

    @NonNull
    @Override
    public boolean compare(final Object value1, @NonNull final Object value2) {
        ComparativeHelper comparator = new ComparativeHelper();
        return comparator.compare(value1, value2) < 0;
    }

}
