package org.example.query.queryNode.implementation.filterNode.filterValue.subClasses.operator.logicalOperator;

import org.example.query.queryNode.implementation.filterNode.filterValue.subClasses.operator.Operator;

import java.util.List;

public interface Logician extends Operator {
    <T> List<T> resolve(List<T> left, List<T> right);
}
