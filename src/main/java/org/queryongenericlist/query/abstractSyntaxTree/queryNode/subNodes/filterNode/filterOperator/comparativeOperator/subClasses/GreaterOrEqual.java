package org.queryongenericlist.query.abstractSyntaxTree.queryNode.subNodes.filterNode.filterOperator.comparativeOperator.subClasses;

import lombok.NonNull;
import org.queryongenericlist.query.abstractSyntaxTree.queryNode.subNodes.filterNode.filterOperator.comparativeOperator.ComparativeOperator;

public class GreaterOrEqual extends ComparativeOperator {

    @Override
    public boolean compare(@NonNull final Object value1, @NonNull final Object value2) {
        return super.relation(value1, value2) >= 0;
    }

}