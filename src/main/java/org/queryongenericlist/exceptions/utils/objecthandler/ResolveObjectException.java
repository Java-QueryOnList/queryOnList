package org.queryongenericlist.exceptions.utils.objecthandler;

import org.queryongenericlist.exceptions.QueryOnListException;

public class ResolveObjectException extends QueryOnListException {
    public ResolveObjectException(String message) {
        super(message);
    }

    public ResolveObjectException(String message, Throwable cause) {
        super(message, cause);
    }
}
