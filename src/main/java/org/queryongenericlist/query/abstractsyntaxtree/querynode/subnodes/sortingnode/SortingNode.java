package org.queryongenericlist.query.abstractsyntaxtree.querynode.subnodes.sortingnode;

import lombok.Data;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.QueryNode;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.leafnode.subclasses.ReferenceValue;

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
