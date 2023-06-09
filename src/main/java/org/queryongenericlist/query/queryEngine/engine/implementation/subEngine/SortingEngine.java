package org.queryongenericlist.query.queryEngine.engine.implementation.subEngine;

import lombok.NonNull;
import org.queryongenericlist.query.queryEngine.engine.QueryEngine;
import org.queryongenericlist.query.queryNode.implementation.sortingNode.SortingNode;

import java.util.List;

public class SortingEngine implements QueryEngine<SortingNode> {
    @Override
    public @NonNull <T> List<T> apply(SortingNode syntaxTree, List<T> onList) {
        List<T> queriedList = onList;

        if (syntaxTree != null) {
            // TODO: Apply Engine
        }

        return queriedList;
    }
}
