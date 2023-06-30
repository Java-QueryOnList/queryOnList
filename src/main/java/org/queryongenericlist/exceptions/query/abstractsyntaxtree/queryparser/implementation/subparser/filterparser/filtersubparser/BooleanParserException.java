package org.queryongenericlist.exceptions.query.abstractsyntaxtree.queryparser.implementation.subparser.filterparser.filtersubparser;

import org.queryongenericlist.exceptions.query.abstractsyntaxtree.queryparser.implementation.subparser.filterparser.FilterParserException;

public class BooleanParserException extends FilterParserException {
    public BooleanParserException(String message) {
        super(message);
    }

    public BooleanParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
