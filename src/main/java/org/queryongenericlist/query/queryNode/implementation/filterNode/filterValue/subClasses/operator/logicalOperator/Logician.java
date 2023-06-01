package org.queryongenericlist.query.queryNode.implementation.filterNode.filterValue.subClasses.operator.logicalOperator;

import org.queryongenericlist.query.queryNode.implementation.filterNode.filterValue.subClasses.operator.Operator;

import java.util.List;

public interface Logician extends Operator {

    <T> List<T> resolve(final List<T> left, final List<T> right);

}
