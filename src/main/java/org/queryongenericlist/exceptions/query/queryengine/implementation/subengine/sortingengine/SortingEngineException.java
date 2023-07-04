package org.queryongenericlist.exceptions.query.queryengine.implementation.subengine.sortingengine;

import org.queryongenericlist.exceptions.query.queryengine.implementation.SuperQueryEngineException;

public class SortingEngineException extends SuperQueryEngineException {
    public SortingEngineException(String message) {
        super(message);
    }

    public SortingEngineException(String message, Throwable cause) {
        super(message, cause);
    }
}