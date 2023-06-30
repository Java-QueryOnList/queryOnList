package org.queryongenericlist.exceptions.query.abstractsyntaxtree.queryparser.implementation.subparser.paginationparser;

import org.queryongenericlist.exceptions.query.abstractsyntaxtree.queryparser.implementation.SuperQueryParserException;

public class PaginationParserException extends SuperQueryParserException {
    public PaginationParserException(String message) {
        super(message);
    }

    public PaginationParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
