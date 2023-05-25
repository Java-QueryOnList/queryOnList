package org.example.query.queryNode.implementation;

import org.example.query.queryNode.QueryNode;
import org.example.query.queryNode.implementation.filterNode.FilterNode;
import org.example.query.queryNode.implementation.paginationNode.PaginationNode;
import org.example.query.queryNode.implementation.sortingNode.SortingNode;

import java.util.List;

public class SuperQueryNode implements QueryNode {
    FilterNode filterNode;
    SortingNode sortingNode;
    PaginationNode paginationNode;

    public SuperQueryNode(FilterNode filterNode, SortingNode sortingNode, PaginationNode paginationNode) {
        this.filterNode = filterNode;
        this.sortingNode = sortingNode;
        this.paginationNode = paginationNode;
    }

    @Override
    public <T> List<T> query(List<T> onList) throws NoSuchFieldException, IllegalAccessException {
        List<T> result = onList;
        if (filterNode != null) result = filterNode.query(result);
        if (sortingNode != null) result = sortingNode.query(result);
        if (paginationNode != null) result = paginationNode.query(result);
        return result;
    }
}
