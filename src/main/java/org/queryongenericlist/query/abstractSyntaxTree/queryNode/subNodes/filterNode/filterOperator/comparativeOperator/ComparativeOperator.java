package org.queryongenericlist.query.abstractSyntaxTree.queryNode.subNodes.filterNode.filterOperator.comparativeOperator;

import lombok.NonNull;
import org.queryongenericlist.query.abstractSyntaxTree.queryNode.subNodes.filterNode.filterOperator.FilterOperator;

public abstract class ComparativeOperator implements FilterOperator {

    public abstract boolean compare(final Object value1, final Object value2);

    public int relation(@NonNull final Object value1, @NonNull final Object value2) {
        if (isNumeric(value1) && isNumeric(value2)) {
            final Double intValue1 = Double.parseDouble(value1.toString());
            final Double intValue2 = Double.parseDouble(value2.toString());
            return intValue1.compareTo(intValue2);
        } else if (value1 instanceof String && value2 instanceof String) {
            final String strValue1 = (String) value1;
            final String strValue2 = (String) value2;
            return strValue1.compareTo(strValue2);
        } else if (value1 instanceof Boolean && value2 instanceof Boolean) {
            final Boolean boolValue1 = (Boolean) value1;
            final Boolean boolValue2 = (Boolean) value2;
            return boolValue1.compareTo(boolValue2);
        } else if (value1 instanceof String && isNumeric(value2)) {
            final Double doubleValue1 = Double.parseDouble((String) value1);
            final Double doubleValue2 = Double.parseDouble(value2.toString());
            return doubleValue1.compareTo(doubleValue2);
        } else if (isNumeric(value1) && value2 instanceof String) {
            final Double doubleValue1 = Double.parseDouble(value1.toString());
            final Double doubleValue2 = Double.parseDouble((String) value2);
            return doubleValue1.compareTo(doubleValue2);
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
