package org.queryongenericlist.utils.stringparser.applied;

import lombok.NonNull;
import org.queryongenericlist.utils.stringparser.StringParser;

public class UrlParser {

    /**
     * Get query from full url
     *
     * @param fullUrl e.g.: "https://www.example.com/people?$filter=name eq 'david'&$orderBy=hireDate" to get "$filter=name eq 'david'&$orderBy=hireDate"
     * @return query string e.g.: "$filter=name eq 'david'&$orderBy=hireDate"
     */
    public static String getQueryFromUrl(@NonNull final String fullUrl) {
        return StringParser.getFirst(fullUrl, "\\?(.*)");
    }

    /**
     * Get base url from full url
     *
     * @param fullUrl e.g.: "https://www.example.com/people?$filter=name eq 'david'&$orderBy=hireDate"
     * @return base url string e.g.: "https://www.example.com/people"
     */
    public static String getBaseUrlFromUrl(@NonNull final String fullUrl) {
        return StringParser.getFirst(fullUrl, "(.*)\\?");
    }
}
