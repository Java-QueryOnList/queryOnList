package testHelpers.utils;

import java.lang.reflect.Field;
import java.util.List;

public class LoggingUtils {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";

    private static final String SUCCEEDED_STRING = ANSI_GREEN + "SUCCEEDED" + ANSI_RESET;
    private static final String FAILED_STRING = ANSI_RED + "FAILED" + ANSI_RESET;

    /**
     * Gets a list of Objects of type T and returns the list in form of String. Can be important to compare to lists of objects
     * @param objects list of objects whose content you want to convert to a String
     * @return returns the list of objects in form of a string with their fields and values
     * @param <T> the type of the elemenets in the object list
     */
    public static <T> String genericListToString(List<T> objects) {
        StringBuilder result = new StringBuilder();

        for (T obj : objects) {
            String subResult = genericObjectToString(obj);
            result.append(subResult);
        }

        return result.toString();
    }


    public static <T> String getTestResultString(String query, List<T> expectedList, List<T> queriedList, Boolean testSucceeded) {
        // Add title
        StringBuilder result = new StringBuilder("########## NEW CASE: ");
        result.append(testSucceeded ? SUCCEEDED_STRING : FAILED_STRING);
        result.append(" ##########\n");
        result.append("\n");

        // Add query
        result.append("Query:\n");
        result.append("'");
        result.append(query);
        result.append("'");
        result.append("\n");
        result.append("\n");

        // Add Expected
        result.append("Expected List:\n");
        result.append(genericListToString(expectedList));
        result.append("\n");

        // Add Actual
        result.append("Actually Queried:\n");
        result.append(genericListToString(queriedList));
        result.append("\n");

        return result.toString();
    }

    private static <T> String genericObjectToString(T obj) {
        StringBuilder result = new StringBuilder();

        Class<?> objClass = obj.getClass();
        Field[] fields = objClass.getDeclaredFields();

        result.append(getLastPartAfterPoint(objClass.getName()));
        result.append("(");

        for(int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true); // Make the field accessible

            try {
                Object value = fields[i].get(obj);
                result.append(fields[i].getName())
                        .append(": ")
                        .append(value.toString());
                if (i != fields.length - 1) result.append(", ");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        result.append(")");
        result.append(System.lineSeparator());

        return result.toString();
    }

    private static String getLastPartAfterPoint(String str) {
        return str.substring(str.lastIndexOf('.') + 1);
    }
}