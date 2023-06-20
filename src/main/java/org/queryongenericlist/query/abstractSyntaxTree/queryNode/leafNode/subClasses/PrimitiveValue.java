package org.queryongenericlist.query.abstractSyntaxTree.queryNode.leafNode.subClasses;

import org.queryongenericlist.query.abstractSyntaxTree.queryNode.leafNode.LeafNode;
import org.queryongenericlist.utils.ObjectHandler;

public record PrimitiveValue(Object value) implements LeafNode {

    public PrimitiveValue(final Object value) {
        if (ObjectHandler.checkIfPrimitive(value)) {
            this.value = value;
        } else {
            throw ObjectHandler.throwUnsupportedValueTypeException();
        }
    }

}
