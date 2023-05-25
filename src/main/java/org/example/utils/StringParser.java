package org.example.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringParser {

    public static String getFirst(String input, String pattern) {
        String substring = "";

        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(input);

        if (matcher.find()) {
            substring = matcher.group(1);
        }

        return substring;
    }

    public static List<String> getAll(String input, String pattern) {
        List<String> substrings = new ArrayList<>();
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(input);

        while (matcher.find()) {
            String substring = matcher.group();
            substrings.add(substring);
        }

        return substrings;
    }
}
