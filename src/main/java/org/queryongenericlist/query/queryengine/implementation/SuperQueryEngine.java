package org.queryongenericlist.query.queryengine.implementation;

import lombok.Getter;
import lombok.NonNull;
import org.queryongenericlist.exceptions.query.queryengine.implementation.SuperQueryEngineException;
import org.queryongenericlist.query.queryengine.QueryEngine;
import org.queryongenericlist.query.queryengine.implementation.subengine.filterengine.FilterEngine;
import org.queryongenericlist.query.queryengine.implementation.subengine.paginationengine.PaginationEngine;
import org.queryongenericlist.query.queryengine.implementation.subengine.sortingengine.SortingEngine;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.subnodes.SuperQueryNode;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.subnodes.filternode.FilterNode;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.subnodes.paginationnode.PaginationNode;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.subnodes.sortingnode.SortingNode;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SuperQueryEngine implements QueryEngine<SuperQueryNode> {
    @Getter
    private int filteredResultSize = -1;

    @Override
    public @NonNull <T> Stream<T> apply(SuperQueryNode syntaxTree, Stream<T> onStream) {
        try {
            Stream<T> queryResult = onStream;

            if (syntaxTree != null) {

                // Filter engine
                FilterNode filterNode = syntaxTree.getFilterNode();
                if (filterNode != null) {
                    FilterEngine filterEngine = new FilterEngine();
                    queryResult = filterEngine.apply(filterNode, queryResult);
                    // Save filtered list information for pagination later
                    List<T> filteredList = queryResult.toList();
                    filteredResultSize = filteredList.size();
                    // Reset stream
                    queryResult = filteredList.stream();
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
        } catch (Throwable throwable) {
            if (throwable instanceof SuperQueryEngineException) {
                throw throwable;
            } else {
                throw new SuperQueryEngineException("An error occurred while applying the query engines.", throwable);
            }
        }
    }
}
