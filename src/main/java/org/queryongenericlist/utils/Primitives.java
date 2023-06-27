package org.queryongenericlist.utils;

import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Primitives {
    private static final List<PrimitiveType> SUPPORTED_PRIMITIVE_TYPES = new ArrayList<>();

    static {
        SUPPORTED_PRIMITIVE_TYPES.add(new PrimitiveType("Null", Objects::isNull));
        SUPPORTED_PRIMITIVE_TYPES.add(new PrimitiveType("Integer", value -> value instanceof Integer));
        SUPPORTED_PRIMITIVE_TYPES.add(new PrimitiveType("Boolean", value -> value instanceof Boolean));
        SUPPORTED_PRIMITIVE_TYPES.add(new PrimitiveType("Float", value -> value instanceof Float));
        SUPPORTED_PRIMITIVE_TYPES.add(new PrimitiveType("Double", value -> value instanceof Double));
        SUPPORTED_PRIMITIVE_TYPES.add(new PrimitiveType("String", value -> value instanceof String));
        SUPPORTED_PRIMITIVE_TYPES.add(new PrimitiveType("Enum", value -> value instanceof Enum));
        SUPPORTED_PRIMITIVE_TYPES.add(new PrimitiveType("Collection", value -> value instanceof Collection));
        SUPPORTED_PRIMITIVE_TYPES.add(new PrimitiveType("Array", value -> value.getClass().isArray()));
    }

    /**
     * Check if the given object is 'primitive' and suitable for further operations
     *
     * @param value the object which is getting checked
     * @return whether the given object is primitive or not
     */
    public static boolean isPrimitive(Object value) {
        for (PrimitiveType type : SUPPORTED_PRIMITIVE_TYPES) {
            if (type.isInstance(value)) {
                return true;
            }
        }
        return false;
    }

    public static List<String> supportedTypeNames() {
        return SUPPORTED_PRIMITIVE_TYPES.stream()
                .map(PrimitiveType::getTypeName)
                .collect(Collectors.toList());
    }

    @RequiredArgsConstructor
    private static class PrimitiveType {
        private final String typeName;
        private final Function<Object, Boolean> isInstance;

        private String getTypeName() {
            return typeName;
        }

        private boolean isInstance(Object value) {
            return isInstance.apply(value);
        }
    }

}