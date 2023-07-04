package org.queryongenericlist.exceptions.utils;

import org.queryongenericlist.exceptions.QueryOnListException;

public class ComparativeHelperException extends QueryOnListException {
    public ComparativeHelperException(String message) {
        super(message);
    }

    public ComparativeHelperException(String message, Throwable cause) {
        super(message, cause);
    }
}
