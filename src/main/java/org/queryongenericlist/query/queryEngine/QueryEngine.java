package org.queryongenericlist.query.queryEngine;

import lombok.NonNull;
import org.queryongenericlist.query.abstractSyntaxTree.queryNode.QueryNode;

import java.util.List;

public interface QueryEngine<N extends QueryNode > {

    @NonNull
    <T> List<T> apply(N syntaxTree, List<T> onList);
}
