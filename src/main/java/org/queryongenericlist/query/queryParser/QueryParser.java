package org.queryongenericlist.query.queryParser;

import lombok.NonNull;
import org.queryongenericlist.query.queryNode.QueryNode;

public interface QueryParser<N extends QueryNode> {

    @NonNull
    N parse();

}
