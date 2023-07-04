package org.queryongenericlist.exceptions.query.queryengine.implementation.subengine.filterengine;

import org.queryongenericlist.exceptions.query.queryengine.implementation.SuperQueryEngineException;

public class FilterEngineException extends SuperQueryEngineException {
    public FilterEngineException(String message) {
        super(message);
    }

    public FilterEngineException(String message, Throwable cause) {
        super(message, cause);
    }
}
