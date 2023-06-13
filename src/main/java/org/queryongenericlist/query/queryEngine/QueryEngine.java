package org.queryongenericlist.query.queryEngine;

import lombok.NonNull;
import org.queryongenericlist.query.abstractSyntaxTree.queryNode.QueryNode;

import java.util.List;
import java.util.stream.Stream;

public interface QueryEngine<N extends QueryNode > {

    @NonNull
    <T> Stream<T> apply(N syntaxTree, Stream<T> onStream);
}
