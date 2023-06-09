package org.queryongenericlist.query.queryEngine.pipeline;

import lombok.NonNull;
import org.queryongenericlist.query.queryNode.QueryNode;

import java.util.function.Predicate;

public interface PredicatePipeline<T, N extends QueryNode> {
    Predicate<T> fromNode(@NonNull N givenNode, @NonNull Predicate<T> currentPredicateChain);
}
