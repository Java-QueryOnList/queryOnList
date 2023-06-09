package org.queryongenericlist.utils;

import lombok.NonNull;
import org.queryongenericlist.query.queryNode.implementation.filterNode.filterValue.subClasses.operand.subClasses.PrimitiveOperand;

import java.lang.reflect.Field;

/**
 * This class provides helpful methods for working with reflection in Java.
 */
public class GenericClassHelper {

    /**
     * Extracts the value of nested fields from a given object using reflection.
     *
     * @param obj           The object from which to extract the field value.
     * @param fieldNames    An array of field names specifying the nested path of the field.
     * @return              The value of the nested field.
     */
    @NonNull
    static public PrimitiveOperand extractField(@NonNull final Object obj, @NonNull String[] fieldNames) {

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
            final Object fieldValue = getFieldValue(currentObject, field);

            // Update the current object to be the field value for the next iteration
            currentObject = fieldValue;
        }

        return new PrimitiveOperand(currentObject);
    }

    private static Object getFieldValue(Object currentObject, Field field) {
        final Object fieldValue;
        try {
            fieldValue = field.get(currentObject);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return fieldValue;
    }

    private static Field getFieldFromAnySuperclass(Class<?> currentObjectClass, String currentFieldName) {
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
