package org.queryongenericlist.query.queryengine.implementation.subengine.paginationengine;

import lombok.NonNull;
import org.queryongenericlist.exceptions.query.queryengine.implementation.subengine.paginationengine.PaginationEngineException;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.subnodes.paginationnode.PaginationNode;
import org.queryongenericlist.query.queryengine.QueryEngine;

import java.util.stream.Stream;

public class PaginationEngine implements QueryEngine<PaginationNode> {
    @Override
    public @NonNull <T> Stream<T> apply(@NonNull PaginationNode syntaxTree, @NonNull Stream<T> onStream) {
        try {
            Stream<T> queryResult = onStream
                    .skip(syntaxTree.getSkip())
                    .limit(syntaxTree.getTop());

            return queryResult;
        } catch (Throwable throwable) {
            if (throwable instanceof PaginationEngineException) {
                throw throwable;
            } else {
                throw new PaginationEngineException("An error occurred while applying the pagination engine.", throwable);
            }
        }
    }
}
