package org.queryongenericlist.exceptions.query.abstractsyntaxtree.queryparser.implementation;

import org.queryongenericlist.exceptions.query.abstractsyntaxtree.queryparser.QueryParserException;

public class SuperQueryParserException extends QueryParserException {
    public SuperQueryParserException(String message) {
        super(message);
    }

    public SuperQueryParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
