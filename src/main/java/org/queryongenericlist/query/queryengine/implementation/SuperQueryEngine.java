package org.queryongenericlist.query.queryengine.implementation;

import lombok.NonNull;
import org.queryongenericlist.query.queryengine.QueryEngine;
import org.queryongenericlist.query.queryengine.implementation.subengine.filterengine.FilterEngine;
import org.queryongenericlist.query.queryengine.implementation.subengine.paginationengine.PaginationEngine;
import org.queryongenericlist.query.queryengine.implementation.subengine.sortingengine.SortingEngine;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.subnodes.SuperQueryNode;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.subnodes.filternode.FilterNode;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.subnodes.paginationnode.PaginationNode;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.subnodes.sortingnode.SortingNode;

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
