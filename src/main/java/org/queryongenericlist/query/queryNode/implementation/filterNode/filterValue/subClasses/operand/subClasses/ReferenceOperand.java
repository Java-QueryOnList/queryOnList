package org.queryongenericlist.query.queryNode.implementation.filterNode.filterValue.subClasses.operand.subClasses;

import lombok.NonNull;
import org.queryongenericlist.query.queryNode.implementation.filterNode.filterValue.subClasses.operand.Operand;

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

            Field field;
            try {
                // Find the field with the current field name
                field = currentObjectClass.getDeclaredField(currentFieldName);
            } catch (Exception e) {
                // if field doesn't exist in current class search it recursively in parent classes
                field = getFieldFromAnySuperclass(currentObjectClass, currentFieldName);
            }

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

    private Field getFieldFromAnySuperclass(Class<?> currentObjectClass, String currentFieldName) {
        Field field;
        Class<?> superClass = currentObjectClass.getSuperclass();

        try {
            // try if field exists in current superclass
            field = superClass.getDeclaredField(currentFieldName);
        } catch (Exception e) {
            // call method recursively to find a superclass where field exists
            field = getFieldFromAnySuperclass(superClass, currentFieldName);
        }

        return field;
    }
}
