package org.queryongenericlist.exceptions.query.queryengine.implementation.subengine.sortingengine.sortingpipeline.sortingsubpipelines;

import org.queryongenericlist.exceptions.query.queryengine.implementation.subengine.sortingengine.sortingpipeline.SortingPipelineException;

public class PrepareComparablePipelineException extends SortingPipelineException {
    public PrepareComparablePipelineException(String message) {
        super(message);
    }

    public PrepareComparablePipelineException(String message, Throwable cause) {
        super(message, cause);
    }
}