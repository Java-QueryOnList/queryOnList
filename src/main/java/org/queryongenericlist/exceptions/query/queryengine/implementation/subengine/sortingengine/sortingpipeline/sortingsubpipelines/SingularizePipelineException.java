package org.queryongenericlist.exceptions.query.queryengine.implementation.subengine.sortingengine.sortingpipeline.sortingsubpipelines;

import org.queryongenericlist.exceptions.query.queryengine.implementation.subengine.sortingengine.sortingpipeline.SortingPipelineException;

public class SingularizePipelineException extends SortingPipelineException {
    public SingularizePipelineException(String message) {
        super(message);
    }

    public SingularizePipelineException(String message, Throwable cause) {
        super(message, cause);
    }
}
