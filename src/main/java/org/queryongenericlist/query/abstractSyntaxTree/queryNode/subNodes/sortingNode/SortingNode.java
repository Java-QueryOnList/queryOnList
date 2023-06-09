package org.queryongenericlist.query.abstractSyntaxTree.queryNode.subNodes.sortingNode;

import lombok.Data;
import org.queryongenericlist.query.abstractSyntaxTree.queryNode.QueryNode;
import org.queryongenericlist.query.abstractSyntaxTree.queryNode.leafNode.subClasses.ReferenceValue;

@Data
public class SortingNode implements QueryNode {

    // head
    private ReferenceValue head;
    private boolean ascending;
    // tail
    private SortingNode nextSort;

    public SortingNode() {
        this.ascending = true;
    }
}
