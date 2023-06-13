package org.queryongenericlist.query.abstractSyntaxTree.queryNode.subNodes.filterNode.filterOperator.comparativeOperator;

import org.queryongenericlist.query.abstractSyntaxTree.queryNode.subNodes.filterNode.filterOperator.FilterOperator;

public interface ComparativeOperator extends FilterOperator {

    boolean compare(final Object value1, final Object value2);

}
