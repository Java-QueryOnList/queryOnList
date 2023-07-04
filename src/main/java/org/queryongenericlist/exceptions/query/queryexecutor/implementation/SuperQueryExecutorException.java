package org.queryongenericlist.exceptions.query.queryexecutor.implementation;

import org.queryongenericlist.exceptions.query.queryexecutor.QueryExecutorException;

public class SuperQueryExecutorException extends QueryExecutorException {
    public SuperQueryExecutorException(String message) {
        super(message);
    }

    public SuperQueryExecutorException(String message, Throwable cause) {
        super(message, cause);
    }
}
