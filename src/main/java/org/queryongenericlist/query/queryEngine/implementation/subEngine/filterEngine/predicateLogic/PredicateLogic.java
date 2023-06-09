package org.queryongenericlist.query.queryEngine.implementation.subEngine.filterEngine.predicateLogic;

import lombok.NonNull;
import org.queryongenericlist.query.abstractSyntaxTree.queryNode.QueryNode;

import java.util.function.Predicate;

public interface PredicateLogic<T, N extends QueryNode> {
    Predicate<T> fromNode(@NonNull N givenNode);
}
