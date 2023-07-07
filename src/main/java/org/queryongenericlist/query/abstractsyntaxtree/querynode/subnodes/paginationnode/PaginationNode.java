package org.queryongenericlist.query.abstractsyntaxtree.querynode.subnodes.paginationnode;

import lombok.Data;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.QueryNode;

@Data
public class PaginationNode implements QueryNode {
    private final int skip;
    private final int top;
}
