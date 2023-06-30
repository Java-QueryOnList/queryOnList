package org.queryongenericlist.exceptions.query.abstractsyntaxtree.queryparser.implementation.subparser.filterparser.filtersubparser;

import org.queryongenericlist.exceptions.query.abstractsyntaxtree.queryparser.implementation.subparser.filterparser.FilterParserException;

public class FieldParserException extends FilterParserException {
    public FieldParserException(String message) {
        super(message);
    }

    public FieldParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
