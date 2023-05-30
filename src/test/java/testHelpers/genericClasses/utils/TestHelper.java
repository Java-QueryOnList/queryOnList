package testHelpers.genericClasses.utils;

import java.lang.reflect.Field;
import java.util.List;

public class TestHelper {

    /**
     * Gets a list of Objects of type T and returns the list in form of String. Can be important to compare to lists of objects
     * @param objects list of objects you want to convert to a String
     * @return returns the list of objects in form of a string with their fields and values
     * @param <T> the type of the elemenets in the object list
     */
    public static <T> String listToString(List<T> objects) {
        StringBuilder result = new StringBuilder();

        for (T obj : objects) {
            Class<?> objClass = obj.getClass();
            Field[] fields = objClass.getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true); // Make the field accessible

                try {
                    Object value = field.get(obj);
                    result.append(field.getName())
                            .append(": ")
                            .append(value)
                            .append(", ");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            result.append(System.lineSeparator());
        }

        return result.toString();
    }
}