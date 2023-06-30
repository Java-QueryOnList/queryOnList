package org.queryongenericlist.exceptions.query.abstractsyntaxtree.queryparser.implementation.subparser.filterparser;

import org.queryongenericlist.exceptions.query.abstractsyntaxtree.queryparser.implementation.SuperQueryParserException;

public class FilterParserException extends SuperQueryParserException {

    public FilterParserException(String message) {
        super(message);
    }

    public FilterParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
