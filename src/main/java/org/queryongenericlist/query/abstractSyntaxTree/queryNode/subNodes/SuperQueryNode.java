package org.queryongenericlist.query.abstractSyntaxTree.queryNode.subNodes;

import lombok.*;
import org.queryongenericlist.query.abstractSyntaxTree.queryNode.QueryNode;
import org.queryongenericlist.query.abstractSyntaxTree.queryNode.subNodes.filterNode.FilterNode;
import org.queryongenericlist.query.abstractSyntaxTree.queryNode.subNodes.paginationNode.PaginationNode;
import org.queryongenericlist.query.abstractSyntaxTree.queryNode.subNodes.sortingNode.SortingNode;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SuperQueryNode implements QueryNode {

    private FilterNode filterNode;
    private SortingNode sortingNode;
    private PaginationNode paginationNode;
}
