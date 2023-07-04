package org.queryongenericlist.exceptions.query.queryengine.implementation;

import org.queryongenericlist.exceptions.query.queryengine.QueryEngineException;

public class SuperQueryEngineException extends QueryEngineException {
    public SuperQueryEngineException(String message) {
        super(message);
    }

    public SuperQueryEngineException(String message, Throwable cause) {
        super(message, cause);
    }
}
