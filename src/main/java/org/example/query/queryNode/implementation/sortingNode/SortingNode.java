package org.example.query.queryNode.implementation.sortingNode;

import org.example.query.queryNode.QueryNode;

import java.util.List;

public class SortingNode implements QueryNode {
    // TODO: Implement fields
    @Override
    public <T> List<T> query(List<T> onList) throws NoSuchFieldException, IllegalAccessException {
        // TODO: Implement method
        return onList;
    }
}
