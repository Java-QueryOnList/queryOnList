package org.queryongenericlist.query.queryEngine.engine.implementation.subEngine;

import lombok.NonNull;
import org.queryongenericlist.query.queryEngine.engine.QueryEngine;
import org.queryongenericlist.query.queryNode.implementation.paginationNode.PaginationNode;

import java.util.List;

public class PaginationEngine implements QueryEngine<PaginationNode> {
    @Override
    public @NonNull <T> List<T> apply(PaginationNode syntaxTree, List<T> onList) {
        List<T> queriedList = onList;

        if (syntaxTree != null) {
            // TODO: Apply Engine
        }

        return queriedList;
    }
}
