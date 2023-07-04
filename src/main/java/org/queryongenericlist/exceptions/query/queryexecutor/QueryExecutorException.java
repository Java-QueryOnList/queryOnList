package org.queryongenericlist.exceptions.query.queryexecutor;

import org.queryongenericlist.exceptions.QueryOnListException;

public class QueryExecutorException extends QueryOnListException {
    public QueryExecutorException(String message) {
        super(message);
    }

    public QueryExecutorException(String message, Throwable cause) {
        super(message, cause);
    }
}
