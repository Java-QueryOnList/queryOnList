package org.queryongenericlist.query.abstractsyntaxtree.querynode.leafnode.subclasses;

import org.queryongenericlist.exceptions.abstractsyntaxtree.InvalidPrimitiveValueException;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.leafnode.LeafNode;
import org.queryongenericlist.utils.Primitives;

public record PrimitiveValue(Object value) implements LeafNode {

    public PrimitiveValue(final Object value) {
        if (Primitives.isPrimitive(value)) {
            this.value = value;
        } else {
            throw new InvalidPrimitiveValueException("Invalid primitive value: ", value, Primitives.supportedTypeNames());
        }
    }

}
