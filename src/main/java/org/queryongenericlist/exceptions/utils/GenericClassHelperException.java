package org.queryongenericlist.exceptions.utils;

import org.queryongenericlist.exceptions.QueryOnListException;

public class GenericClassHelperException extends QueryOnListException {
    public GenericClassHelperException(String message) {
        super(message);
    }

    public GenericClassHelperException(String message, Throwable cause) {
        super(message, cause);
    }
}
