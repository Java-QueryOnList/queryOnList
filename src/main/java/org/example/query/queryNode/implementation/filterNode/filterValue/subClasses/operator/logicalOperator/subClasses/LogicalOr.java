package org.example.query.queryNode.implementation.filterNode.filterValue.subClasses.operator.logicalOperator.subClasses;

import org.example.query.queryNode.implementation.filterNode.filterValue.subClasses.operator.logicalOperator.Logician;

import java.util.*;

public class LogicalOr implements Logician {
    @Override
    public <T> List<T> resolve(List<T> left, List<T> right) {
        Set<T> uniqueElements = new LinkedHashSet<>(right); //LinkedHashSet instead of HashSet to preserve order
        uniqueElements.addAll(left);

        return new ArrayList<>(uniqueElements);
    }
}
