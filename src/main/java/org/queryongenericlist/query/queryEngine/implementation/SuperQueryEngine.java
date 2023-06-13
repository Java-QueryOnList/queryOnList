package org.queryongenericlist.query.queryEngine.implementation;

import lombok.NonNull;
import org.queryongenericlist.query.queryEngine.QueryEngine;
import org.queryongenericlist.query.queryEngine.implementation.subEngine.filterEngine.FilterEngine;
import org.queryongenericlist.query.queryEngine.implementation.subEngine.paginationEngine.PaginationEngine;
import org.queryongenericlist.query.queryEngine.implementation.subEngine.sortingEngine.SortingEngine;
import org.queryongenericlist.query.abstractSyntaxTree.queryNode.subNodes.SuperQueryNode;
import org.queryongenericlist.query.abstractSyntaxTree.queryNode.subNodes.filterNode.FilterNode;
import org.queryongenericlist.query.abstractSyntaxTree.queryNode.subNodes.paginationNode.PaginationNode;
import org.queryongenericlist.query.abstractSyntaxTree.queryNode.subNodes.sortingNode.SortingNode;

import java.util.List;
import java.util.stream.Stream;

public class SuperQueryEngine implements QueryEngine<SuperQueryNode> {
    @Override
    public @NonNull <T> Stream<T> apply(SuperQueryNode syntaxTree, Stream<T> onStream) {
        Stream<T> queryResult = onStream;

        if (syntaxTree != null) {

            // Filter engine
            FilterNode filterNode = syntaxTree.getFilterNode();
            if (filterNode != null) {
                FilterEngine filterEngine = new FilterEngine();
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
