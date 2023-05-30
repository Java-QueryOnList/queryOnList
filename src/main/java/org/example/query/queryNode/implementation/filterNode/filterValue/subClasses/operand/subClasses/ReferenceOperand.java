package org.example.query.queryNode.implementation.filterNode.filterValue.subClasses.operand.subClasses;

import lombok.NonNull;
import org.example.query.queryNode.implementation.filterNode.filterValue.subClasses.operand.Operand;

import java.lang.reflect.Field;

public class ReferenceOperand implements Operand {
    private final String[] fieldNames;

    public ReferenceOperand(@NonNull final String[] fieldNames) {
        this.fieldNames = fieldNames;
    }

    @NonNull
    public Object getValue(@NonNull final Object obj) throws NoSuchFieldException, IllegalAccessException {
        Object currentObject = obj;

        // Traverse through the nested fields e.g. ["car", "engine", "horsepower"] for obj.element.engine.horsepower
        for (String currentFieldName : fieldNames) {
            final Class<?> currentObjectClass = currentObject.getClass();

            // Find the field with the current field name
            final Field field = currentObjectClass.getDeclaredField(currentFieldName);

            // Set the field accessible if it's not public
            field.setAccessible(true);

            // Get the value of the current field for the current object
            final Object fieldValue = field.get(currentObject);

            // Update the current object to be the field value for the next iteration
            currentObject = fieldValue;
        }

        final PrimitiveOperand primitiveOperand = new PrimitiveOperand(currentObject);
        return primitiveOperand.value();
    }
}
