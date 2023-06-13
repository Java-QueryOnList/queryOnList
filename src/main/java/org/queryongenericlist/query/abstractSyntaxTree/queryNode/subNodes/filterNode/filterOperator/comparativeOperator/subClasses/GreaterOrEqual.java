package org.queryongenericlist.query.abstractSyntaxTree.queryNode.subNodes.filterNode.filterOperator.comparativeOperator.subClasses;

import lombok.NonNull;
import org.queryongenericlist.query.abstractSyntaxTree.queryNode.subNodes.filterNode.filterOperator.comparativeOperator.ComparativeOperator;
import org.queryongenericlist.utils.ComparativeHelper;

public class GreaterOrEqual implements ComparativeOperator {

    @Override
    public boolean compare(@NonNull final Object value1, @NonNull final Object value2) {
        return ComparativeHelper.relation(value1, value2) >= 0;
    }

}