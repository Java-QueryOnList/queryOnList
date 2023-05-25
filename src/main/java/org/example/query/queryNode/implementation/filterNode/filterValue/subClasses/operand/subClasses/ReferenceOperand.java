package org.example.query.queryNode.implementation.filterNode.filterValue.subClasses.operand.subClasses;

import org.example.query.queryNode.implementation.filterNode.filterValue.subClasses.operand.Operand;

import java.lang.reflect.Field;

public class ReferenceOperand implements Operand {
    private final String[] fieldNames;

    public ReferenceOperand(String[] fieldNames) {
        this.fieldNames = fieldNames;
    }

    public Object getValue(Object obj) throws NoSuchFieldException, IllegalAccessException {
        Object currentObject = obj;

        // Traverse through the nested fields e.g. ["car", "engine", "horsepower"] for obj.element.engine.horsepower
        for (String currentFieldName : fieldNames) {
            Class<?> currentObjectClass = currentObject.getClass();

            // Find the field with the current field name
            Field field = currentObjectClass.getDeclaredField(currentFieldName);

            // Set the field accessible if it's not public
            field.setAccessible(true);

            // Get the value of the current field for the current object
            Object fieldValue = field.get(currentObject);

            // Update the current object to be the field value for the next iteration
            currentObject = fieldValue;
        }

        PrimitiveOperand primitiveOperand = new PrimitiveOperand(currentObject);
        return primitiveOperand.value();
    }
}
