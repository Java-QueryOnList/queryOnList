package org.queryongenericlist.query.queryEngine.implementation;

import lombok.NonNull;
import org.queryongenericlist.query.queryEngine.QueryEngine;
import org.queryongenericlist.query.queryEngine.implementation.subEngine.FilterEngine;
import org.queryongenericlist.query.queryEngine.implementation.subEngine.PaginationEngine;
import org.queryongenericlist.query.queryEngine.implementation.subEngine.SortingEngine;
import org.queryongenericlist.query.queryNode.implementation.SuperQueryNode;

import java.util.List;

public class SuperQueryEngine implements QueryEngine<SuperQueryNode> {
    @Override
    public @NonNull <T> List<T> apply(SuperQueryNode syntaxTree, List<T> onList) {
        List<T> queriedList = onList;

        if (syntaxTree != null) {
            // Filter engine
            FilterEngine filterEngine = new FilterEngine();
            queriedList = filterEngine.apply(syntaxTree.getFilterNode(), queriedList);

            // Sorting engine
            SortingEngine sortingEngine = new SortingEngine();
            queriedList = sortingEngine.apply(syntaxTree.getSortingNode(), queriedList);

            // Pagination Engine
            PaginationEngine paginationEngine = new PaginationEngine();
            queriedList = paginationEngine.apply(syntaxTree.getPaginationNode(), queriedList);
        }

        return queriedList;
    }
}
