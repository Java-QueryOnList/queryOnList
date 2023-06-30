package org.queryongenericlist.exceptions.query.abstractsyntaxtree.queryparser;

import org.queryongenericlist.exceptions.QueryOnListException;

public class QueryParserException extends QueryOnListException {
    public QueryParserException(String message) {
        super(message);
    }

    public QueryParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
