package org.queryongenericlist.utils;

import lombok.NonNull;
import org.queryongenericlist.query.queryNode.implementation.filterNode.filterValue.subClasses.operand.subClasses.PrimitiveOperand;
import org.queryongenericlist.query.queryNode.implementation.filterNode.filterValue.subClasses.operand.subClasses.ReferenceOperand;

public class OperandHelper {

    public static boolean checkIfPrimitive(@NonNull final Object value) {
        return value instanceof Integer || value instanceof Boolean || value instanceof Float || value instanceof Double || value instanceof String;
    }

    @NonNull
    public static IllegalArgumentException throwUnsupportedValueTypeException() {
        return new IllegalArgumentException("Invalid value type. Supported types are int, boolean, float, double and String.");
    }

    @NonNull
    public static <T> Object resolveObject(@NonNull final Object leafValue, T element) {
        PrimitiveOperand primitiveOperand = null;
        if (leafValue instanceof PrimitiveOperand) {
            primitiveOperand = (PrimitiveOperand) leafValue;
        } else if (leafValue instanceof ReferenceOperand) {
            Object currentObject = GenericClassHelper.extractField(element, ((ReferenceOperand) leafValue).fieldNames());
            primitiveOperand = new PrimitiveOperand(currentObject);
        }

        assert primitiveOperand != null;
        return primitiveOperand.value();
    }
}
