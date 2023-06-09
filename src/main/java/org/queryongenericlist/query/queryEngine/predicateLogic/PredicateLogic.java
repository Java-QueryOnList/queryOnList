package org.queryongenericlist.query.queryEngine.predicateLogic;

import lombok.NonNull;
import org.queryongenericlist.query.queryNode.QueryNode;

import java.util.function.Predicate;

public interface PredicateLogic<T, N extends QueryNode> {
    Predicate<T> fromNode(@NonNull N givenNode);
}
