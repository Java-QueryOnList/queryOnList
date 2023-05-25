package org.example.query.queryNode;

import java.util.List;

public interface QueryNode {
    <T> List<T> query(List<T> onList) throws NoSuchFieldException, IllegalAccessException;
}
