package org.queryongenericlist.query.queryengine;

import lombok.NonNull;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.QueryNode;

import java.util.stream.Stream;

public interface QueryEngine<N extends QueryNode > {

    @NonNull
    <T> Stream<T> apply(N syntaxTree, Stream<T> onStream);
}
