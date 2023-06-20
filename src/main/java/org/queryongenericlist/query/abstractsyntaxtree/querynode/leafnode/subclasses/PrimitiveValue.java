package org.queryongenericlist.query.abstractsyntaxtree.querynode.leafnode.subclasses;

import org.queryongenericlist.query.abstractsyntaxtree.querynode.leafnode.LeafNode;
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
