package org.queryongenericlist.query.abstractSyntaxTree.queryParser;

import lombok.NonNull;
import org.queryongenericlist.query.abstractSyntaxTree.queryNode.QueryNode;

public interface QueryParser<N extends QueryNode> {

    @NonNull
    N parse();

}
