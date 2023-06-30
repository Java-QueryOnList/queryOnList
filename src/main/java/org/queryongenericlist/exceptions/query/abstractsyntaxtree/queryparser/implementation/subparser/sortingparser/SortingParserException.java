package org.queryongenericlist.exceptions.query.abstractsyntaxtree.queryparser.implementation.subparser.sortingparser;

import org.queryongenericlist.exceptions.query.abstractsyntaxtree.queryparser.implementation.SuperQueryParserException;

public class SortingParserException extends SuperQueryParserException {
    public SortingParserException(String message) {
        super(message);
    }

    public SortingParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
