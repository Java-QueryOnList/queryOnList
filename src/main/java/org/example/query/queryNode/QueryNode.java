package org.example.query.queryNode;

import lombok.NonNull;

import java.util.List;

public interface QueryNode {

    @NonNull
    <T> List<T> query(final List<T> onList) throws NoSuchFieldException, IllegalAccessException;

}
