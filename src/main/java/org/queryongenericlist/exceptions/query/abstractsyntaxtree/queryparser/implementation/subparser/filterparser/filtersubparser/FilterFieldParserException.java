package org.queryongenericlist.exceptions.query.abstractsyntaxtree.queryparser.implementation.subparser.filterparser.filtersubparser;

import org.queryongenericlist.exceptions.query.abstractsyntaxtree.queryparser.implementation.subparser.filterparser.FilterParserException;

public class FilterFieldParserException extends FilterParserException {
    public FilterFieldParserException(String message) {
        super(message);
    }

    public FilterFieldParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
