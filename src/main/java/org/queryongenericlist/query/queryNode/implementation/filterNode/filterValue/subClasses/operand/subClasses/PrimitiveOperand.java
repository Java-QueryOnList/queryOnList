package org.queryongenericlist.query.queryNode.implementation.filterNode.filterValue.subClasses.operand.subClasses;

import lombok.NonNull;
import org.queryongenericlist.query.queryNode.implementation.filterNode.filterValue.subClasses.operand.Operand;
import org.queryongenericlist.utils.OperandHelper;

public record PrimitiveOperand(Object value) implements Operand {

    public PrimitiveOperand(@NonNull final Object value) {
        if (OperandHelper.checkIfPrimitive(value)) {
            this.value = value;
        } else {
            throw OperandHelper.throwUnsupportedValueTypeException();
        }
    }

}
