package org.example;

import org.example.utils.StringParser;

/**
 * Hello world!
 */
public class Main {

    public static void main(final String[] args) {
        String subString = "";
        final String FILTER_PATTERN = "\\$filter=([^&]+)";
        final String query = "$filter=radius gt 5.0";
        subString = StringParser.getFirst(query, FILTER_PATTERN);
        System.out.println(subString);
    }

}

