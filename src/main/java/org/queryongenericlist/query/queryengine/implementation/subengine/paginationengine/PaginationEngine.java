package org.queryongenericlist.query.queryengine.implementation.subengine.paginationengine;

import lombok.NonNull;
import org.queryongenericlist.query.queryengine.QueryEngine;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.subnodes.paginationnode.PaginationNode;

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
