package org.queryongenericlist.exceptions.utils.objecthandler;

import org.queryongenericlist.exceptions.QueryOnListException;

public class IfArrayConvertToListException extends QueryOnListException {
    public IfArrayConvertToListException(String message) {
        super(message);
    }

    public IfArrayConvertToListException(String message, Throwable cause) {
        super(message, cause);
    }
}
