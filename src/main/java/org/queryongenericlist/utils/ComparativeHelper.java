package org.queryongenericlist.utils;

import lombok.NonNull;

public abstract class ComparativeHelper {

    public static int relation(@NonNull final Object value1, @NonNull final Object value2) {
        if (isNumeric(value1) && isNumeric(value2)) {
            final Double intValue1 = Double.parseDouble(value1.toString());
            final Double intValue2 = Double.parseDouble(value2.toString());
            return intValue1.compareTo(intValue2);
        } else if (value1 instanceof String strValue1 && value2 instanceof String strValue2) {
            return strValue1.compareTo(strValue2);
        } else {
            throw new IllegalArgumentException("Unsupported value types");
        }
    }

    private static boolean isNumeric(@NonNull final Object value) {
        if (value instanceof Number) {
            return true;
        }
        if (value instanceof String) {
            try {
                Double.parseDouble((String) value);
                return true;
            } catch (NumberFormatException ignored) {
            }
        }

        return false;
    }
}
