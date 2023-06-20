package org.queryongenericlist.query.abstractsyntaxtree.querynode.subnodes.paginationnode;

import lombok.Data;
import lombok.NonNull;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.QueryNode;

@Data
public class PaginationNode implements QueryNode {
    private final int top;
    private final int skip;

    public PaginationNode(@NonNull int top, @NonNull int skip) {
        this.top = top;
        this.skip = skip;
    }
}
