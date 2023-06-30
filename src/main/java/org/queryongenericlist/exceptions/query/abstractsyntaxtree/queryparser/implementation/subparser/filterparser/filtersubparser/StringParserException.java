package org.queryongenericlist.exceptions.query.abstractsyntaxtree.queryparser.implementation.subparser.filterparser.filtersubparser;

import org.queryongenericlist.exceptions.query.abstractsyntaxtree.queryparser.implementation.subparser.filterparser.FilterParserException;

public class StringParserException extends FilterParserException {
    public StringParserException(String message) {
        super(message);
    }

    public StringParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
