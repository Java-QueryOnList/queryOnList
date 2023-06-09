package org.queryongenericlist.query.queryNode.implementation.sortingNode;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.queryongenericlist.query.queryNode.QueryNode;

@Data
@RequiredArgsConstructor
public class SortingNode implements QueryNode {

    // head
    private final boolean ascending;
    // tail
    private SortingNode nextSort;
}
