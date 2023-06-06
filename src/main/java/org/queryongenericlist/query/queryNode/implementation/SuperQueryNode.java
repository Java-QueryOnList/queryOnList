package org.queryongenericlist.query.queryNode.implementation;

import lombok.*;
import org.queryongenericlist.query.queryNode.QueryNode;
import org.queryongenericlist.query.queryNode.implementation.filterNode.FilterNode;
import org.queryongenericlist.query.queryNode.implementation.paginationNode.PaginationNode;
import org.queryongenericlist.query.queryNode.implementation.sortingNode.SortingNode;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SuperQueryNode implements QueryNode {

    private FilterNode filterNode;
    private SortingNode sortingNode;
    private PaginationNode paginationNode;
}
