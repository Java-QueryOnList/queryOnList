package org.queryongenericlist.query.queryNode.implementation.filterNode.filterValue.subClasses.operand.subClasses;

import lombok.NonNull;
import org.queryongenericlist.query.queryNode.implementation.filterNode.filterValue.subClasses.operand.Operand;

public record PrimitiveOperand(Object value) implements Operand {

    public PrimitiveOperand(@NonNull final Object value) {
        if (Operand.isPrimitive(value)) {
            this.value = value;
        } else {
            throw Operand.throwUnsupportedValueTypeException();
        }
    }

}
