package org.queryongenericlist.query.abstractsyntaxtree.querynode.subnodes.filternode.filteroperator.comparativeoperator;

import org.queryongenericlist.query.abstractsyntaxtree.querynode.subnodes.filternode.filteroperator.FilterOperator;

public interface ComparativeOperator extends FilterOperator {

    boolean compare(final Object value1, final Object value2);

}
