package org.queryongenericlist.exceptions.query.abstractsyntaxtree.queryparser.implementation.subparser.filterparser.filtersubparser;

import org.queryongenericlist.exceptions.query.abstractsyntaxtree.queryparser.implementation.subparser.filterparser.FilterParserException;

public class InvalidStringInputException extends FilterParserException {

    public InvalidStringInputException(String message) {
        super(message);
    }

    public InvalidStringInputException(String message, Throwable cause) {
        super(message, cause);
    }
}
