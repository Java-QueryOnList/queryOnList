package org.queryongenericlist.utils;

import java.util.List;

public class LoggingHelper {


    /**
     * Show the current token and index of the token in the query
     *
     * @param index      the index of the current token e.g. 0
     * @param splitQuery the query split into tokens e.g. [color, eq, 'Blue']
     * @return the string which can be logged e.g.:
     * "engine.horsepower" (index 0) of [engine.horsepower, eq, 180]
     *                                   ^^^^^^^^^^^^^^^^^
     */
    public static String showTokenFromTokens(int index, List<String> splitQuery) {
        StringBuilder builder = new StringBuilder();
        String currentToken = splitQuery.get(index);

        // Add the current token and index
        builder.append('"').append(currentToken).append('"')
                .append(" (index ").append(index).append(") ")
                .append("of ").append(splitQuery).append("\n");

        // Calculating Offset for e.g. "'cherry' (index 2) of [apple, banana, cherry]"
        // offset for "'cherry'"
        int offsetOfToken = "'".length() + currentToken.length() + "'".length();
        // offset for " (index 2) "
        int offsetOfIndexInfo = " (index ".length() + String.valueOf(index).length() + ") ".length();
        // offset for "of "
        int offsetOfOf = "of [".length();
        // offset for "[apple, banana, "
        List<String> sublist = splitQuery.subList(0, index);
        String subStrings = String.join(", ", sublist);
        int offsetOfSubStrings = subStrings.length();
        if (sublist.size() > 0) {
            offsetOfSubStrings += 2;
        }
        // total offset
        int offset = offsetOfToken + offsetOfIndexInfo + offsetOfOf + offsetOfSubStrings;

        // Add spaces for offset
        builder.append(" ".repeat(Math.max(0, offset)));

        // Add the "^" character for the length of the current token
        builder.append("^".repeat(currentToken.length()));
        builder.append("\n");

        // Return the final string
        return builder.toString();
    }
}
