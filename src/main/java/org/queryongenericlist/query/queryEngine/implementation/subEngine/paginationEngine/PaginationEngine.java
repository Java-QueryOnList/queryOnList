package org.queryongenericlist.query.queryEngine.implementation.subEngine.paginationEngine;

import lombok.NonNull;
import org.queryongenericlist.query.queryEngine.QueryEngine;
import org.queryongenericlist.query.abstractSyntaxTree.queryNode.subNodes.paginationNode.PaginationNode;

import java.util.List;
import java.util.stream.Collectors;

public class PaginationEngine implements QueryEngine<PaginationNode> {
    @Override
    public @NonNull <T> List<T> apply(@NonNull PaginationNode syntaxTree, @NonNull List<T> onList) {
        List<T> queryResult;

        queryResult = onList.stream()
                .skip(syntaxTree.getSkip())
                .limit(syntaxTree.getTop())
                .collect(Collectors.toList());

        return queryResult;
    }
}
