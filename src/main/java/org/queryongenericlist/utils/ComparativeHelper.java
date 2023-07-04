package org.queryongenericlist.utils;

import lombok.NonNull;
import org.queryongenericlist.exceptions.utils.ComparativeHelperException;

import java.util.Comparator;

public class ComparativeHelper implements Comparator<Object> {

    @NonNull
    @Override
    public int compare(final Object value1, final Object value2) {
        try {
            if (value1 == value2) {
                return 0;
            } else if (value1 == null) {
                return 1;
            } else if (value2 == null) {
                return -1;
            } else if (isNumeric(value1) && isNumeric(value2)) {
                final Double intValue1 = Double.parseDouble(value1.toString());
                final Double intValue2 = Double.parseDouble(value2.toString());
                return intValue1.compareTo(intValue2);
            } else if (value1 instanceof String strValue1 && value2 instanceof String strValue2) {
                return strValue1.toLowerCase().compareTo(strValue2.toLowerCase());
            } else if (value1 instanceof Boolean boolValue1 && value2 instanceof Boolean boolValue2) {
                return boolValue1.compareTo(boolValue2);
            } else if (value1 instanceof String && isNumeric(value2)) {
                final Double doubleValue1 = Double.parseDouble((String) value1);
                final Double doubleValue2 = Double.parseDouble(value2.toString());
                return doubleValue1.compareTo(doubleValue2);
            } else if (isNumeric(value1) && value2 instanceof String) {
                final Double doubleValue1 = Double.parseDouble(value1.toString());
                final Double doubleValue2 = Double.parseDouble((String) value2);
                return doubleValue1.compareTo(doubleValue2);
            } else if (value1 instanceof Enum<?> && value2 instanceof String stringValue2) {
                String stringValue1 = value1.toString();
                return stringValue1.compareTo(stringValue2);
            } else if (!value1.equals(value2)) {
                return -1000; // just a distinct number to distinguish from other cases
            } else {
                throw new IllegalArgumentException("Unsupported value types");
            }
        } catch (Throwable throwable) {
            if (throwable instanceof ComparativeHelperException) {
                throw throwable;
            } else {
                throw new ComparativeHelperException("Error while comparing values: " + value1 + " and " + value2, throwable);
            }
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
