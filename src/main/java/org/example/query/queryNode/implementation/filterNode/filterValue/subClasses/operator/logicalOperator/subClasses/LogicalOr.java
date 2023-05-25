package org.example.query.queryNode.implementation.filterNode.filterValue.subClasses.operator.logicalOperator.subClasses;

import org.example.query.queryNode.implementation.filterNode.filterValue.subClasses.operator.logicalOperator.Logician;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LogicalOr implements Logician {
    @Override
    public <T> List<T> resolve(List<T> left, List<T> right) {
        Set<T> uniqueElements = new HashSet<>(left);
        uniqueElements.retainAll(right);

        return new ArrayList<>(uniqueElements);
    }
}
