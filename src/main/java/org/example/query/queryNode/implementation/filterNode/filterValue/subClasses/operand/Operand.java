package org.example.query.queryNode.implementation.filterNode.filterValue.subClasses.operand;

import lombok.NonNull;
import org.example.query.queryNode.implementation.filterNode.filterValue.FilterValue;
import org.example.query.queryNode.implementation.filterNode.filterValue.subClasses.operand.subClasses.PrimitiveOperand;
import org.example.query.queryNode.implementation.filterNode.filterValue.subClasses.operand.subClasses.ReferenceOperand;

public interface Operand extends FilterValue {

    public static boolean isPrimitive(@NonNull final Object value) {
        return value instanceof Integer || value instanceof Boolean || value instanceof Float || value instanceof Double || value instanceof String;
    }

    @NonNull
    public static IllegalArgumentException throwUnsupportedValueTypeException() {
        return new IllegalArgumentException("Invalid value type. Supported types are int, boolean, float, and String.");
    }

    @NonNull
    public static <T> Object resolveOperand(@NonNull final Object leafValue, T element) throws NoSuchFieldException, IllegalAccessException {
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
