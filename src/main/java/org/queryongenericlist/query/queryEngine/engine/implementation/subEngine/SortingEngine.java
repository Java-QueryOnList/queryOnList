package org.queryongenericlist.query.queryEngine.engine.implementation.subEngine;

import lombok.NonNull;
import org.queryongenericlist.query.queryEngine.engine.QueryEngine;
import org.queryongenericlist.query.queryNode.implementation.sortingNode.SortingNode;

import java.util.List;

public class SortingEngine implements QueryEngine<SortingNode> {
    @Override
    public @NonNull <T> List<T> apply(@NonNull SortingNode syntaxTree, @NonNull List<T> onList) {
        List<T> queryResult = onList;

        return queryResult;
    }
}
