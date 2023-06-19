package org.queryongenericlist.query.abstractSyntaxTree.queryNode.leafNode.subClasses;

import lombok.NonNull;
import org.queryongenericlist.query.abstractSyntaxTree.queryNode.leafNode.LeafNode;
import org.queryongenericlist.utils.OperandHelper;

public record PrimitiveValue(Object value) implements LeafNode {

    public PrimitiveValue(final Object value) {
        if (OperandHelper.checkIfPrimitive(value)) {
            this.value = value;
        } else {
            throw OperandHelper.throwUnsupportedValueTypeException();
        }
    }

}
