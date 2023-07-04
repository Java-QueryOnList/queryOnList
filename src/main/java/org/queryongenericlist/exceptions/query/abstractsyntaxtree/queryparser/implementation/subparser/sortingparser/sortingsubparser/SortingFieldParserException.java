package org.queryongenericlist.exceptions.query.abstractsyntaxtree.queryparser.implementation.subparser.sortingparser.sortingsubparser;

import org.queryongenericlist.exceptions.query.abstractsyntaxtree.queryparser.implementation.subparser.sortingparser.SortingParserException;

public class SortingFieldParserException extends SortingParserException {
    public SortingFieldParserException(String message) {
        super(message);
    }

    public SortingFieldParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
