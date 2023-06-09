package org.queryongenericlist.query.abstractSyntaxTree.queryNode.subNodes.filterNode.filterOperator.logicalOperator;

import org.queryongenericlist.query.abstractSyntaxTree.queryNode.subNodes.filterNode.filterOperator.FilterOperator;

import java.util.List;

public interface LogicalOperator extends FilterOperator {

    <T> List<T> resolve(final List<T> left, final List<T> right);

}
