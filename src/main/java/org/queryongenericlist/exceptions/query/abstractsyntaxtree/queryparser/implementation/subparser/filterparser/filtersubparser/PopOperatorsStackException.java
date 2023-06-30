package org.queryongenericlist.exceptions.query.abstractsyntaxtree.queryparser.implementation.subparser.filterparser.filtersubparser;

import org.queryongenericlist.exceptions.query.abstractsyntaxtree.queryparser.implementation.subparser.filterparser.FilterParserException;

public class PopOperatorsStackException extends FilterParserException {
    public PopOperatorsStackException(String message) {
        super(message);
    }

    public PopOperatorsStackException(String message, Throwable cause) {
        super(message, cause);
    }
}
