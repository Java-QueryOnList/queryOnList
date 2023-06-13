package org.queryongenericlist.query.queryEngine.implementation.subEngine.paginationEngine;

import lombok.NonNull;
import org.queryongenericlist.query.queryEngine.QueryEngine;
import org.queryongenericlist.query.abstractSyntaxTree.queryNode.subNodes.paginationNode.PaginationNode;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PaginationEngine implements QueryEngine<PaginationNode> {
    @Override
    public @NonNull <T> Stream<T> apply(@NonNull PaginationNode syntaxTree, @NonNull Stream<T> onStream) {
        Stream<T> queryResult = onStream
                .skip(syntaxTree.getSkip())
                .limit(syntaxTree.getTop());

        return queryResult;
    }
}
