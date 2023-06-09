package org.queryongenericlist.query.abstractSyntaxTree.queryNode.subNodes.filterNode;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.queryongenericlist.query.abstractSyntaxTree.queryNode.QueryNode;

@Data
@RequiredArgsConstructor
public class FilterNode implements QueryNode {

    @NonNull
    private final QueryNode head;
    private FilterNode tailLeft;
    private FilterNode tailRight;

}
