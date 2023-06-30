package org.queryongenericlist.exceptions.query.abstractsyntaxtree.queryparser.implementation.subparser.filterparser.filtersubparser;

import org.queryongenericlist.exceptions.query.abstractsyntaxtree.queryparser.implementation.subparser.filterparser.FilterParserException;

public class NumberParserException extends FilterParserException {
    public NumberParserException(String message) {
        super(message);
    }

    public NumberParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
