package org.queryongenericlist.exceptions.query.abstractsyntaxtree.queryparser.implementation.subparser.filterparser.filtersubparser;

import org.queryongenericlist.exceptions.query.abstractsyntaxtree.queryparser.implementation.subparser.filterparser.FilterParserException;

public class OperatorParserException extends FilterParserException {
    public OperatorParserException(String message) {
        super(message);
    }

    public OperatorParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
