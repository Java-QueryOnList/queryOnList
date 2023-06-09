package org.queryongenericlist.query.queryEngine.engine.implementation;

import lombok.NonNull;
import org.queryongenericlist.query.queryEngine.engine.QueryEngine;
import org.queryongenericlist.query.queryEngine.engine.implementation.subEngine.FilterPipeline;
import org.queryongenericlist.query.queryEngine.engine.implementation.subEngine.PaginationEngine;
import org.queryongenericlist.query.queryEngine.engine.implementation.subEngine.SortingEngine;
import org.queryongenericlist.query.queryNode.implementation.SuperQueryNode;
import org.queryongenericlist.query.queryNode.implementation.filterNode.FilterNode;
import org.queryongenericlist.query.queryNode.implementation.paginationNode.PaginationNode;
import org.queryongenericlist.query.queryNode.implementation.sortingNode.SortingNode;

import java.util.List;

public class SuperQueryEngine implements QueryEngine<SuperQueryNode> {
    @Override
    public @NonNull <T> List<T> apply(SuperQueryNode syntaxTree, List<T> onList) {
        List<T> queryResult = onList;

        if (syntaxTree != null) {

            // Filter engine
            FilterNode filterNode = syntaxTree.getFilterNode();
            if (filterNode != null) {
                FilterPipeline filterEngine = new FilterPipeline();
                queryResult = filterEngine.apply(filterNode, queryResult);
            }

            // Sorting engine
            SortingNode sortingNode = syntaxTree.getSortingNode();
            if (sortingNode != null) {
                SortingEngine sortingEngine = new SortingEngine();
                queryResult = sortingEngine.apply(sortingNode, queryResult);
            }

            // Pagination Engine
            PaginationNode paginationNode = syntaxTree.getPaginationNode();
            if (paginationNode != null) {
                PaginationEngine paginationEngine = new PaginationEngine();
                queryResult = paginationEngine.apply(paginationNode, queryResult);
            }
        }

        return queryResult;
    }
}
