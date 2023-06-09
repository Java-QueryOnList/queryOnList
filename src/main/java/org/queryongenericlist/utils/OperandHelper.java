package org.queryongenericlist.utils;

import lombok.NonNull;
import org.queryongenericlist.query.abstractSyntaxTree.queryNode.leafNode.subClasses.PrimitiveValue;
import org.queryongenericlist.query.abstractSyntaxTree.queryNode.leafNode.subClasses.ReferenceValue;

import java.util.List;

public class OperandHelper {

    public static boolean checkIfPrimitive(@NonNull final Object value) {
        return value instanceof Integer
                || value instanceof Boolean
                || value instanceof Float
                || value instanceof Double
                || value instanceof String
                || value instanceof List<?>;
    }

    @NonNull
    public static IllegalArgumentException throwUnsupportedValueTypeException() {
        return new IllegalArgumentException("Invalid value type. Supported types are int, boolean, float, double and String.");
    }

    @NonNull
    public static <T> Object resolveObject(@NonNull final Object leafValue, T element) {
        PrimitiveValue primitiveOperand = null;
        if (leafValue instanceof PrimitiveValue) {
            primitiveOperand = (PrimitiveValue) leafValue;
        } else if (leafValue instanceof ReferenceValue) {
            primitiveOperand = GenericClassHelper.extractAllFields(element, ((ReferenceValue) leafValue).fieldNames());
        }

        assert primitiveOperand != null;
        return primitiveOperand.value();
    }
}
