package org.queryongenericlist.query.queryNode.implementation.filterNode.filterValue.subClasses.operator.logicalOperator.subClasses;

import lombok.NonNull;
import org.queryongenericlist.query.queryNode.implementation.filterNode.filterValue.subClasses.operator.logicalOperator.Logician;

import java.util.*;

public class LogicalOr implements Logician {
    @NonNull
    @Override
    public <T> List<T> resolve(@NonNull final List<T> left, @NonNull final List<T> right) {
        final Set<T> uniqueElements = new LinkedHashSet<>(right); //LinkedHashSet instead of HashSet to preserve order
        uniqueElements.addAll(left);

        return new ArrayList<>(uniqueElements);
    }
}
