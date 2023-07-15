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
            } else if (isStringLike(value1) && isStringLike(value2)) {
                final String strValue1 = getStringValue(value1);
                final String strValue2 = getStringValue(value2);
                return strValue1.compareToIgnoreCase(strValue2);
            } else if (value1 instanceof Boolean boolValue1 && value2 instanceof Boolean boolValue2) {
                return boolValue1.compareTo(boolValue2);
            } else if (isStringLike(value1) && isNumeric(value2)) {
                final Double doubleValue1 = Double.parseDouble(getStringValue(value1));
                final Double doubleValue2 = Double.parseDouble(value2.toString());
                return doubleValue1.compareTo(doubleValue2);
            } else if (isNumeric(value1) && isStringLike(value2)) {
                final Double doubleValue1 = Double.parseDouble(value1.toString());
                final Double doubleValue2 = Double.parseDouble(getStringValue(value2));
                return doubleValue1.compareTo(doubleValue2);
            } else if (value1 instanceof Enum<?> && isStringLike(value2)) {
                final String stringValue1 = value1.toString();
                final String stringValue2 = getStringValue(value2);
                return stringValue1.compareToIgnoreCase(stringValue2);
            } else if (!value1.equals(value2)) {
                return -1000; // just a distinct number to distinguish from other cases
            } else {
                throw new IllegalArgumentException("Unsupported value types");
            }
        } catch (Throwable throwable) {
            if (throwable instanceof ComparativeHelperException) {
                throw (ComparativeHelperException) throwable;
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

    private static boolean isStringLike(@NonNull final Object value) {
        return value instanceof CharSequence || value instanceof Character;
    }

    @NonNull
    private static String getStringValue(final Object value) {
        if (value instanceof Character) {
            return value.toString();
        } else if (value instanceof CharSequence) {
            return value.toString();
        } else {
            return String.valueOf(value);
        }
    }
}

