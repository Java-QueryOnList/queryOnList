package org.example.query.queryNode.implementation.filterNode.filterValue.subClasses.operand.subClasses;

import org.example.query.queryNode.implementation.filterNode.filterValue.subClasses.operand.Operand;

public record PrimitiveOperand(Object value) implements Operand {
    public PrimitiveOperand(Object value) {
        if (Operand.isPrimitive(value)) {
            this.value = value;
        } else {
            throw Operand.throwUnsupportedValueTypeException();
        }
    }
}
