package org.queryongenericlist.query.queryNode.implementation.filterNode.filterValue.subClasses.operand;

import lombok.NonNull;
import org.queryongenericlist.query.queryNode.implementation.filterNode.filterValue.FilterValue;
import org.queryongenericlist.query.queryNode.implementation.filterNode.filterValue.subClasses.operand.subClasses.PrimitiveOperand;
import org.queryongenericlist.query.queryNode.implementation.filterNode.filterValue.subClasses.operand.subClasses.ReferenceOperand;

public interface Operand extends FilterValue {

    static boolean isPrimitive(@NonNull final Object value) {
        return value instanceof Integer || value instanceof Boolean || value instanceof Float || value instanceof Double || value instanceof String;
    }

    @NonNull
    static IllegalArgumentException throwUnsupportedValueTypeException() {
        return new IllegalArgumentException("Invalid value type. Supported types are int, boolean, float, and String.");
    }

    @NonNull
    static <T> Object resolveOperand(@NonNull final Object leafValue, T element) {
        Object result = leafValue;
        // prepare left and right value
        if (result instanceof PrimitiveOperand) {
            result = ((PrimitiveOperand) result).value();
        } else if (result instanceof ReferenceOperand) {
            result = ((ReferenceOperand) result).getValue(element);
        }

        return result;
    }
}
