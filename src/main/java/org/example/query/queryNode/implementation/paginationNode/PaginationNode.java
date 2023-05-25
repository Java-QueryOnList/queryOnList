package org.example.query.queryNode.implementation.paginationNode;

import org.example.query.queryNode.QueryNode;

import java.util.List;

public class PaginationNode implements QueryNode {
    // TODO: Implement fields
    @Override
    public <T> List<T> query(List<T> onList) throws NoSuchFieldException, IllegalAccessException {
        // TODO: Implement method
        return onList;
    }
}
