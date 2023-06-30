package org.queryongenericlist.exceptions.query.abstractsyntaxtree.queryparser.implementation.subparser.filterparser.filtersubparser;

import org.queryongenericlist.exceptions.query.abstractsyntaxtree.queryparser.implementation.subparser.filterparser.FilterParserException;

public class NotParserException extends FilterParserException {
    public NotParserException(String message) {
        super(message);
    }

    public NotParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
