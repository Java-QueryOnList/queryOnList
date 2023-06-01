package org.queryongenericlist.utils;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringParser {

    @NonNull
    public static String getFirst(@NonNull final String input, @NonNull final String pattern) {
        String substring = "";

        final Pattern regex = Pattern.compile(pattern);
        final Matcher matcher = regex.matcher(input);

        if (matcher.find()) {
            substring = matcher.group(1);
        }

        return substring;
    }

    @NonNull
    public static List<String> getAll(@NonNull final String input, @NonNull final String pattern) {
        final List<String> substrings = new ArrayList<>();
        final Pattern regex = Pattern.compile(pattern);
        final Matcher matcher = regex.matcher(input);

        while (matcher.find()) {
            final String substring = matcher.group();
            substrings.add(substring);
        }

        return substrings;
    }

}
