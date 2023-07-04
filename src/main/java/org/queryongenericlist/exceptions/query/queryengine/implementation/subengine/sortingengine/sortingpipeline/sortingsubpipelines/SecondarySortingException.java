package org.queryongenericlist.exceptions.query.queryengine.implementation.subengine.sortingengine.sortingpipeline.sortingsubpipelines;

import org.queryongenericlist.exceptions.query.queryengine.implementation.subengine.sortingengine.sortingpipeline.SortingPipelineException;

public class SecondarySortingException extends SortingPipelineException {
    public SecondarySortingException(String message) {
        super(message);
    }

    public SecondarySortingException(String message, Throwable cause) {
        super(message, cause);
    }
}
