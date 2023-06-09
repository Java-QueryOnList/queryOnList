package org.queryongenericlist.query.queryEngine.engine;

import lombok.NonNull;
import org.queryongenericlist.query.queryNode.QueryNode;

import java.util.List;

public interface QueryEngine<N extends QueryNode > {

    @NonNull
    <T> List<T> apply(N syntaxTree, List<T> onList);
}
