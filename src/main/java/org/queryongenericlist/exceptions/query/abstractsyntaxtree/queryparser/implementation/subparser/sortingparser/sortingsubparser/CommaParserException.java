package org.queryongenericlist.exceptions.query.abstractsyntaxtree.queryparser.implementation.subparser.sortingparser.sortingsubparser;

import org.queryongenericlist.exceptions.query.abstractsyntaxtree.queryparser.implementation.subparser.sortingparser.SortingParserException;

public class CommaParserException extends SortingParserException {
    public CommaParserException(String message) {
        super(message);
    }

    public CommaParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
