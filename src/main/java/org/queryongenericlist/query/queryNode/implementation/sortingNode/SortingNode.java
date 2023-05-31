package org.example.query.queryNode.implementation.sortingNode;

import lombok.NonNull;
import org.example.query.queryNode.QueryNode;

import java.util.List;

public class SortingNode implements QueryNode {
    // TODO: Implement fields

    @NonNull
    @Override
    public <T> List<T> query(@NonNull final List<T> onList) throws NoSuchFieldException, IllegalAccessException {
        // TODO: Implement method
        return onList;
    }

}
