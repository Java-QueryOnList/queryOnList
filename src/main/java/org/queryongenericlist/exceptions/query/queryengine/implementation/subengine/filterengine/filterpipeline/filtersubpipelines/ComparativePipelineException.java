package org.queryongenericlist.exceptions.query.queryengine.implementation.subengine.filterengine.filterpipeline.filtersubpipelines;

import org.queryongenericlist.exceptions.query.queryengine.implementation.subengine.filterengine.filterpipeline.FilterPipelineException;

public class ComparativePipelineException extends FilterPipelineException {
    public ComparativePipelineException(String message) {
        super(message);
    }

    public ComparativePipelineException(String message, Throwable cause) {
        super(message, cause);
    }
}
