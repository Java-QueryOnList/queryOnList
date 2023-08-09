package org.queryongenericlist.query.abstractsyntaxtree.querynode.subnodes;

import lombok.*;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.QueryNode;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.subnodes.filternode.FilterNode;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.subnodes.paginationnode.PaginationNode;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.subnodes.sortingnode.SortingNode;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SuperQueryNode implements QueryNode {

    private FilterNode filterNode;
    private SortingNode sortingNode;
    @Setter
    private PaginationNode paginationNode;
}
