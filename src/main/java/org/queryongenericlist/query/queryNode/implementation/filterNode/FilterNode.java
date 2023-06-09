package org.queryongenericlist.query.queryNode.implementation.filterNode;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.queryongenericlist.query.queryNode.QueryNode;
import org.queryongenericlist.query.queryNode.implementation.filterNode.filterValue.FilterValue;

@Data
@RequiredArgsConstructor
public class FilterNode implements QueryNode {

    @NonNull
    private final FilterValue head;
    private FilterNode tailLeft;
    private FilterNode tailRight;

}
