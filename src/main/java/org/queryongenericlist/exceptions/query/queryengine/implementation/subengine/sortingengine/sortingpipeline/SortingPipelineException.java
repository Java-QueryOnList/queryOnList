package org.queryongenericlist.exceptions.query.queryengine.implementation.subengine.sortingengine.sortingpipeline;

import org.queryongenericlist.exceptions.query.queryengine.implementation.subengine.sortingengine.SortingEngineException;

public class SortingPipelineException extends SortingEngineException {
    public SortingPipelineException(String message) {
        super(message);
    }

    public SortingPipelineException(String message, Throwable cause) {
        super(message, cause);
    }
}