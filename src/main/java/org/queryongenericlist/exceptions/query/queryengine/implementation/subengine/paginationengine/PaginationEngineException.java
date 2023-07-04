package org.queryongenericlist.exceptions.query.queryengine.implementation.subengine.paginationengine;

import org.queryongenericlist.exceptions.query.queryengine.implementation.SuperQueryEngineException;

public class PaginationEngineException extends SuperQueryEngineException {
    public PaginationEngineException(String message) {
        super(message);
    }

    public PaginationEngineException(String message, Throwable cause) {
        super(message, cause);
    }
}
