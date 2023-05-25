package org.example.query.queryNode.implementation.filterNode.filterValue.subClasses.operator.comparisonOperator;

import org.example.query.queryNode.implementation.filterNode.filterValue.subClasses.operator.Operator;

public abstract class Comparator implements Operator {

    public abstract boolean compare(Object value1, Object value2);

    public int relation(Object value1, Object value2) {
        if (isNumeric(value1) && isNumeric(value2)) {
            Double intValue1 = Double.parseDouble(value1.toString());
            Double intValue2 = Double.parseDouble(value2.toString());
            return intValue1.compareTo(intValue2);
        } else if (value1 instanceof String && value2 instanceof String) {
            String strValue1 = (String) value1;
            String strValue2 = (String) value2;
            return strValue1.compareTo(strValue2);
        } else if (value1 instanceof String && isNumeric(value2)) {
            Double doubleValue1 = Double.parseDouble((String) value1);
            Double doubleValue2 = Double.parseDouble(value2.toString());
            return doubleValue1.compareTo(doubleValue2);
        } else if (isNumeric(value1) && value2 instanceof String) {
            Double doubleValue1 = Double.parseDouble(value1.toString());
            Double doubleValue2 = Double.parseDouble((String) value2);
            return doubleValue1.compareTo(doubleValue2);
        } else {
            throw new IllegalArgumentException("Unsupported value types");
        }
    }

    private static boolean isNumeric(Object value) {
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
