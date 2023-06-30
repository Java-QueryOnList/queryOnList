package org.queryongenericlist.exceptions;

public class QueryOnListException extends RuntimeException {

    public QueryOnListException(String message) {
        super(message);
    }

    public QueryOnListException(String message, Throwable cause) {
        super(message, cause);
    }
}
