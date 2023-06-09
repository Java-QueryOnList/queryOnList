package org.queryongenericlist.query.abstractSyntaxTree.queryNode.subNodes.sortingNode;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.queryongenericlist.query.abstractSyntaxTree.queryNode.QueryNode;

@Data
@RequiredArgsConstructor
public class SortingNode implements QueryNode {

    // head
    private final boolean ascending;
    // tail
    private SortingNode nextSort;
}
