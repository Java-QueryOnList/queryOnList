package org.queryongenericlist.exceptions.query.queryengine;

import org.queryongenericlist.exceptions.QueryOnListException;

public class QueryEngineException extends QueryOnListException {
    public QueryEngineException(String message) {
        super(message);
    }

    public QueryEngineException(String message, Throwable cause) {
        super(message, cause);
    }
}
