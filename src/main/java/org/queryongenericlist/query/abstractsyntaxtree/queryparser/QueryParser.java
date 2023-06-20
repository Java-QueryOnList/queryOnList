package org.queryongenericlist.query.abstractsyntaxtree.queryparser;

import lombok.NonNull;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.QueryNode;

public interface QueryParser<N extends QueryNode> {

    @NonNull
    N parse();

}
