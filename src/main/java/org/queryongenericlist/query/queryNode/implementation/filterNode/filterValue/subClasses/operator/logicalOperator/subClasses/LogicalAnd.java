package org.example.query.queryNode.implementation.filterNode.filterValue.subClasses.operator.logicalOperator.subClasses;

import lombok.NonNull;
import org.example.query.queryNode.implementation.filterNode.filterValue.subClasses.operator.logicalOperator.Logician;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class LogicalAnd implements Logician {

    @NonNull
    public <T> List<T> resolve(@NonNull final List<T> left, @NonNull final List<T> right) {
        final Set<T> uniqueElements = new LinkedHashSet<>(right); //LinkedHashSet instead of HashSet to preserve order
        uniqueElements.retainAll(left);

        return new ArrayList<>(uniqueElements);
    }

}
