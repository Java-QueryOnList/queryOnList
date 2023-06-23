package org.queryongenericlist.query.abstractsyntaxtree.querynode.subnodes.filternode;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.QueryNode;

@Data
@RequiredArgsConstructor
public class FilterNode implements QueryNode {

    @NonNull
    private final QueryNode head;
    private FilterNode tailLeft;
    private FilterNode tailRight;

}
