package org.queryongenericlist.query.queryNode.implementation;

import lombok.*;
import org.queryongenericlist.query.queryNode.QueryNode;
import org.queryongenericlist.query.queryNode.implementation.filterNode.FilterNode;
import org.queryongenericlist.query.queryNode.implementation.paginationNode.PaginationNode;
import org.queryongenericlist.query.queryNode.implementation.sortingNode.SortingNode;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SuperQueryNode implements QueryNode {

    private FilterNode filterNode;
    private SortingNode sortingNode;
    private PaginationNode paginationNode;

    @NonNull
    @Override
    public <T> List<T> query(@NonNull final List<T> onList) throws NoSuchFieldException, IllegalAccessException {
        List<T> result = onList;
        if (filterNode != null) result = filterNode.query(result);
        if (sortingNode != null) result = sortingNode.query(result);
        if (paginationNode != null) result = paginationNode.query(result);
        return result;
    }
}
