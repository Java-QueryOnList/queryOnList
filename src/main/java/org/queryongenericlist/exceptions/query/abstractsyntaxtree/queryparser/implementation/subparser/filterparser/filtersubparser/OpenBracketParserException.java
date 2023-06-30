package org.queryongenericlist.exceptions.query.abstractsyntaxtree.queryparser.implementation.subparser.filterparser.filtersubparser;

import org.queryongenericlist.exceptions.query.abstractsyntaxtree.queryparser.implementation.subparser.filterparser.FilterParserException;

public class OpenBracketParserException extends FilterParserException {

    public OpenBracketParserException(String message) {
        super(message);
    }

    public OpenBracketParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
