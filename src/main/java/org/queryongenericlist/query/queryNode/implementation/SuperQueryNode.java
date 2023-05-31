package org.example.query.queryNode.implementation;

import lombok.*;
import org.example.query.queryNode.QueryNode;
import org.example.query.queryNode.implementation.filterNode.FilterNode;
import org.example.query.queryNode.implementation.paginationNode.PaginationNode;
import org.example.query.queryNode.implementation.sortingNode.SortingNode;

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
