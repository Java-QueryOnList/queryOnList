package org.queryongenericlist.query.queryEngine.implementation.subEngine.sortingEngine;

import lombok.NonNull;
import org.queryongenericlist.query.queryEngine.QueryEngine;
import org.queryongenericlist.query.abstractSyntaxTree.queryNode.subNodes.sortingNode.SortingNode;

import java.util.List;

public class SortingEngine implements QueryEngine<SortingNode> {
    @Override
    public @NonNull <T> List<T> apply(@NonNull SortingNode syntaxTree, @NonNull List<T> onList) {
        List<T> queryResult = onList;

        return queryResult;
    }
}
